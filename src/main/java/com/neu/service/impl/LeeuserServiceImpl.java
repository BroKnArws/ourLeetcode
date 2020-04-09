package com.neu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.neu.entity.Leeuser;
import com.neu.entity.vo.RegisterVo;
import com.neu.mapper.LeeuserMapper;
import com.neu.service.LeeuserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neu.utils.ConstantPropertiesUtils;
import com.neu.utils.JwtUtils;
import com.neu.utils.MD5;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 胡若琛
 * @since 2020-04-09
 */
@Service
public class LeeuserServiceImpl extends ServiceImpl<LeeuserMapper, Leeuser> implements LeeuserService {

    @Override
    public Boolean send(Map<String, Object> param, String phone) {
        if (StringUtils.isEmpty(phone)) return false;
        DefaultProfile profile =
                DefaultProfile.getProfile("default", "LTAI4Fx3TirGds9kHrqH75n2", "VFHBX2cJ0EgIfuEVqSNaU0WXiv9Dhw");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "逸盛");
        request.putQueryParameter("TemplateCode", "SMS_187540023");
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return response.getHttpResponse().isSuccess();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String register(RegisterVo registerVo) {

        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();
        String mobile = registerVo.getMobile();

        Leeuser user = new Leeuser();
        user.setNickname(nickname);
        user.setMobile(mobile);
        user.setPassword(MD5.encrypt(password));
        baseMapper.insert(user);

        String jwtToken = JwtUtils.getJwtToken(user.getId(), user.getNickname());
        return jwtToken;
    }

    @Override
    public String uploadFileAvatar(MultipartFile file) {

        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        try {
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            InputStream inputStream = file.getInputStream();
            String filename = UUID.randomUUID().toString().replaceAll("-", "") + file.getOriginalFilename();
            String s = new DateTime().toString("yyyy/MM/dd");
            filename = s + "/" + filename;
            ossClient.putObject(bucketName, filename, inputStream);
            ossClient.shutdown();
            String url = "https://" + bucketName + "." + endpoint + "/" + filename;
            System.out.println(url);
            return url;

        } catch (Exception e) {
            return null;

        }
    }


}
