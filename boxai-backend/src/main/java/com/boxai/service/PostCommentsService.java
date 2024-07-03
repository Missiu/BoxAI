package com.boxai.service;

import com.boxai.model.dto.postcomment.CommentAddDTO;
import com.boxai.model.dto.postcomment.CommentDeleteDTO;
import com.boxai.model.entity.PostComments;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Hzh
* @description 针对表【post_comments(帖子评论信息表)】的数据库操作Service
* @createDate 2024-05-13 19:42:53
*/
public interface PostCommentsService extends IService<PostComments> {

    Boolean addComment(CommentAddDTO commentAddDTO);

    Boolean deleteComment(CommentDeleteDTO commentDeleteDTO);
    /**
     * 获取评论列表
     *
     * @param postId
     * @return
     */
    static List<PostComments> getCommentList(Long postId);

    /**
     * 获取评论列表总数
     *
     * @param postId
     * @return
     */
    static Long getCommentListTotal(Long postId);

    /**
     * 添加评论
     *
     * @param  comment
     * @return
     */
    static boolean save(PostComments comment);

    /**
     * 删除评论
     *
     * @param comment
     * @return
     */
    static boolean removeComment(PostComments comment);
}
