package cn.ming.smartmedic.Service.Impl;

import cn.ming.smartmedic.Service.NewsService;
import cn.ming.smartmedic.dao.NewsDao;
import cn.ming.smartmedic.domain.News;
import cn.ming.smartmedic.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
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
    public List<News> listNews(Map<String, Object> con) {
        return newsDao.listNews(con);
    }

    @Override
    public News getNews(Long id) {
        return newsDao.getNews(id);
    }

    @Override
    public Integer saveNews(News news) {
        return newsDao.saveNews(news);
    }

    @Override
    public Integer updateNews(News news) {
        return newsDao.updateNews(news);
    }

    @Override
    public Integer deleteNews(BigInteger id) {
        return newsDao.deleteNews(id);
    }
}
