package cn.ming.smartmedic.dao;

import cn.ming.smartmedic.domain.Diseases;
import cn.ming.smartmedic.domain.News;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Mapper
public interface DiseasesDao {

    Integer countDiseases(Map<String, Object> con);

    List<Diseases> listDiseases(Map<String, Object> con);

    Diseases getDiseases(BigInteger id);

    Integer saveDiseases(News news);

    Integer updateDiseases(News news);

    Integer deleteDiseases(BigInteger id);
}
