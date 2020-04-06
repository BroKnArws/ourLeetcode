package com.neu.service;

import com.neu.entity.Draft;
import com.neu.mapper.DraftMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DraftService {

    @Autowired
    DraftMapper draftMapper;



    public List<Draft> getDraftList(Integer authorId, int pageStart, int pageEnd) {

        List<Draft> drafts = draftMapper.selectDraftList(authorId, pageStart, pageEnd);

        return drafts;
    }




    public Draft getDraftDetail(Integer draftId) {

        Draft draft = draftMapper.selectDraftDetailById(draftId);

        return draft;
    }


    public Integer addOneDraft(Draft draft) {

        draftMapper.insertOneDraft(draft);
        Integer draftId = draft.getId();
        return draftId;
    }


    public Boolean putOneDraft(Draft draft) {
        Boolean hasUpdate = draftMapper.updateOneDraft(draft);

        return hasUpdate;
    }

    public Boolean deleteOneDraft(Integer draftId) {
        Boolean hasDelete = draftMapper.deleteDraftById(draftId);
        return hasDelete;
    }





}
