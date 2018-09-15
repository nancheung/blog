package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : NanCheung
 * @create : 2018/9/16 4:15
 */
@Controller

public class PlayVideoController {
    
    
    private final PageController pageController;
    
    @Autowired
    public PlayVideoController(PageController pageController) {
        this.pageController = pageController;
    }
    
    @RequestMapping("/playVideo")
    public String playVideo(Model model){
        pageController.loadCommonInfo(model);
        return "plugin/playVideo";
    }
}
