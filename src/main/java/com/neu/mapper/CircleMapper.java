package com.neu.mapper;

import com.neu.dto.response.ArticlePreview;
import com.neu.entity.Article;
import com.neu.entity.LeetcodeLabel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CircleMapper {


    List<Article> selectArticlesByPages(@Param("startPage") Integer startPage,@Param("endPage") Integer endPage);

    String selectTopicByTopicId(Integer topicId);

    List<LeetcodeLabel> selectLabelByLinkId(Integer Articleid);

    List<ArticlePreview> selectArticlePreviewsByPage(String userId, @Param("startPage")Integer startPage,@Param("endPage")Integer endPage);

    List<ArticlePreview> selectArticlePreviewsByAuthorIdAndPage(@Param("authorId")Integer authorId,
                                                                @Param("startPage")Integer startPage,
                                                                @Param("endPage")Integer endPage);

}
