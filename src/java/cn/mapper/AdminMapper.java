package cn.mapper;

import cn.model.Admin;

/**
 * @author 少杰
 * @create 2019/3/21  15:25
 * GrowingUp  cn.mapper
 */
public interface AdminMapper {

    /**
     * 管理员登录方法
     * @param admin 管理员实体
     * @return 返回数据库验证的信息
     */
    Integer login(Admin admin);

}
