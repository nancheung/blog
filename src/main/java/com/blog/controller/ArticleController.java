package com.blog.controller;

import com.blog.constant.ProjectConstant;
import com.blog.service.*;
import com.blog.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 前台文章处理controller
 *
 * @author NanCheung
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
    /**
     * 文章service
     */
    private final ArticleService articleService;
    
    /**
     * 友情链接service
     */
    private final PartnerService partnerService;
    
    /**
     * 分类service
     */
    private final CategoryService categoryService;
    
    /**
     * 标签service
     */
    private final TagService tagService;
    
    private final UserService userService;
    
    @Autowired
    public ArticleController(ArticleService articleService, PartnerService partnerService, CategoryService categoryService, TagService tagService, UserService userService) {
        this.articleService = articleService;
        this.partnerService = partnerService;
        this.categoryService = categoryService;
        this.tagService = tagService;
        this.userService = userService;
    }
    
    /**
     * 加载分页列表数据
     *
     * @param pager 分页对象
     * @param model 对象
     */
    @RequestMapping("/load")
    public String loadArticle(Pager<Article> pager, Model model) {
        List<ArticleCustom> articleList = articleService.articleList(pager);
        System.out.println(articleList);
        model.addAttribute("articleList", articleList);
        return "blog/part/articleSummary";
    }
    
    /**
     * 加载文章
     * 包括总标签数
     * 总文章数量
     * 分类及每个分类文章数量
     * 友链集合
     */
    @RequestMapping("/details/{articleId}")
    public String loadArticle(@PathVariable Integer articleId, Model model) {
        ArticleCustom articleCustom = articleService.getArticleCustomById(articleId);
        //新增判断，当文章不存在或文章不展示的情况下，会跳转到404页面
        if (articleCustom == null) {
            return "redirect:/404";
        }
        
        //2018年5月28日16点30分 文章添加侵权声明头
        Integer id = articleCustom.getId();
        String domain = ProjectConstant.domain;
        String reference = ">作者：NanCheung 链接："+ domain +"article/details/" + id + " 来源：NanCheung | 博客 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。\n\n<br>";
        articleCustom.setContent(reference + articleCustom.getContent());
        
        //当前文章的所有信息
        List<Partner> partnerList = partnerService.findAll();
        List<CategoryCustom> categoryList = categoryService.initCategoryList();
        //上一篇
        Article lastArticle = articleService.getLastArticle(articleId);
        //下一篇
        Article nextArticle = articleService.getNextArticle(articleId);
        //增加浏览量
        articleService.addArticleCount(articleId);
        //获取文章总数量
        int articleCount = articleService.getArticleCount();
        //标签总数量
        int tagCount = tagService.getTagCount();
        
        UserInfo userInfo = userService.getUserInfo();
        model.addAttribute("lastArticle", lastArticle);
        model.addAttribute("nextArticle", nextArticle);
        model.addAttribute("article", articleCustom);
        model.addAttribute("categoryCount", categoryList.size());
        model.addAttribute("articleCount", articleCount);
        model.addAttribute("tagCount", tagCount);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("partnerList", partnerList);
        model.addAttribute(ProjectConstant.USER_INFO, userInfo);
        return "blog/article";
    }
    
    /**
     * 全局搜索
     *
     * @param keyword 关键字
     * @param model   对象
     */
    @RequestMapping("/content/search")
    public String search(String keyword, Model model) {
        List<Article> articleList = articleService.getArticleListByKeywords(keyword);
        model.addAttribute("articleList", articleList);
        return "blog/part/search-info";
    }
    
}
