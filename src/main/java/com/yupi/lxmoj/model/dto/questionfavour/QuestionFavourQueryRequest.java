
package com.yupi.lxmoj.model.dto.questionfavour;


import com.yupi.lxmoj.common.PageRequest;
import com.yupi.lxmoj.model.dto.question.QuestionQueryRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 题目收藏请求
 *
 * @author: LXY
 * @description: TODO
 * @date: 2023/12/20 14:28
 * @return null
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionFavourQueryRequest extends PageRequest implements Serializable {

    /**
     * 题目查询请求
     */
    private QuestionQueryRequest questionQueryRequest;

    /**
     * 用户 id
     */
    private Long userId;

    private static final long serialVersionUID = 1L;
}
