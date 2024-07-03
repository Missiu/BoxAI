package com.boxai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boxai.common.base.ReturnCode;
import com.boxai.exception.customize.CustomizeReturnException;
import com.boxai.mapper.UsersMapper;
import com.boxai.model.dto.postcomment.CommentAddDTO;
import com.boxai.model.dto.postcomment.CommentDeleteDTO;
import com.boxai.model.entity.PostComments;
import com.boxai.model.entity.Users;
import com.boxai.service.PostCommentsService;
import com.boxai.mapper.PostCommentsMapper;
import com.boxai.utils.CommentUtils.CommentUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
 @Resource
    private PostCommentsMapper postCommentsMapper;
    @Autowired(required = false)
    private UsersMapper usersMapper;

    /**
     * 获取评论列表
     *
     * @param postId
     * @return
     */
    @Override
    public List<PostComments> getCommentList(Long postId) {
        List<PostComments> list =  postCommentsMapper.getCommentList(postId);
        return CommentUtils.processComments(list);
    }

    /**
     * 获取评论列表总数
     *
     * @param postId
     * @return
     */
    @Override
    public Long getCommentListTotal(Long postId) {
        return  postCommentsMapper.getCommentListTotal(postId);
    }

    /**
     * 添加评论
     *
     * @param comment
     * @return
     */
    @Override
    public boolean save(PostComments comment) {
        Users users = usersMapper.selectById(comment.getUserId().intValue());
//        comment.setUserName(user.getUsername());
        comment.setCreateTime(new Date());
        comment.setIsDelete(0);
        return  postCommentsMapper.save(comment) > 0;
    }

    /**
     * 删除评论
     *
     * @param comment
     * @return
     */
    @Override
    public boolean removeComment(PostComments comment) {
        Queue<PostComments> queue = new LinkedList<>();
        queue.offer(comment);
        while (!queue.isEmpty()) {
            PostComments cur = queue.poll();
            int resultNum = postCommentsMapper.removeById(cur.getId());
            if (resultNum <= 0) return false;
            if (cur.getChild() != null) {
                List<PostComments> child = cur.getChild();
                for (PostComments tmp : child)
                    queue.offer(tmp);
            }
        }
        return true;
    }

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
//        List<Integer> commentIds = new ArrayList<>();
//        commentIds.add(commentDeleteDTO.getCommentId());
//        roleIds.forEach(roleId -> {
//        }
        return null;
    }
}




