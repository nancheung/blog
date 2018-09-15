package com.blog.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.blog.service.UserService;
import com.blog.util.ImageCutUtil;
import com.blog.util.PhotoUploadUtil;
import com.blog.vo.PhotoResult;
import com.blog.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 上传图片的controller
 *
 * @author NanCheung
 */
@RestController
public class ImageController {
    
    private Logger logger = LoggerFactory.getLogger(AdminArticleController.class);
    
    
    private final UserService userService;
    
    private final PhotoUploadUtil photoUploadUtil;
    
    @Autowired
    public ImageController(PhotoUploadUtil photoUploadUtil, UserService userService) {
        this.photoUploadUtil = photoUploadUtil;
        this.userService = userService;
    }
    
    @RequestMapping("/admin/article/imageUpload")
    public PhotoResult imageUpload(@RequestParam(value = "editormd-image-file") MultipartFile file) {
        PhotoResult result;
        try {
            
            result = photoUploadUtil.uploadPhoto(file.getBytes(), file.getOriginalFilename());
            return result;
        } catch (IOException e) {
            logger.error("{}.imageUpload方法上传文件出错,错误信息:{}", e.getMessage());
            return new PhotoResult(0, "", "IO异常上传失败！！！");
        }
    }
    
    /**
     * 头像修改
     *
     * @param request    获取session的request
     * @param avatarData 图片裁剪的内容
     * @param file       图片
     */
    @RequestMapping("/admin/avatar/update")
    public PhotoResult updateAvatar(HttpServletRequest request, @RequestParam(value = "avatar_data") String avatarData, @RequestParam(value = "avatar_file") MultipartFile file) {
        PhotoResult result;
        String type = file.getContentType();
        //图片的开头格式
        String prefix = "image/";
        if (type == null || !type.toLowerCase().startsWith(prefix)) {
            return new PhotoResult(0, "", "不支持的文件类型，仅支持图片！");
        }
        try {
            
            JSONObject object = (JSONObject) JSONObject.parse(avatarData);
            //上传图片
            result = photoUploadUtil.uploadPhoto(ImageCutUtil.cutImageForByte(ImageIO.read(file.getInputStream()), (int) object.getFloatValue("x"), (int) object.getFloatValue("y"), (int) object.getFloatValue("width"), (int) object.getFloatValue("height")), file.getOriginalFilename());
            User user = (User) request.getSession().getAttribute("user");
            userService.updateAvatar(result.getUrl(), user.getUsername());
            result.setMessage("修改图像成功！！！");
            
        } catch (IOException e) {
            logger.error(e.getMessage());
            return new PhotoResult(0, "", "上传图片发生异常！");
        }
        return result;
    }
}
