package com.boxai.mapper;

import com.boxai.model.entity.PostComments;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boxai.model.entity.PostFavorites;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

import java.util.List;

/**
* @author Hzh
* @description 针对表【post_comments(帖子评论信息表)】的数据库操作Mapper
* @createDate 2024-05-13 19:42:53
* @Entity generator.entity.PostComments
*/
public interface PostCommentsMapper extends BaseMapper<PostComments> {
    List<Long> selectByPostIdAndUserIdAndParentId (Long postId, Long userId, Long Id);
    public int deleteByIds(List<Long> commentIds);

}




