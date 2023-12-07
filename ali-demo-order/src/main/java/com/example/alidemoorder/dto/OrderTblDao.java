package com.example.alidemoorder.dto;

import com.example.alidemoorder.pojo.OrderTblPojo;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderTblDao extends BaseMapper<OrderTblPojo> {
}
