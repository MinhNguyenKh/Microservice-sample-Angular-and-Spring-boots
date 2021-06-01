package com.minhnk.comment.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendDataVO {

    private Long id;

    private String content;

    private String type;

    private Long postId;
}
