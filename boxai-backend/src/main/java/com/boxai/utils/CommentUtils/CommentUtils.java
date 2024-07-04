package com.boxai.utils.CommentUtils;



import com.boxai.model.vo.postcomments.PostCommentsListQueryVO;

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
    public static List<PostCommentsListQueryVO> processComments(List<PostCommentsListQueryVO> list) {
        Map<Long, PostCommentsListQueryVO> map = new HashMap<>();   // (id, Comment)
        List<PostCommentsListQueryVO> result = new ArrayList<>();
        // 将所有根评论加入 map
        for (PostCommentsListQueryVO comment : list) {
            if (comment.getParentId() == 0)
                result.add(comment);
            map.put(comment.getId(), comment);
        }
        // 子评论加入到父评论的 child 中
        for (PostCommentsListQueryVO comment : list) {
            Long id = comment.getParentId();
            if (id != 0) {   // 当前评论为子评论
                PostCommentsListQueryVO p = map.get(id);
                if (p.getChild() == null)    // child 为空，则创建
                    //hashmap是引用传递类型，在这里创建了新的p是保存的map的地址
                    p.setChild(new ArrayList<>());
                p.getChild().add(comment);
            }
        }

        return result;
    }
}