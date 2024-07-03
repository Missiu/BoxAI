package com.boxai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boxai.common.base.ReturnCode;
import com.boxai.exception.customize.CustomizeReturnException;
import com.boxai.model.dto.postcomment.CommentAddDTO;
import com.boxai.model.dto.postcomment.CommentDeleteDTO;
import com.boxai.model.entity.PostComments;
import com.boxai.service.PostCommentsService;
import com.boxai.mapper.PostCommentsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.boxai.common.constants.RedisKeyConstant.POST_SHARE_COUNT;
import static net.sf.jsqlparser.parser.feature.Feature.comment;

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

        comments.setCommentText(commentAddDTO.getComments());
        comments.setCreateTime(new Date());
        comments.setPostId(commentAddDTO.getPostId());
        comments.setParentId(commentAddDTO.getParentId());
        comments.setUserId(commentAddDTO.getUserId());
        comments.setRootParentId(commentAddDTO.getRootParentId());

        return postCommentsMapper.insert(comments) > 0;
    }

    @Override
    public Boolean deleteComment(CommentDeleteDTO commentDeleteDTO) {
        List<Integer> commentIds = new ArrayList<>();
        commentIds.add(commentDeleteDTO.getCommentId());
        roleIds.forEach(roleId -> {
        }
        return null;
    }
}




