package com.neu.controller;

import com.neu.common.Response;
import com.neu.dto.request.DraftRequest;
import com.neu.entity.Draft;
import com.neu.service.DraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{authorId}/list")
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
    @GetMapping("/{draftId}/get")
    public Response getDraftDetail(@PathVariable Integer draftId){


        Response response = new Response();
        Draft draft = draftService.getDraftDetail(draftId);
        response.setCode(200);
        response.setData(draft);

        return response;

    }


    /**
     * 7
     * 保存草稿
     *
     */
    @PostMapping("/post")
    public Response saveDraft(@RequestBody DraftRequest request){


        //绑定数据
        Draft draft = new Draft(request);


        Response response = new Response();
        Integer draftId = draftService.addOneDraft(draft);
        response.setCode(200);
        response.setData(draftId);

        return response;

    }

    /**
     * 8
     * 更新草稿
     *
     */
    @PutMapping("/put")
    public Response putDraft(@RequestBody DraftRequest request){


        //绑定数据
        Draft draft = new Draft(request);


        Response response = new Response();
        Boolean hasUpdate = draftService.putOneDraft(draft);
        response.setCode(200);
        response.setData(hasUpdate);

        return response;

    }





    /**
     * 9
     * 删除草稿
     *
     */
    @DeleteMapping("/{draftId}/delete")
    public Response deleteDraft(@PathVariable Integer draftId){


        Response response = new Response();
        Boolean hasDelete = draftService.deleteOneDraft(draftId);
        response.setCode(200);
        response.setData(hasDelete);

        return response;

    }





}
