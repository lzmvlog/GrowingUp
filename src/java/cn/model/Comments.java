package cn.model;

import lombok.Data;

import java.util.Date;

/**
 * @author 少杰
 * @create 2019/4/28  22:42
 * GrowingUp  cn.model
 */
@Data
public class Comments {

    /**
     * 评论 id
     */
    private int id;
    /**
     * 贴子的 id
     */
    private int postId;
    /**
     * 用户的 id
     */
    private int userId;
    /**
     * 评论生成的时间
     */
    private Date time;
    /**
     * 评论的内容
     */
    private String commentInfo;
    /**
     * 用户的实体
     */
    private User user;
    /**
     * 贴子的实体
     */
    private Post post;

}
