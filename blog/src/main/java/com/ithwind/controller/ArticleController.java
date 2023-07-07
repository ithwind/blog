package com.ithwind.controller;


;
import com.ithwind.api.CommonResult;
import com.ithwind.domain.vo.HotArticleVo;
import com.ithwind.service.ArticleService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Resource
    private ArticleService articleService;
    @CrossOrigin
    @RequestMapping(value = "/hotArticleList", method = RequestMethod.GET)
    public CommonResult<List<HotArticleVo>> getHotArticleList(){
        return articleService.hotArticleList();
    }
}