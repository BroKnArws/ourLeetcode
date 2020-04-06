package com.neu.mapper;

import com.neu.entity.Draft;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DraftMapper {
    List<Draft> selectDraftList(@Param("authorId") Integer authorId, @Param("startPage")int startPage,@Param("endPage") int endPage);

    Draft selectDraftDetailById(Integer draftId);

    void insertOneDraft(Draft draft);

    Boolean updateOneDraft(Draft draft);

    Boolean deleteDraftById(Integer draftId);
}
