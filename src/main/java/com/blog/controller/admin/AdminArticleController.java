package com.blog.controller.admin;

import com.blog.service.ArticleService;
import com.blog.service.CategoryService;
import com.blog.service.TagService;
import com.blog.service.UserService;
import com.blog.util.ResultInfo;
import com.blog.util.ResultInfoFactory;
import com.blog.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台管理 文章controller
 */
@Controller
@RequestMapping("/admin/article")
public class AdminArticleController {
    private Logger log = LoggerFactory.getLogger(AdminArticleController.class);
    
    /**
     * 文章service
     */
    private final ArticleService articleService;
    
    /**
     * 标签service
     */
    private final TagService tagService;
    
    /**
     * 分类service
     */
    private final CategoryService categoryService;

    private final UserService userService;
    
    @Autowired
    public AdminArticleController(ArticleService articleService, TagService tagService, CategoryService categoryService, UserService userService) {
        this.articleService = articleService;
        this.tagService = tagService;
        this.categoryService = categoryService;
        this.userService = userService;
    }
    
    /**
     * 初始化文章分页信息
     * @param pager 分页对象
     * @return
     */
    @RequestMapping("/initPage")
    @ResponseBody
    public Pager initPage(Pager pager) {
        articleService.initPager(pager);
        return pager;
    }

    /**
     * 跳转到添加页面
     * @return
     */
    @RequestMapping("/addPage")
    public String addPage() {
        return "admin/article/articleAdd";
    }

    /**
     * 初始化文章列表
     * @param pager 分页对象 分页对象
     * @param categoryId 搜索条件 分类id
     * @param tagIds 搜索条件 tag集合
     * @param title 搜索条件 文章标题
     * @param model 对象
     * @return
     */
    @RequestMapping("/load")
    public String loadArticle(Pager pager, Integer categoryId, String tagIds, String title, Model model) {
        /**
         * 设置start位置
         */
        pager.setStart(pager.getStart());
        //封装查询条件
        Map<String, Object> param = new HashMap<>();
        if (tagIds != null && !"".equals(tagIds)) {
            param.put("tags", tagIds.split(","));
        }else {
            param.put("tags", null);
        }
        param.put("categoryId", categoryId);
        param.put("title",title);
        param.put("pager", pager);
        //获取文章列表
        List<Article> articleList = articleService.loadArticle(param);
        model.addAttribute("articleList", articleList);
        return "admin/article/articleTable";
    }

    /**
     * 更新文章可用状态
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/updateStatue")
    @ResponseBody
    public ResultInfo updateStatue(Integer id, int status) {
        try {

            articleService.updateStatue(id, status);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultInfoFactory.getErrorResultInfo("更新状态失败,请稍后再尝试");
        }
        return ResultInfoFactory.getSuccessResultInfo();
    }

    /**
     * 获取条件,所有tag和category
     * @param model 对象
     * @return
     */
    @RequestMapping("/term")
    public String articleTerm(Model model) {
        List<Tag> tagList = tagService.getTagList();
        List<Category> categoryList = categoryService.getCategoryList();
        model.addAttribute("tagList", tagList);
        model.addAttribute("categoryList", categoryList);
        return "admin/article/articleInfo";
    }

    /**
     * 保存文章
     * @param article
     * @param tags
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public ResultInfo saveArticle(Article article, int[] tags){
        try {
            //解码文章内容防止出现部分特殊字符串被转义
            article.setDescription(URLDecoder.decode(article.getDescription(),"UTF-8"));
            article.setTitle(URLDecoder.decode(article.getTitle(),"UTF-8"));
            article.setContent(URLDecoder.decode(article.getContent(),"UTF-8"));
            User user = userService.getCurrentUser();
            article.setAuthor(user.getUsername());
            articleService.saveArticle(article, tags);
        }catch (Exception e){
            log.error(e.getMessage());
            return ResultInfoFactory.getErrorResultInfo("添加失败,请稍后再尝试");
        }
        return ResultInfoFactory.getSuccessResultInfo();

    }

    /**
     * 跳转到编辑页面
     * @param id
     * @param model 对象
     * @return
     */
    @RequestMapping("/editJump")
    public String updatePage(Integer id,Model model){
        Article article = articleService.getArticleById(id);
        model.addAttribute("article",article);
        return"admin/article/articleEdit";
    }

    /**
     * 获取更新文章信息
     * @param articleId 文章标题 用于获取文章信息
     * @param model 对象
     * @return
     */
    @RequestMapping("/updateInfo")
    public String updateInfo(Integer articleId,Model model){
        Article article = articleService.getArticleById(articleId);
        List<Category> categoryList = categoryService.getCategoryList();
        List<Tag> tagList = tagService.getTagList();
        model.addAttribute("article",article);
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("tagList",tagList);
        return "admin/article/articleEditInfo";
    }

    /**
     * 更新文章
     * @param article
     * @param tags
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public ResultInfo updateArticle(Article article,int[] tags){
        try {
            //解码文章内容防止出现部分特殊字符串被转义
            article.setDescription(URLDecoder.decode(article.getDescription(),"UTF-8"));
            article.setTitle(URLDecoder.decode(article.getTitle(),"UTF-8"));
            article.setContent(URLDecoder.decode(article.getContent(),"UTF-8"));
            articleService.updateArticle(article,tags);
        }catch (Exception e){
            log.error(e.getMessage());
            ResultInfoFactory.getErrorResultInfo("修改失败,请稍后再试!");
        }
        return ResultInfoFactory.getSuccessResultInfo();
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public ResultInfo deleteArticle(@PathVariable Integer id){
        try {

            articleService.deleteArticle(id);
        }catch (Exception e){
            log.error(e.getMessage());
            return ResultInfoFactory.getErrorResultInfo("删除失败!");
        }
        return ResultInfoFactory.getSuccessResultInfo();
    }


}