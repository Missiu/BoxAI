package com.boxai.model.dto.postcomment;
import lombok.Data;
import java.io.Serial;

import java.io.Serializable;

@Data
public class CommentAddDTO implements Serializable{
    /**
     * 用户 id
     */
    private Long userId;
    /**
     * 帖子 id
     */
    private Long postId;

    /**
     * 评论内容
     */

    private String comments;
    /**
     * 父级评论 id
     */
    private Long parentId;
    /**
     * 子级评论 id
     */
    private Long rootParentId;
    @Serial
    private static final long serialVersionUID = 1L;
}
