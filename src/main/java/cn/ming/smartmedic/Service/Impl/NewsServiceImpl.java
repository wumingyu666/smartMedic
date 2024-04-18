package cn.ming.smartmedic.Service.Impl;

import cn.ming.smartmedic.Service.NewsService;
import cn.ming.smartmedic.dao.NewsDao;
import cn.ming.smartmedic.domain.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsDao newsDao;

    @Override
    public Integer countNews(Map<String, Object> con) {
        return newsDao.countNews(con);
    }

    @Override
    public List<Map<String, Object>> listNews(Map<String, Object> con) {
        return newsDao.listNews(con);
    }
}
