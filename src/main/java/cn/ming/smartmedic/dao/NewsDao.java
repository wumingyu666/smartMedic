package cn.ming.smartmedic.dao;

import cn.ming.smartmedic.domain.News;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface NewsDao {

    Integer countNews(Map<String, Object> con);

    List<Map<String, Object>> listNews(Map<String, Object> con);
}
