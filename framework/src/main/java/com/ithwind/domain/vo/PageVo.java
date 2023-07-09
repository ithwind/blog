package com.ithwind.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//分页Vo
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVo {
    private List<?> rows;
    private Long total;
}
