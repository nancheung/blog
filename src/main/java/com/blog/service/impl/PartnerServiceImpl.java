package com.blog.service.impl;

import com.blog.mapper.PartnerMapper;
import com.blog.service.PartnerService;
import com.blog.vo.Pager;
import com.blog.vo.Partner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 友链实现类
 *
 * @author NanCheung
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PartnerServiceImpl implements PartnerService {
    
    private final PartnerMapper partnerMapper;
    
    @Autowired
    public PartnerServiceImpl(PartnerMapper partnerMapper) {
        this.partnerMapper = partnerMapper;
    }
    
    
    @Override
    public List<Partner> findAll() {
        return partnerMapper.findAll();
    }
    
    @Override
    public void savePartner(Partner partner) {
        partnerMapper.savePartner(partner);
    }
    
    @Override
    public List<Partner> loadPartner(Pager pager, String param) {
        pager.setStart(pager.getStart());
        return partnerMapper.loadPartner(pager, param);
    }
    
    @Override
    public Partner getPartnerById(Integer id) {
        return partnerMapper.getPartnerById(id);
    }
    
    @Override
    public void deletePartner(Integer id) {
        partnerMapper.deletePartner(id);
    }
    
    @Override
    public void updatePartner(Partner partner) {
        partnerMapper.updatePartner(partner);
    }
    
    @Override
    public void initPage(Pager pager) {
        int count = partnerMapper.initPage(pager);
        pager.setTotalCount(count);
    }
    
}
