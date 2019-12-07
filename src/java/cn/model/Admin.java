package cn.model;

import lombok.Data;

/**
 * @author 少杰
 * @create 2019/3/20  16:29
 * GrowUp  cn.mapper
 */
@Data
public class Admin {
    /**
     * 管理员id
     */
    private Integer id;
    /**
     * 管理员账号
     */
    private String account;
    /**
     * 管理员密码
     */
    private String password;
}
