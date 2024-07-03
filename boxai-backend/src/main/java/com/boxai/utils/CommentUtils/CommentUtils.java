package com.boxai.utils.CommentUtils;



import com.boxai.model.entity.PostComments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentUtils {

    /**
     * 构建评论树
     *
     * @param list
     * @return
     */
    public static List<PostComments> processComments(List<PostComments> list) {
        Map<Long, PostComments> map = new HashMap<>();   // (id, Comment)
        List<PostComments> result = new ArrayList<>();
        // 将所有根评论加入 map
        for (PostComments comment : list) {
            if (comment.getParentId() == null)
                result.add(comment);
            map.put(comment.getId(), comment);
        }
        // 子评论加入到父评论的 child 中
        for (PostComments comment : list) {
            Long id = comment.getParentId();
            if (id != null) {   // 当前评论为子评论
                PostComments p = map.get(id);
                if (p.getChild() == null)    // child 为空，则创建
                    p.setChild(new ArrayList<>());
                p.getChild().add(comment);
            }
        }
        return result;
    }
}