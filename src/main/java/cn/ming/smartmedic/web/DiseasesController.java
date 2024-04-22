package cn.ming.smartmedic.web;

import cn.ming.smartmedic.Service.DiseasesService;
import cn.ming.smartmedic.Service.NewsService;
import cn.ming.smartmedic.domain.Diseases;
import cn.ming.smartmedic.dto.ResponseDto;
import cn.ming.smartmedic.dto.StateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/v1/com/diseases/")
public class DiseasesController {

    @Autowired
    private DiseasesService diseasesService;

    @GetMapping("/list")
    public ResponseDto search(String name, Integer page, Integer pageSize) {

        Map<String, Object> con = new HashMap<>();
        con.put("name", name);
        Integer total = diseasesService.countDiseases(con);

        con.put("page", page);
        con.put("pageSize", pageSize);
        List<Diseases> list = diseasesService.listDiseases(con);

        Map<String, Object> res = new HashMap<>();
        res.put("total", total);
        res.put("list", list);
        return new ResponseDto(StateDto.SUCCESS, res);
    }
}
