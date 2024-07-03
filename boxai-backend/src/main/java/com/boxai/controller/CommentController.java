package com.boxai.controller;

import com.boxai.common.base.R;
import com.boxai.common.base.ReturnCode;
import com.boxai.exception.customize.CustomizeReturnException;
import com.boxai.model.dto.postcomment.CommentAddDTO;
import com.boxai.model.dto.postcomment.CommentDeleteDTO;
import com.boxai.model.dto.postfavorite.FavoriteAddDTO;
import com.boxai.service.PostCommentsService;
import com.boxai.service.PostLikesService;
import com.boxai.utils.threadlocal.UserHolder;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
   private PostCommentsService postCommentsService;
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
