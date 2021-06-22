package com.minhnk.evenbus.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EvenBusDataMsg {

    private Long postId;

    private Long commentId;

    private String title;

    private String content;

    private String type;
}
