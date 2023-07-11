package com.ithwind.controller;


import com.ithwind.api.CommonResult;
import com.ithwind.domain.vo.HotArticleVo;
import com.ithwind.domain.vo.PageVo;
import com.ithwind.service.ArticleService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Resource
    private ArticleService articleService;
    @RequestMapping(value = "/hotArticleList", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:8080")
    public CommonResult<List<HotArticleVo>> getHotArticleList(){
        return articleService.hotArticleList();
    }

    @RequestMapping(value = "/articleList", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:8080")
    public CommonResult<PageVo> articleList(@RequestParam("pageNum")Integer pageNum,
                                            @RequestParam("pageSize")Integer pageSize,
                                            Long categoryId){

        return articleService.articleList(pageNum, pageSize, categoryId);
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:8080")
    public CommonResult<?> getArticleDetail(@PathVariable("id") Long id){
        return articleService.getArticleDetail(id);
    }
}