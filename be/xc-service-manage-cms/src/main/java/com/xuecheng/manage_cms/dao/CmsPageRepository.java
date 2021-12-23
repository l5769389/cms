package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface CmsPageRepository extends MongoRepository<CmsPage,String> {

        // findList 没写，直接使用了MongoDB自带的方法,
    CmsPage findCmsPageByPageNameAndSiteIdAndPageWebPath(String pageName,String siteId,String pageWebPath);

    //Optional 类是一个可以为null的容器对象。
    // 如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象。
    Optional<CmsPage> findById(String id);
}
