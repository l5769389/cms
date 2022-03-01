package com.xuecheng.manage_cms.controller;

import com.xuecheng.api.cms.CmsPageControllerApi;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.manage_cms.service.CmsPageService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms/page")
public class CmsPageController implements CmsPageControllerApi {

    @Autowired
    CmsPageService cmsPageService;

    @Override
    @GetMapping("/list/{page}/{size}")
    @ApiOperation("分页查询列表")
    public QueryResponseResult findList(@PathVariable("page") int page,@PathVariable("size") int size, QueryPageRequest queryPageRequest) {
        QueryResponseResult list1 = cmsPageService.findList(page, size, queryPageRequest);
        return list1;
    }

    @Override
    @ApiOperation("新增")
    @PostMapping("/add")
    public CmsPageResult add(@RequestBody CmsPage cmsPage) {
        CmsPageResult result = cmsPageService.add(cmsPage);
        return result;
    }

    @Override
    @GetMapping("/get/{id}")
    public CmsPage findById(@PathVariable("id") String id) {
        CmsPage byId = cmsPageService.findById(id);
        return byId;
    }

    @Override
    @ApiOperation("根据id 更新cms_page")
    @PutMapping("/edit/{id}")
    public CmsPageResult edit(@PathVariable("id") String id,@RequestBody CmsPage cmsPage) {
        CmsPageResult update = cmsPageService.update(id, cmsPage);
        return  update;
    }

    @Override
    @DeleteMapping("/del/{id}")
    public CmsPageResult delete(@PathVariable("id") String id) {
        CmsPageResult result = cmsPageService.delete(id);
        return result;
    }
}
