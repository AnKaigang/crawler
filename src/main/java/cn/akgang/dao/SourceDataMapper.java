package cn.akgang.dao;

import cn.akgang.entity.SourceData;
import cn.akgang.entity.SourceDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceDataMapper {
    int countByExample(SourceDataExample example);

    int deleteByExample(SourceDataExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SourceData record);

    int insertSelective(SourceData record);

    List<SourceData> selectByExampleWithBLOBs(SourceDataExample example);

    List<SourceData> selectByExample(SourceDataExample example);

    SourceData selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SourceData record, @Param("example") SourceDataExample example);

    int updateByExampleWithBLOBs(@Param("record") SourceData record, @Param("example") SourceDataExample example);

    int updateByExample(@Param("record") SourceData record, @Param("example") SourceDataExample example);

    int updateByPrimaryKeySelective(SourceData record);

    int updateByPrimaryKeyWithBLOBs(SourceData record);

    int updateByPrimaryKey(SourceData record);
}