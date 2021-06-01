package com.minhnk.post.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendDataVO {

    private Long id;

    private String title;

    private String type;
}
