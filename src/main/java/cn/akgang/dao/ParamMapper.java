package cn.akgang.dao;

import cn.akgang.entity.RequestParam;
import cn.akgang.entity.ParamExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ParamMapper {
    int countByExample(ParamExample example);

    int deleteByExample(ParamExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RequestParam record);

    int insertSelective(RequestParam record);

    List<RequestParam> selectByExample(ParamExample example);

    RequestParam selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RequestParam record, @Param("example") ParamExample example);

    int updateByExample(@Param("record") RequestParam record, @Param("example") ParamExample example);

    int updateByPrimaryKeySelective(RequestParam record);

    int updateByPrimaryKey(RequestParam record);
}