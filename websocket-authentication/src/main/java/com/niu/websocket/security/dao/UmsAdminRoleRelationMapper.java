package com.niu.websocket.security.dao;


import com.niu.websocket.security.entity.UmsAdminRoleRelation;
import com.niu.websocket.security.entity.UmsAdminRoleRelationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsAdminRoleRelationMapper {

    List<UmsAdminRoleRelation> selectByExample(UmsAdminRoleRelationExample example);

    UmsAdminRoleRelation selectByPrimaryKey(Long id);
}