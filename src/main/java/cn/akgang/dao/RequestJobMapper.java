package cn.akgang.dao;

import cn.akgang.entity.RequestJob;
import cn.akgang.entity.RequestJobExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestJobMapper {
    int countByExample(RequestJobExample example);

    int deleteByExample(RequestJobExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RequestJob record);

    int insertSelective(RequestJob record);

    List<RequestJob> selectByExample(RequestJobExample example);

    RequestJob selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RequestJob record, @Param("example") RequestJobExample example);

    int updateByExample(@Param("record") RequestJob record, @Param("example") RequestJobExample example);

    int updateByPrimaryKeySelective(RequestJob record);

    int updateByPrimaryKey(RequestJob record);
}