package com.seu.universe.mapper;

import com.seu.universe.entity.Like;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeMapper {

    /**
     * 添加点赞
     * @param like
     * @return
     */
    int addLike(Like like);

    /**
     * 获取点赞信息
     * @param messageId
     * @param userId
     * @return
     */
    Like getLike(@Param("messageId") long messageId,
                 @Param("userId") long userId);

    /**
     * 取消点赞
     * @param messageId
     * @param userId
     * @return
     */
    int cancelLike(@Param("messageId") long messageId,
                   @Param("userId") long userId);
}
