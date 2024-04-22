package cn.ming.smartmedic.Service.Impl;

import cn.ming.smartmedic.Service.DiseasesService;
import cn.ming.smartmedic.Service.NewsService;
import cn.ming.smartmedic.dao.DiseasesDao;
import cn.ming.smartmedic.dao.NewsDao;
import cn.ming.smartmedic.domain.Diseases;
import cn.ming.smartmedic.domain.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Service
public class DiseasesServiceImpl implements DiseasesService {

    @Autowired
    private DiseasesDao diseasesDao;

    @Override
    public Integer countDiseases(Map<String, Object> con) {
        return diseasesDao.countDiseases(con);
    }

    @Override
    public List<Diseases> listDiseases(Map<String, Object> con) {
        return diseasesDao.listDiseases(con);
    }

    @Override
    public Diseases getDiseases(BigInteger id) {
        return diseasesDao.getDiseases(id);
    }

    @Override
    public Integer saveDiseases(News news) {
        return diseasesDao.saveDiseases(news);
    }

    @Override
    public Integer updateDiseases(News news) {
        return diseasesDao.updateDiseases(news);
    }

    @Override
    public Integer deleteDiseases(BigInteger id) {
        return diseasesDao.deleteDiseases(id);

    }
}
