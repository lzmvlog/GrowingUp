package cn.mapper;

import cn.model.User;

import java.util.List;

/**
 * @author 少杰
 * @create 2019/3/21  15:24
 * GrowingUp  cn.mapper
 */
public interface UserMapper {

    /**
     * 用户的注册
     * @param user 用户实体
     * @return 返回用户注册的信息
     */
    Integer userRegistered(User user);

    /**
     * 用户登录
     * @param user 用户实体
     * @return 返回用户登录时数据库验证返回的验证信息
     */
    Integer userLogin(User user);

    /**
     * 用户登录之后，页面判断用户是否登录，由此改变页面的登录信息
     * @param email 用户登录的email
     * @return 返回一个用户信息
     */
    User selectUserNameFromUserEmail(String email);

    /**
     * 通过贴子的 id 去获取这个发帖者
     * @param postId 贴子的 id
     * @return 返回用户的信息
     */
    User selectUserNameFromPostId(int postId);

    /**
     * 更改用户的密码
     * @param user 用户的实体
     * @return 返回更改的行数
     */
    Integer updateUserPassWord(User user);

    /**
     * 当用户注册时检差注册的 email 是否被使用
     * @param email 界面获取的注册 email
     * @return 返回数据库校验行数
     */
    Integer selectUserEmailOnly(String email);

    /**
     * 通过评论人的 email 来查询用户的名称
     * @param email 评论的email
     * @return 返回用户的实体
     */
   User selectUserNameByUserId(String email);

    /**
     * 查询所有用户信息
     * @return 返回数据库查到的集合
     */
    List<User> selectUserAllInfo();

    /**
     * 根据用户的 id 来查询用户信息
     * @param id 用户的 id
     * @return 返回用户的实体
     */
    User selectUserInfoById(Integer id);

    /**
     * 根据用户的 id 来查询用户信息
     * @param user 用户的实体
     * @return 返回数据库验证信息
     */
    Integer updateUserInfoById(User user);

    /**
     * 通过用户的email 来查询用户的 id
     * @param email 用户的 email
     * @return 返回用户的 id
     */
    Integer selectUserIdByEmail(String email);

    /**
     * 删除用户信息
     * @param id 用户的id
     * @return 返回数据库验证信息
     */
    Integer deleteUserByOne(Integer id);
}
