package cn.akgang.dao;

import cn.akgang.entity.RequestQueue;
import cn.akgang.entity.RequestQueueExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestQueueMapper {
    int countByExample(RequestQueueExample example);

    int deleteByExample(RequestQueueExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RequestQueue record);

    int insertSelective(RequestQueue record);

    List<RequestQueue> selectByExample(RequestQueueExample example);

    RequestQueue selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RequestQueue record, @Param("example") RequestQueueExample example);

    int updateByExample(@Param("record") RequestQueue record, @Param("example") RequestQueueExample example);

    int updateByPrimaryKeySelective(RequestQueue record);

    int updateByPrimaryKey(RequestQueue record);
}