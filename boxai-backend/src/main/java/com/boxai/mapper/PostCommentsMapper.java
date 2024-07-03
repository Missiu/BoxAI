package com.boxai.mapper;

import com.boxai.model.entity.PostComments;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boxai.model.entity.PostFavorites;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author Hzh
* @description 针对表【post_comments(帖子评论信息表)】的数据库操作Mapper
* @createDate 2024-05-13 19:42:53
* @Entity generator.entity.PostComments
*/
public interface PostCommentsMapper extends BaseMapper<PostComments> {
    PostFavorites selectByPostIdAndUserIdAndParentId (Long postId, Long userId, Long parentId);
    /**
     * 根据商品ID获取评论列表
     *
     * @param postId
     * @return
     */


   List<PostComments> getCommentList(Long postId);

    /**
     * 获取评论列表总数
     *
     * @param postId
     * @return
     */
   Long getCommentListTotal(Long postId);

    /**
     * 添加评论
     *
     * @param comment
     * @return
     */
  int save(PostComments comment);

    /**
     * 根据评论ID删除评论
     *
     * @param id
     * @return
     */

    int removeById(Long id);
}




