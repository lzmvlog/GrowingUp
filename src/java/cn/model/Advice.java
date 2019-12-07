package cn.model;

import lombok.Data;

/**
 * @author 少杰
 * @create 2019/4/25  22:41
 * GrowingUp  cn.model
 */
@Data
public class Advice {
    /**
     * 建议的 id
     */
    private int id;
    /**
     * 电脑的ip
     */
    private String ip;
    /**
     * 建议的内容
     */
    private String adviceInfo;

}
