package com.neu.controller;

import com.neu.common.Response;
import com.neu.entity.BDPosition;
import com.neu.exception.BaseException;
import com.neu.exception.general.NoResultException;
import com.neu.service.BDJobsInquireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: treblez
 * @className: JobsController
 * @description:Bytedance请求
 * @data: 2020-04-06
 **/

@RestController
@RequestMapping("/campus")
public class BDJobsInquireController {
    @Autowired
    BDJobsInquireService BDjobsInquireService;
    /**
     *
     * @param summary 职位类别
     * @param city 城市
     * @param q1 模糊查询
     * @param position_type 职位类型
     * @throws BaseException
     */

    @GetMapping("/position")
    public Response getPositionInf(@RequestParam(defaultValue = "0",required = false,name = "summary")int summary,
                                   @RequestParam(defaultValue = "",required = false,name = "city")List<String> city,
                                   @RequestParam(defaultValue = "",required = false,name = "q1")String q1,
                                   @RequestParam(defaultValue = "",required = false,name = "position_type")String position_type)
                                    throws NoResultException {

        List<BDPosition> BDPosition = BDjobsInquireService.findBDPosition(summary,city,q1,position_type);

        if(BDPosition == null){
                throw new NoResultException();
            }
        return new Response(0,BDPosition);
    }


}

