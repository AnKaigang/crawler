package cn.akgang.dao;

import cn.akgang.entity.CFDA;
import cn.akgang.entity.CFDAExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CFDAMapper {
    int countByExample(CFDAExample example);

    int deleteByExample(CFDAExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CFDA record);

    int insertSelective(CFDA record);

    List<CFDA> selectByExample(CFDAExample example);

    CFDA selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CFDA record, @Param("example") CFDAExample example);

    int updateByExample(@Param("record") CFDA record, @Param("example") CFDAExample example);

    int updateByPrimaryKeySelective(CFDA record);

    int updateByPrimaryKey(CFDA record);
}