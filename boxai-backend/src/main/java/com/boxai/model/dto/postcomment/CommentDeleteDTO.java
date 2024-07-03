package com.boxai.model.dto.postcomment;

import java.io.Serial;
import lombok.Data;


import java.io.Serializable;

@Data
public class CommentDeleteDTO implements Serializable{
    /**
     * 用户 id
     */
    private Long userId;
    /**
     * 帖子 id
     */
    private Long postId;
    /**
     * 评论 id
     */
    private Long id;

    @Serial
    private static final long serialVersionUID = 1L;
}
