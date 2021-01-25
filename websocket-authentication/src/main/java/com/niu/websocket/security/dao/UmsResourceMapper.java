package com.niu.websocket.security.dao;


import com.niu.websocket.security.entity.UmsResource;
import com.niu.websocket.security.entity.UmsResourceExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsResourceMapper {

    List<UmsResource> selectByExample(UmsResourceExample example);

    UmsResource selectByPrimaryKey(Long id);

}