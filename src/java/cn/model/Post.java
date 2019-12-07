package cn.model;

import lombok.Data;

import java.util.Date;

/**
 * @author 少杰
 * @create 2019/4/12  10:10
 * GrowingUp  cn.model
 */
@Data
public class Post {
    /**
     * 贴子的id
     */
    private int id;
    /**
     * 贴子的标题
     */
    private String title;
    /**
     * 贴子的作者
     */
    private int author;
    /**
     * 帖子的图片的名称
     */
    private String srcname;
    /**
     * 贴子的描述
     */
    private String info;
    /**
     * 贴子的内容
     */
    private String context;
    /**
     * 贴子生成的时间
     */
    private Date time;
    /**
     * 贴子被阅读的次数
     */
    private int count;
    /**
     * 用户
     */
    private User user;

}
