package com.xuecheng.manage_cms.controller;

import com.xuecheng.framework.domain.cms.CmsConfig;
import com.xuecheng.manage_cms.service.CmsConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cms/config")
public class CmsConfigController {
    @Autowired
    CmsConfigService cmsConfigService;

    public CmsConfig findById(@PathVariable("id") String id){
        CmsConfig byId = cmsConfigService.findById(id);
        return byId;
    }
}
