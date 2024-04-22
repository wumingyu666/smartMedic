package cn.ming.smartmedic.Service;

import cn.ming.smartmedic.domain.Diseases;
import cn.ming.smartmedic.domain.News;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface DiseasesService {

    Integer countDiseases(Map<String, Object> con);

    List<Diseases> listDiseases(Map<String, Object> con);

    Diseases getDiseases(BigInteger id);

    Integer saveDiseases(News news);

    Integer updateDiseases(News news);

    Integer deleteDiseases(BigInteger id);
}
