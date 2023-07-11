package com.ithwind.controller;

import com.ithwind.api.CommonResult;
import com.ithwind.service.CategoryService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    CategoryService categoryService;

    @GetMapping("/getCategoryList")
    @CrossOrigin(origins = "http://localhost:8080")
    public CommonResult<?> getCategoryList(){
        return categoryService.getCategoryList();
    }
}
