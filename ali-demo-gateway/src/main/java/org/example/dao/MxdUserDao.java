package org.example.dao;

import org.example.pojo.MxdUser;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MxdUserDao extends BaseMapper<MxdUser> {

}
