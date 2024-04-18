package cn.ming.smartmedic.Service;

import cn.ming.smartmedic.domain.News;

import java.util.List;
import java.util.Map;

public interface NewsService {

    Integer countNews(Map<String, Object> con);

    List<Map<String, Object>> listNews(Map<String, Object> con);
}
