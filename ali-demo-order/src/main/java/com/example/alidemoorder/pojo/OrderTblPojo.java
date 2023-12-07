package com.example.alidemoorder.pojo;

import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Table("order_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderTblPojo {
    private Long id;
    private Long userId;
    private String commodityCode;
    private Integer count;
    private BigDecimal money;
}
