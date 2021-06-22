package com.minhnk.evenbus.message.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QueryDataMsg {

    private Long postId;

    private Long commentId;

    private String title;

    private String content;

    private String type;
}
