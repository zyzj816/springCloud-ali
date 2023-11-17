package org.example.pojo;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.util.Date;

@Data
@Table("mdx_user")
public class MxdUser {
    @Id(keyType = KeyType.Auto)
    private Long userId;

    private String userName;

    private String password;

    private String nick;
    private String phone;

    private String email;

    private Integer status;

    private Integer sex;
    private String remarks;

    private Date lastLoginTime;

    private Date createTime;

    private Date updateTime;
}
