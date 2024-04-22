package cn.ming.smartmedic.Service;

import cn.ming.smartmedic.domain.News;
import cn.ming.smartmedic.domain.User;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface NewsService {

    Integer countNews(Map<String, Object> con);

    List<News> listNews(Map<String, Object> con);

    News getNews(Long id);

    Integer saveNews(News news);

    Integer updateNews(News news);

    Integer deleteNews(BigInteger id);
}
