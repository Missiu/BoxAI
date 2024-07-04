package com.boxai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boxai.model.dto.postcomment.CommentAddDTO;
import com.boxai.model.dto.postcomment.CommentDeleteDTO;
import com.boxai.model.entity.PostComments;
import com.boxai.model.vo.postcomments.PostCommentsListQueryVO;
import com.boxai.model.vo.user.UserInfoVO;
import com.boxai.service.PostCommentsService;
import com.boxai.mapper.PostCommentsMapper;
import com.boxai.service.UsersService;
import com.boxai.utils.CommentUtils.CommentUtils;
import com.boxai.utils.threadlocal.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Hzh
 * @description 针对表【post_comments(帖子评论信息表)】的数据库操作Service实现
 * @createDate 2024-05-13 19:42:53
 */
@Service
public class PostCommentsServiceImpl extends ServiceImpl<PostCommentsMapper, PostComments>
        implements PostCommentsService {
    @Autowired
    private PostCommentsMapper postCommentsMapper;

    @Override
    public Boolean addComment(CommentAddDTO commentAddDTO) {
        PostComments comments = new PostComments();
        // 获取评论者的信息
        UserInfoVO user = UserHolder.getUser();
        Long parentId = commentAddDTO.getParentId();
        // 如果是一级评论
        if (parentId == 0L){
            comments.setRootId(0L);
            comments.setParentId(0L);
        }else if (parentId > 0L){
            // 如果是二级
            Long commentId = baseMapper.selectByParentId(parentId);
            if (commentId != null){
                comments.setRootId(commentId);
                comments.setParentId(commentId);
            }else {
                // 如果是三级以上
                PostComments postComments = baseMapper.selectById(parentId);
                comments.setRootId(postComments.getId());
                comments.setParentId(parentId);
            }
        }
        comments.setCommentText(commentAddDTO.getComments());
        comments.setPostId(commentAddDTO.getPostId());
        comments.setUserId(user.getId());
        return postCommentsMapper.insert(comments) > 0;
    }

    @Override
    public Boolean deleteComment(CommentDeleteDTO commentDeleteDTO) {
        List<Long> commentIds = new ArrayList<>();
        Long postId = commentDeleteDTO.getPostId();
        Long userId = commentDeleteDTO.getUserId();
        Long Id = commentDeleteDTO.getId();
        commentIds.addAll(postCommentsMapper.selectByPostIdAndUserIdAndParentId(postId, userId, Id));
        int result=postCommentsMapper.deleteByIds(commentIds);
        return result > 0;
    }

    @Override
    public List<PostCommentsListQueryVO> getCommentList(Long postId) {
        List<PostCommentsListQueryVO> list =new ArrayList<>();
        List<PostComments> result =  postCommentsMapper.getCommentList(postId);
        for (PostComments postComments : result) {
            PostCommentsListQueryVO vo = new PostCommentsListQueryVO();
            // 手动设置VO的属性
            vo.setId(postComments.getId());
            vo.setPostId(postComments.getPostId());
            vo.setUserId(postComments.getUserId());
            vo.setParentId(postComments.getParentId());
            vo.setContent(postComments.getCommentText());
            vo.setCreateTime(postComments.getCreateTime());
//            vo.setRootParentId(postComments.getRootParentId());
            list.add(vo);
        }
        return CommentUtils.processComments(list);
    }

    @Override
    public Long getCommentListTotal(Long postId) {
        return  postCommentsMapper.getCommentListTotal(postId);
    }
}




