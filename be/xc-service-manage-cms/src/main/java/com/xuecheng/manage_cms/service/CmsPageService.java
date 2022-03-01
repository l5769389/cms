package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class CmsPageService implements ICmsPageService {

    @Autowired
    CmsPageRepository cmsPageRepository;

    @Override
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest) {
        // 分页条件
        Pageable pageable = PageRequest.of(page, size);
        // 自定义查找对象
        CmsPage filterCmsPage = new CmsPage();
        if (StringUtils.isNotEmpty(queryPageRequest.getSiteId())) {
            filterCmsPage.setSiteId(queryPageRequest.getSiteId());
        }
        if (StringUtils.isNotEmpty(queryPageRequest.getTemplateId())) {
            filterCmsPage.setSiteId(queryPageRequest.getTemplateId());
        }
        if (StringUtils.isNotEmpty(queryPageRequest.getPageAliase())) {
            filterCmsPage.setSiteId(queryPageRequest.getPageAliase());
        }
        // 查找example匹配对象，并且设置模糊查询的属性名。
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<CmsPage> example = Example.of(filterCmsPage, exampleMatcher);
        // 根据 example和分页条件对象查找。
        Page<CmsPage> all = cmsPageRepository.findAll(example, pageable);
        QueryResult queryResult = new QueryResult();
        queryResult.setList(all.getContent());
        queryResult.setTotal(all.getTotalElements());
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        return queryResponseResult;
    }

    @Override
    public CmsPageResult add(CmsPage cmsPage) {
        CmsPage searchedCmsPage = cmsPageRepository.findCmsPageByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(), cmsPage.getSiteId(), cmsPage.getPageWebPath());
        if (searchedCmsPage == null) {
            cmsPage.setPageId(null);
            CmsPage savedPage = cmsPageRepository.save(cmsPage);
            return new CmsPageResult(CommonCode.SUCCESS, savedPage);
        }else {
            ExceptionCast.exceptionCast(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);
            return  null;
        }
    }

    @Override
    public CmsPageResult update(String id, CmsPage cmsPage) {
        Optional<CmsPage> findResult = cmsPageRepository.findById(id);
        if (findResult.isPresent()) {
            CmsPage cmsPage1 = findResult.get();
            cmsPage1.setTemplateId(cmsPage.getTemplateId());
            cmsPage1.setSiteId(cmsPage.getSiteId());
            cmsPage1.setPageAliase(cmsPage.getPageAliase());
            cmsPage1.setPageName(cmsPage.getPageName());
            cmsPage1.setPageWebPath(cmsPage.getPageWebPath());
            cmsPage1.setPagePhysicalPath(cmsPage.getPagePhysicalPath());
            CmsPage save = cmsPageRepository.save(cmsPage1);
            // 保存成功会将保存的对象返回。
            if (save != null) {
                return new CmsPageResult(CommonCode.SUCCESS, save);
            }
        }
        return new CmsPageResult(CommonCode.FAIL, null);
    }

    @Override
    public CmsPage findById(String id) {
        Optional<CmsPage> byId = cmsPageRepository.findById(id);
        if (byId.isPresent()){
            return byId.get();
        }else {
            return  null;
        }
    }

    @Override
    public CmsPageResult delete(String id) {
        try {
            cmsPageRepository.deleteById(id);
            CmsPageResult cmsPageResult =new CmsPageResult(CommonCode.SUCCESS,null);
            return  cmsPageResult;
        }catch (Exception e){
            CmsPageResult cmsPageResult =new CmsPageResult(CommonCode.FAIL,null);
            return  cmsPageResult;
        }
    }
}
