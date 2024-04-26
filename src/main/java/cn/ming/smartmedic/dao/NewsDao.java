package cn.ming.smartmedic.dao;

import cn.ming.smartmedic.domain.News;
import cn.ming.smartmedic.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Mapper
public interface NewsDao {

    Integer countNews(Map<String, Object> con);

    List<News> listNews(Map<String, Object> con);

    News getNews(BigInteger id);

    Integer saveNews(News news);

    Integer updateNews(News news);

    Integer deleteNews(BigInteger id);

}
