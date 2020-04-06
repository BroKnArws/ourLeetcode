package com.neu.controller;

import com.neu.common.Response;
import com.neu.dto.request.CreateDraftRequest;
import com.neu.dto.request.EditDraftRequest;
import com.neu.entity.Draft;
import com.neu.exception.BaseException;
import com.neu.exception.UnknownException;
import com.neu.exception.general.FormValidatorException;
import com.neu.exception.general.PermissionDeniedException;
import com.neu.exception.general.ResourceNotExistException;
import com.neu.service.DraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("draft")
public class DraftController {

    @Autowired
    DraftService draftService;


    /**
     * 5
     * 输入用户id
     * 获取草稿列表
     */
    @GetMapping("/list/{authorId}")
    public Response getDraftList(@PathVariable Integer authorId,
                                 @RequestParam(defaultValue = "0",required = false) int pageStart,
                                 @RequestParam(defaultValue = "10",required = false) int pageEnd){


        Response response = new Response();
        List<Draft> drafts = draftService.getDraftList(authorId,pageStart,pageEnd);
        response.setCode(200);
        response.setData(drafts);

        return response;

    }






    /**
     * 6
     * 输入草稿id
     * 获取草稿详情
     */
    @GetMapping("/{draftId}")
    public Response getDraftDetail(@PathVariable Integer draftId){

        Draft draft = draftService.getDraftDetail(draftId);

        return new Response(0,draft);

    }


    /**
     * 7
     * 保存草稿
     *
     */
    @PostMapping("")
    public Response saveDraft(@RequestBody @Valid CreateDraftRequest request
                                ,BindingResult bindingResult) throws BaseException {
        if(bindingResult.hasErrors()) {
            throw new FormValidatorException(bindingResult);
        }

        Integer userId=1;

        //绑定数据
        Draft draft = new Draft(request);
        draft.setAuthorId(userId);

        Integer draftId = draftService.addOneDraft(draft);

        return new Response(0,draftId);

    }

    /**
     * 8
     * 修改草稿
     *
     */
    @PutMapping("")
    public Response putDraft(@RequestBody @Valid EditDraftRequest request
                            ,BindingResult bindingResult) throws BaseException {

        if(bindingResult.hasErrors()) {
            throw new FormValidatorException(bindingResult);
        }

        Integer userId = 1;

        Draft origin = draftService.getById(request.getId());
        if(origin==null){
            throw new ResourceNotExistException("文章");
        }

        if(origin.getAuthorId()!=userId){
            throw new PermissionDeniedException();
        }

        //绑定数据
        Draft draft = new Draft(request);

        if(!draftService.putOneDraft(draft)){
            throw new UnknownException("修改保存失败");
        }


        return new Response(0,"修改成功");

    }





    /**
     * 9
     * 删除草稿
     *
     */
    @DeleteMapping("/{draftId}")
    public Response deleteDraft(@PathVariable Integer draftId) throws BaseException {

        int userId = 1;

        Draft origin = draftService.getById(draftId);
        if (origin == null) {
            throw new ResourceNotExistException("讨论");
        }
        if(origin.getAuthorId() != userId) {
            throw new PermissionDeniedException();
        }
        if(!draftService.deleteOneDraft(draftId)) {
            throw new UnknownException("数据库删除失败");
        }

        return new Response(0,"删除成功");

    }





}
