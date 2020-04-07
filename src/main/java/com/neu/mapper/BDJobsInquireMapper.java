package com.neu.mapper;

import com.neu.entity.BDPosition;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: treblez
 * @data: 2020-04-06
 **/
@Repository
public interface BDJobsInquireMapper {

    List<BDPosition> findBDPosition(@Param("summary") String summary,
                                    @Param("city") List<String> city,
                                    @Param("q1") String q1,
                                    @Param("position_type") String position_type);
}
