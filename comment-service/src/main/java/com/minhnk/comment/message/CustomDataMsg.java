package com.minhnk.comment.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomDataMsg {

    private Long postId;

    private Long commentId;

    private String title;

    private String content;

    private String type;

}
