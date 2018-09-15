package com.blog.controller.admin;

import com.blog.constant.ProjectConstant;
import com.blog.service.PartnerService;
import com.blog.util.ResultInfo;
import com.blog.util.ResultInfoFactory;
import com.blog.vo.Pager;
import com.blog.vo.Partner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;

/**
 * 后台管理员的友链操作contributor
 *
 * @author NanCheung
 */
@RestController
@RequestMapping("/admin/partner")
public class AdminPartnerController {
    
    private final PartnerService partnerService;
    
    @Autowired
    public AdminPartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }
    
    @RequestMapping("/initPage")
    public Pager initPage(Pager pager) {
        partnerService.initPage(pager);
        return pager;
    }
    
    /**
     * 保存友链
     *
     * @param partner 友链对象
     */
    @RequestMapping("/save")
    public ResultInfo savePartner(Partner partner) {
        try {
            partner.setSiteName(URLDecoder.decode(partner.getSiteName(), ProjectConstant.CHARSET));
            partner.setSiteDesc(URLDecoder.decode(partner.getSiteDesc(), ProjectConstant.CHARSET));
            partner.setSiteUrl(URLDecoder.decode(partner.getSiteUrl(), ProjectConstant.CHARSET));
            partnerService.savePartner(partner);
        } catch (Exception e) {
            
            return ResultInfoFactory.getErrorResultInfo();
        }
        return ResultInfoFactory.getSuccessResultInfo();
        
    }
    
    /**
     * 更新友链
     *
     * @param partner 友链对象
     */
    @RequestMapping("/update")
    public ResultInfo updatePartner(Partner partner) {
        try {
            partner.setSiteName(URLDecoder.decode(partner.getSiteName(), ProjectConstant.CHARSET));
            partner.setSiteDesc(URLDecoder.decode(partner.getSiteDesc(), ProjectConstant.CHARSET));
            partner.setSiteUrl(URLDecoder.decode(partner.getSiteUrl(), ProjectConstant.CHARSET));
            if (partner.getSort() == null) {
                partner.setSort(0);
            }
            partnerService.updatePartner(partner);
        } catch (Exception e) {
            return ResultInfoFactory.getErrorResultInfo();
        }
        return ResultInfoFactory.getSuccessResultInfo();
        
    }
    
    /**
     * 删除友链
     *
     * @param id 友链ID
     */
    @RequestMapping("/delete/{id}")
    public ResultInfo deletePartner(@PathVariable Integer id) {
        try {
            partnerService.deletePartner(id);
        } catch (Exception e) {
            return ResultInfoFactory.getErrorResultInfo();
        }
        return ResultInfoFactory.getSuccessResultInfo();
    }
}
