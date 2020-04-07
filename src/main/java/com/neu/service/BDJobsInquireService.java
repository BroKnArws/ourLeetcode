package com.neu.service;

import com.neu.entity.BDPosition;
import com.neu.mapper.BDJobsInquireMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: treblez
 * @data: 2020-04-06
 **/
@Service
public class BDJobsInquireService {

    @Autowired
    private BDJobsInquireMapper BDjobsInquireMapper;

    public List<BDPosition> findBDPosition() {
        return null;
    }

    public List<BDPosition> findBDPosition(int summary, List<String> city, String q1, String position_type) {

        String category = "";

        if(summary == 873){
            category =  "研发";
        }
        else if(summary == 874){
            category =  "产品";
        }
        else if(summary == 45000){
            category =  "设计";
        }
        else if(summary == 875){
            category =  "市场";
        }
        else if(summary == 45001){
            category =  "销售";
        }
        else if(summary == 876){
            category =  "职能/支持";
        }
        else if(summary == 877){
            category =  "运营";
        }
        else if(summary == 60000){
            category =  "科研教学";
        }
        System.out.println(q1);
        return BDjobsInquireMapper.findBDPosition(category,city,q1,position_type);
    }


}
