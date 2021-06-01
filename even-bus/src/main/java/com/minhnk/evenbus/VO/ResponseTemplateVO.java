package com.minhnk.evenbus.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {

    private Long id;

    private String title;

    private String content;

    private String type;

    private String message;

    private Long postId;
}
