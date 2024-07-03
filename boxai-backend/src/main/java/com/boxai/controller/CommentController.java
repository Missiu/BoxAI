package com.boxai.controller;

import com.boxai.common.base.R;
import com.boxai.common.base.ReturnCode;
import com.boxai.exception.customize.CustomizeReturnException;
import com.boxai.model.dto.postcomment.CommentAddDTO;
import com.boxai.model.dto.postcomment.CommentDeleteDTO;
import com.boxai.model.dto.postfavorite.FavoriteAddDTO;
import com.boxai.model.entity.PostComments;
import com.boxai.service.PostCommentsService;
import com.boxai.service.PostLikesService;
import com.boxai.utils.threadlocal.UserHolder;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
   private PostCommentsService postCommentsService;
    @GetMapping("/list")
    public R getCommentList(@RequestParam("postId") Long postId) {
        List<PostComments> list = PostCommentsService.getCommentList(postId);
        Long total = PostCommentsService.getCommentListTotal(postId);
        Map<String, Object> map = new HashMap<>();
        map.put("commentList", list);
        map.put("total", total);
        return R.ok(map);
    }

    /**
     * 添加评论
     *
     * @param comment
     * @return
     */
    @PostMapping("/add")
    public R addComment(@RequestBody PostComments comment) {
        if (PostCommentsService.save(comment))
            return R.ok("评论成功");
        return R.fail("评论失败");
    }

    /**
     * 删除评论
     *
     * @param comm
     * @return
     */
    @DeleteMapping("/delete")
    public R deleteComment(@RequestBody PostComments comm) {
        if (PostCommentsService.removeComment(comm))
            return R.ok("删除评论成功");
        return R.fail("删除评论失败");
    }
// 添加评论
    @PostMapping("/add")
    public R<Boolean> doComment(@RequestBody CommentAddDTO commentAddDTO) {
        if (commentAddDTO.getPostId() <= 0) {
            throw new CustomizeReturnException(ReturnCode.REQUEST_REQUIRED_PARAMETER_IS_EMPTY);
        }
        if (commentAddDTO.getUserId() <= 0) {
            commentAddDTO.setUserId(UserHolder.getUser().getId());
        }

        return R.ok( postCommentsService.addComment(commentAddDTO));
    }

    // 删除评论
    @PostMapping("/delete")
    public R<Boolean> deleteComment(@RequestBody CommentDeleteDTO commentDeleteDTO) {
        if (commentDeleteDTO.getPostId() <= 0) {
            throw new CustomizeReturnException(ReturnCode.REQUEST_REQUIRED_PARAMETER_IS_EMPTY);
        }
        if (commentDeleteDTO.getUserId() <= 0) {
            commentDeleteDTO.setUserId(UserHolder.getUser().getId());
        }

        return R.ok( postCommentsService.deleteComment(commentDeleteDTO));
    }
}
