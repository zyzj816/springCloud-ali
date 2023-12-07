package org.example.pojo;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table("storage_tbl")
public class StorageTblPojo {
    @Id(keyType = KeyType.Auto)
    private Long id;
    private String commodityCode;
    private Integer count;
}
