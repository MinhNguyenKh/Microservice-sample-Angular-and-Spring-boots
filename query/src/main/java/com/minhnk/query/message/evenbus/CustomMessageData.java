package com.minhnk.query.message.evenbus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomMessageData {

    private Long postId;

    private Long commentId;

    private String title;

    private String content;
}
