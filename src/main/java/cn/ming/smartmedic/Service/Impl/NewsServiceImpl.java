package cn.ming.smartmedic.Service.Impl;

import cn.ming.smartmedic.Service.NewsService;
import cn.ming.smartmedic.dao.NewsDao;
import cn.ming.smartmedic.domain.News;
import cn.ming.smartmedic.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsDao newsDao;

    @Autowired
    private CosServiceImpl cosService;

    @Override
    public Integer countNews(Map<String, Object> con) {
        return newsDao.countNews(con);
    }

    @Override
    public List<News> listNews(Map<String, Object> con) {
        return newsDao.listNews(con);
    }

    @Override
    public News getNews(BigInteger id) {
        return newsDao.getNews(id);
    }

    @Override
    public Integer saveNews(News news) {
        String cosImagePath = cosService.uploadImage(new File(news.getFirstPicture()));
        news.setFirstPicture(cosImagePath);
        return newsDao.saveNews(news);
    }

    @Override
    public Integer updateNews(News news) {
        News existNews = newsDao.getNews(news.getId());
        if (!Objects.equals(existNews.getFirstPicture(), news.getFirstPicture())) {
            String cosImagePath = cosService.uploadImage(new File(news.getFirstPicture()));
            news.setFirstPicture(cosImagePath);
        }
        return newsDao.updateNews(news);
    }

    @Override
    public Integer deleteNews(BigInteger id) {
        return newsDao.deleteNews(id);
    }
}
