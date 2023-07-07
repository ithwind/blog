package com.ithwind.domain.vo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotArticleVo {
    private Long id;
    private String title;

    private Long viewPoint;
}
