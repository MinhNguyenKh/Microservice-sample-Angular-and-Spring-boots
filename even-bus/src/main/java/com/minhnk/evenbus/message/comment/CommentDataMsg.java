package com.minhnk.evenbus.message.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentDataMsg {

    private Long id;

    private String content;

    private Long postId;
}
