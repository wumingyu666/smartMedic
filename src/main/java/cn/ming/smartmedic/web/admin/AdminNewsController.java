package cn.ming.smartmedic.web.admin;

import cn.ming.smartmedic.Service.NewsService;
import cn.ming.smartmedic.domain.News;
import cn.ming.smartmedic.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/v1/com/admin")
public class AdminNewsController {
    private static final String INPUT = "admin/news-input";
    private static final String LIST = "admin/news";
    private static final String REDIRECT_LIST = "redirect:/v1/com/admin/news";

    @Autowired
    private NewsService newsService;

    @GetMapping("/news")
    public String blogs(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer pageSize, Model model) {
        Map<String, Object> con = new HashMap<>();
        Integer total = newsService.countNews(con);
        con.put("page", page);
        con.put("pageSize", pageSize);
        List<News> news = newsService.listNews(con);
        model.addAttribute("total", total);
        model.addAttribute("news", news);
        model.addAttribute("curPage", page);
        int totalPages = (int) Math.ceil((double) total / pageSize);
        model.addAttribute("totalPages", totalPages);
        return LIST;
    }

    @PostMapping("/news/search")
    public String searchblogs(String title, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer pageSize, Model model) {
        Map<String, Object> con = new HashMap<>();
        con.put("title", title);
        con.put("page", page);
        con.put("pageSize", pageSize);
        List<News> news = newsService.listNews(con);
        model.addAttribute("news", news);
        return "admin/news :: newsList";
    }

    @GetMapping("/news/input")
    public String input(Model model) {
        model.addAttribute("news", new News());

        return "v1/com/admin/news-input";
    }

    @GetMapping("/news/{id}/input")
    public String editinput(@PathVariable Long id, Model model) {
        News news = newsService.getNews(id);
        model.addAttribute("news", news);
        return "admin/news-input";
    }

    @PostMapping("/news")
    public String post(News news, RedirectAttributes redirectAttributes, HttpSession httpSession) {
        BigInteger userId = (BigInteger) httpSession.getAttribute("userId");
        news.setUserId(userId);
        Integer res;
        if (news.getId() == null) {
            res = newsService.saveNews(news);
        } else {
            res = newsService.updateNews(news);
        }
        if (res == 0) {
            redirectAttributes.addFlashAttribute("message", "操作失败");
        } else {
            redirectAttributes.addFlashAttribute("message", "操作成功");
        }
        return REDIRECT_LIST;
    }

    @GetMapping("/news/{id}/delete")
    public String delete(@PathVariable BigInteger id, RedirectAttributes redirectAttributes) {
        newsService.deleteNews(id);
        redirectAttributes.addFlashAttribute("message", "删除成功");
        return REDIRECT_LIST;
    }

}
