package com.minhnk.evenbus.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomDataMsg implements Serializable {

    private Long postId;

    private Long commentId;

    private String title;

    private String content;

    private String type;

    private String postStatus;
}
