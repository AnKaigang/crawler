package cn.akgang.dao;

import cn.akgang.entity.Header;
import cn.akgang.entity.HeaderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HeaderMapper {
    int countByExample(HeaderExample example);

    int deleteByExample(HeaderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Header record);

    int insertSelective(Header record);

    List<Header> selectByExample(HeaderExample example);

    Header selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Header record, @Param("example") HeaderExample example);

    int updateByExample(@Param("record") Header record, @Param("example") HeaderExample example);

    int updateByPrimaryKeySelective(Header record);

    int updateByPrimaryKey(Header record);
}