package cn.mapper;

import cn.model.Advice;

import java.util.List;

/**
 * @author 少杰
 * @create 2019/4/25  22:46
 * GrowingUp  cn.mapper
 */
public interface AdviceMapper {

    /**
     * 新增建议
     * @param advice 建议的实体
     * @return 返回验证的数据
     */
    Integer insertAdviceInfo(Advice advice);

    /**
     * 查询所有的建议信息
     * @return 返回所有建议信息
     */
    List<Advice> selectAdviceAllInfo();
}