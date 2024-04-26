package cn.ming.smartmedic.web;

import cn.ming.smartmedic.Service.NewsService;
import cn.ming.smartmedic.domain.News;
import cn.ming.smartmedic.domain.User;
import cn.ming.smartmedic.dto.ResponseDto;
import cn.ming.smartmedic.dto.StateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/v1/com/news/")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @GetMapping("/list")
    @ResponseBody
    public ResponseDto search(String name, Integer page, Integer pageSize) {

        Map<String, Object> con = new HashMap<>();
        con.put("name", name);
        Integer total = newsService.countNews(con);

        con.put("page", page);
        con.put("pageSize", pageSize);
        List<News> list = newsService.listNews(con);

        Map<String, Object> res = new HashMap<>();
        res.put("total", total);
        res.put("list", list);
        return new ResponseDto(StateDto.SUCCESS, res);
    }
}
