package cn.model;

import lombok.Data;

/**
 * @author 少杰
 * @create 2019/3/21  15:18
 * GrowingUp  cn.model
 */
@Data
public class User {
    /**
     * 用户的id
     */
    private Integer id;
    /**
     * 用户的名称
     */
    private String name;
    /**
     * 用户的邮箱
     */
    private String email;
    /**
     * 用户的密码
     */
    private String password;

}
