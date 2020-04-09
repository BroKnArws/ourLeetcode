package com.neu.service;

import com.neu.entity.Leeuser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.neu.entity.vo.RegisterVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 胡若琛
 * @since 2020-04-09
 */
public interface LeeuserService extends IService<Leeuser> {

    Boolean send(Map<String, Object> param, String phone);

    String register(RegisterVo registerVo);

    String uploadFileAvatar(MultipartFile file);
}
