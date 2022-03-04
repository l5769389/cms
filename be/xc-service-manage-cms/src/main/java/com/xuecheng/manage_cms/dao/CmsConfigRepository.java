package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsConfig;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CmsConfigRepository extends MongoRepository<CmsConfig,String> {
    Optional<CmsConfig> findById(String id);
}
