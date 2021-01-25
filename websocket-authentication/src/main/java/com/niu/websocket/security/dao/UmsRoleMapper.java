package com.niu.websocket.security.dao;

import com.niu.websocket.security.entity.UmsRole;
import com.niu.websocket.security.entity.UmsRoleExample;

import java.util.List;

public interface UmsRoleMapper {

    List<UmsRole> selectByExample(UmsRoleExample example);

    UmsRole selectByPrimaryKey(Long id);
}