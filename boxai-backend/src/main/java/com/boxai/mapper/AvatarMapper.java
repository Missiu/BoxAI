package com.boxai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boxai.model.entity.Avatar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AvatarMapper extends BaseMapper<Avatar>{
    @Select("SELECT * FROM Avatar WHERE id = #{id}")
    Avatar getAvatarById(int id);
    void insertAvatar(Avatar avatar);
}