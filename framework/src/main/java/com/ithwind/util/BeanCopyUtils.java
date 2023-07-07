package com.ithwind.util;

import com.ithwind.domain.pojo.Article;
import com.ithwind.domain.vo.HotArticleVo;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {
    private BeanCopyUtils(){

    }
    //单个对象拷贝
    public static <V> V copyBean(Object source, Class<V> clazz){
        V result = null;
        try{
            //创建目标对象
            result =  clazz.getDeclaredConstructor().newInstance();
            //实现属性copy
            BeanUtils.copyProperties(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static <V>List<V> copyBeanList(List<?> list, Class<V> clazz){
        return list.stream()
                .map(o -> copyBean(o, clazz))
                .collect(Collectors.toList());
    }
    public static void main(String[] args) {
        Article article = new Article();
        article.setId(1L);
        article.setContent("aa");
        HotArticleVo articleVo = copyBean(article, HotArticleVo.class);
        System.out.println(articleVo.toString());
    }
}
