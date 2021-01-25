package com.niu.websocket.security.services;
import com.niu.websocket.security.entity.UmsResource;

import java.util.List;

/**
 * 后台资源管理Service
 */
public interface UmsResourceService {

    /**
     * 分页查询资源
     */
    List<UmsResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum);

    /**
     * 查询全部资源
     */
    List<UmsResource> listAll();
}
