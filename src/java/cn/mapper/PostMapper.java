package cn.mapper;

import cn.model.Post;
import javafx.geometry.Pos;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 少杰
 * @create 2019/4/12  10:19
 * GrowingUp  cn.mapper
 */
public interface PostMapper {

    /**
     * 查询优质贴
     *
     * @return 返回查询到的帖子集合
     */
    List<Post> selectPostInfo();

    /**
     * 根据帖子的 id 来查询整个帖子
     *
     * @param id 页面获取到的帖子的 id
     * @return 返回数据库返回的帖子对象
     */
    Post selectPostFromId(int id);

    /**
     * 增加贴子的访问量
     *
     * @param id 页面读取到的贴子的 id
     * @return 返回更改的访问量数据
     */
    Integer updatePostCount(Integer id);

    /**
     * 根据贴子的类型来查询贴子
     *
     * @param info 贴子的类型
     * @return 返回查询的结果
     */
    List<Post> selectPostAllByType(String info);

    /**
     * 查询所有的贴子信息
     *
     * @return 返回所有的贴子信息
     */
    List<Post> selectPostAllInfo();

    /**
     * 新增贴子
     *
     * @param post 贴子的实体
     * @return 返回数据库验证的信息
     */
    Integer insertPost(Post post);

    /**
     *  分页
     * @param currPage 页码
     * @return 返回数据长度
     */
    List<Post> postPagination(@Param("currPage") int currPage);

    /**
     * 查询贴子的条数
     * @return 返回贴子的条数
     */
    Integer selectPostCount();

    /**
     * 分页第一次
     * @return 返回数据库验证的贴子实体
     */
    List<Post> selectPostPagination();

}
