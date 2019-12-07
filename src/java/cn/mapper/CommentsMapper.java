package cn.mapper;

import cn.model.Comments;

import java.util.List;

/**
 * @author 少杰
 * @create 2019/4/28  22:52
 * GrowingUp  cn.mapper
 */
public interface CommentsMapper {

    /**
     * 新增贴子的评论
     * @param comments 评论实体
     * @return  返回数据库校验行数
     */
    Integer insertNewComments(Comments comments);

    /**
     * 根据贴子的 id 来查询
     * @param postId 该贴子的 id
     * @return 返回数据库的验证行数
     */
    List<Comments> selectCommentsByOne(Integer postId);

}
