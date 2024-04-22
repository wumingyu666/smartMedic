package cn.ming.smartmedic.web.admin;

import cn.ming.smartmedic.Service.DiseasesService;
import cn.ming.smartmedic.Service.NewsService;
import cn.ming.smartmedic.domain.Diseases;
import cn.ming.smartmedic.domain.News;
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
public class AdminDiseasesController {
    private static final String INPUT = "admin/diseases-input";
    private static final String LIST = "admin/diseases";
    private static final String REDIRECT_LIST = "redirect:/v1/com/admin/diseases";

    @Autowired
    private DiseasesService diseasesService;

    @GetMapping("/diseases")
    public String blogs(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer pageSize, Model model) {
        Map<String, Object> con = new HashMap<>();
        Integer total = diseasesService.countDiseases(con);
        con.put("page", page);
        con.put("pageSize", pageSize);
        List<Diseases> news = diseasesService.listDiseases(con);
        model.addAttribute("total", total);
        model.addAttribute("diseases", news);
        model.addAttribute("curPage", page);
        int totalPages = (int) Math.ceil((double) total / pageSize);
        model.addAttribute("totalPages", totalPages);
        return LIST;
    }

    @PostMapping("/diseases/search")
    public String searchblogs(String title, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer pageSize, Model model) {
        Map<String, Object> con = new HashMap<>();
        con.put("title", title);
        con.put("page", page);
        con.put("pageSize", pageSize);
        List<Diseases> diseases = diseasesService.listDiseases(con);
        model.addAttribute("diseases", diseases);
        return "v1/com/admin/diseases :: diseasesList";
    }

    @GetMapping("/diseases/input")
    public String input(Model model) {
        model.addAttribute("diseases", new Diseases());

        return "admin/diseases-input";
    }

    @GetMapping("/diseases/{id}/input")
    public String editinput(@PathVariable BigInteger id, Model model) {
        Diseases diseases = diseasesService.getDiseases(id);
        model.addAttribute("diseases", diseases);
        return "admin/diseases-input";
    }

    @PostMapping("/diseases")
    public String post(News news, RedirectAttributes redirectAttributes, HttpSession httpSession) {
        BigInteger userId = (BigInteger) httpSession.getAttribute("userId");
        news.setUserId(userId);
        Integer res;
        if (news.getId() == null) {
            res = diseasesService.saveDiseases(news);
        } else {
            res = diseasesService.updateDiseases(news);
        }
        if (res == 0) {
            redirectAttributes.addFlashAttribute("message", "操作失败");
        } else {
            redirectAttributes.addFlashAttribute("message", "操作成功");
        }
        return REDIRECT_LIST;
    }

    @GetMapping("/diseases/{id}/delete")
    public String delete(@PathVariable BigInteger id, RedirectAttributes redirectAttributes) {
        diseasesService.deleteDiseases(id);
        redirectAttributes.addFlashAttribute("message", "删除成功");
        return REDIRECT_LIST;
    }

}
