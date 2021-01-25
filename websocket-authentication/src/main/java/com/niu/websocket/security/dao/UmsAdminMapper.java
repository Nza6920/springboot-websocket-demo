package com.niu.websocket.security.dao;


import com.niu.websocket.security.entity.UmsAdmin;
import com.niu.websocket.security.entity.UmsAdminExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Zian.Niu
 */
public interface UmsAdminMapper {

    List<UmsAdmin> selectByExample(UmsAdminExample example);

    UmsAdmin selectByPrimaryKey(Long id);
}