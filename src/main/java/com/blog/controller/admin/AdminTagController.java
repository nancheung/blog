package com.blog.controller.admin;

import com.blog.service.TagService;
import com.blog.util.ResultInfo;
import com.blog.util.ResultInfoFactory;
import com.blog.vo.Pager;
import com.blog.vo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;


/**
 * 后台管理员的标签操作contributor
 *
 * @author NanCheung
 */
@Controller
@RequestMapping("/admin/tag")
public class AdminTagController {
    
    private final TagService tagService;
    
    @Autowired
    public AdminTagController(TagService tagService) {
        this.tagService = tagService;
    }
    
    
    /**
     * 初始化分页信息
     *
     * @param pager 分页对象
     */
    @RequestMapping("/initPage")
    @ResponseBody
    public Pager initPage(Pager pager) {
        tagService.initPage(pager);
        return pager;
    }
    
    /**
     * 编辑一个标签
     *
     * @param id    标签ID
     * @param model 对象
     */
    @RequestMapping("/editJump/{id}")
    public String editPage(@PathVariable Integer id, Model model) {
        Tag tag = tagService.getTagById(id);
        model.addAttribute("tag", tag);
        return "admin/label/labelEdit";
    }
    
    /**
     * 跳转到添加页面
     */
    @RequestMapping("/addJump")
    public String addPage() {
        return "admin/label/labelAdd";
    }
    
    /**
     * 分页加载标签
     *
     * @param pager   分页对象
     * @param tagName 标签名
     * @param model   对象
     */
    @RequestMapping("/load")
    public String loadTagList(Pager pager, String tagName, Model model) {
        List<Tag> tagList = tagService.loadTagList(pager, tagName);
        System.out.println(tagList);
        model.addAttribute("tagList", tagList);
        return "admin/label/labelTable";
    }
    
    /**
     * 保存标签
     *
     * @param tag 标签对象
     */
    @RequestMapping("/save")
    @ResponseBody
    public ResultInfo saveTag(Tag tag) {
        try {
            tag.setAliasName(URLDecoder.decode(tag.getAliasName(), "UTF-8"));
            tag.setTagName(URLDecoder.decode(tag.getTagName(), "UTF-8"));
            if (tagService.checkExist(tag)) {
                return ResultInfoFactory.getErrorResultInfo("标签名或别名已经存在");
            }
            tagService.saveTag(tag);
        } catch (UnsupportedEncodingException e) {
            ResultInfoFactory.getErrorResultInfo("添加失败,字符串格式化错误");
        }
        return ResultInfoFactory.getSuccessResultInfo();
    }
    
    /**
     * 更新标签
     *
     * @param tag 标签对象
     */
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateTag(Tag tag) {
        try {
            tag.setAliasName(URLDecoder.decode(tag.getAliasName(), "UTF-8"));
            tag.setTagName(URLDecoder.decode(tag.getTagName(), "UTF-8"));
            if (tagService.checkExist(tag)) {
                return ResultInfoFactory.getErrorResultInfo("已存在相同的标签名或者别名");
            }
            tagService.updateTag(tag);
        } catch (UnsupportedEncodingException e) {
            ResultInfoFactory.getErrorResultInfo("添加失败,字符串格式化错误");
        }
        return ResultInfoFactory.getSuccessResultInfo();
    }
    
    /**
     * 删除一个标签
     *
     * @param id 标签ID
     */
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public ResultInfo deleteTag(@PathVariable int id) {
        tagService.deleteTagById(id);
        return ResultInfoFactory.getSuccessResultInfo();
    }
}
