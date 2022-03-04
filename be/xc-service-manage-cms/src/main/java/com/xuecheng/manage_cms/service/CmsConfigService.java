package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsConfig;
import com.xuecheng.manage_cms.dao.CmsConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class CmsConfigService implements ICmsConfigService{
    @Autowired
    CmsConfigRepository cmsConfigRepository;
    @Override
    public CmsConfig findById(String id) {
        Optional<CmsConfig> optional = cmsConfigRepository.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }else {
         return  null;
        }
    }
}
