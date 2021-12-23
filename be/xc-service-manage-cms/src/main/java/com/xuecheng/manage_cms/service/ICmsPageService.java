package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import org.springframework.web.bind.annotation.PathVariable;

public interface ICmsPageService {
    public QueryResponseResult findList( int page, int size, QueryPageRequest queryPageRequest);
    public CmsPageResult add(CmsPage cmsPage);
    public CmsPageResult update(String id,CmsPage cmsPage);
    public CmsPage findById(String id);
    public CmsPageResult delete(String id);
}
