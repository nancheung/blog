package com.blog.controller.admin;

import com.blog.constant.ProjectConstant;
import com.blog.service.*;
import com.blog.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 后端管理的页面跳转controller
 *
 * @author NanCheung
 */
@Controller
@RequestMapping("/admin")
public class AdminPageController {
    
    private final CategoryService categoryService;
    
    private final TagService tagService;
    
    private final PartnerService partnerService;
    
    private final UserService userService;
    
    @Autowired
    public AdminPageController(CategoryService categoryService, TagService tagService, PartnerService partnerService, UserService userService) {
        this.categoryService = categoryService;
        this.tagService = tagService;
        this.partnerService = partnerService;
        this.userService = userService;
    }
    
    /**
     * 后台首页
     */
    @RequestMapping("/home")
    public String homePage() {
        return "redirect:/admin/article/list";
    }
    
    /**
     * 跳转到文章列表页面
     */
    @RequestMapping("/article/list")
    public String articlePage(Model model) {
        List<Tag> tagList = tagService.getTagList();
        List<Category> categoryList = categoryService.getCategoryList();
        UserInfo userInfo = userService.getUserInfo();
        model.addAttribute(ProjectConstant.USER_INFO, userInfo);
        model.addAttribute("tagList", tagList);
        model.addAttribute("categoryList", categoryList);
        return "admin/article/articleList";
    }
    
    
    /**
     * 跳转标签展示页面
     *
     * @param model 对象
     */
    @RequestMapping("/tag/list")
    public String labelPage(Model model) {
        UserInfo userInfo = userService.getUserInfo();
        model.addAttribute(ProjectConstant.USER_INFO, userInfo);
        return "admin/label/labelList";
    }
    
    /**
     * 加载友链分页
     *
     * @param pager 分页对象
     * @param model 对象
     */
    @RequestMapping("/partner/load")
    public String loadPartner(Pager pager, Model model, String param) {
        pager.setStart((pager.getPage() - 1) * pager.getLimit());
        List<Partner> partnerList = partnerService.loadPartner(pager, param);
        model.addAttribute("partnerList", partnerList);
        return "admin/partner/partnerTable";
    }
    
    /**
     * 跳转添加友链页面
     */
    @RequestMapping("/partner/addJump")
    public String partnerAddPage() {
        return "admin/partner/partnerAdd";
    }
    
    @RequestMapping("/partner/editJump/{id}")
    public String partnerEditPage(@PathVariable Integer id, Model model) {
        Partner partner = partnerService.getPartnerById(id);
        model.addAttribute("partner", partner);
        return "admin/partner/partnerEdit";
    }
    
    /**
     * 跳转到分类列表页面
     *
     * @return 分类列表页面
     */
    @RequestMapping("/category/list")
    public String categoryPage(Model model) {
        UserInfo userInfo = userService.getUserInfo();
        model.addAttribute(ProjectConstant.USER_INFO, userInfo);
        return "admin/category/categoryList";
    }
    
    
    /**
     * 跳转到友链展示页面
     */
    @RequestMapping("/partner/list")
    public String partnerPage(Model model) {
        UserInfo userInfo = userService.getUserInfo();
        model.addAttribute(ProjectConstant.USER_INFO, userInfo);
        return "admin/partner/partnerList";
    }
    
    @RequestMapping("/test")
    public String testPage() {
        return "admin/test";
    }
}
