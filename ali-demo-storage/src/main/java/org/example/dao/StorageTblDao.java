package org.example.dao;

import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.StorageTblPojo;

@Mapper
public interface StorageTblDao extends BaseMapper<StorageTblPojo> {
}
