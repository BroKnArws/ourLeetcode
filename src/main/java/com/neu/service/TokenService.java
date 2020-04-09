//package com.neu.service;
//
//import com.neu.common.Utils;
//import com.neu.entity.Token;
//import com.neu.mapper.TokenMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.*;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.concurrent.TimeUnit;
//
////import org.springframework.data.redis.core.RedisTemplate;
//
///**
// * @author: treblez
// * @className: TokenService
// * @description: 处理token验证
// * @data: 2020-04-07
// **/
//@Service
//public class TokenService {
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//    @Autowired
//    private RedisTemplate redisTemplate;
//    @Resource
//    private ValueOperations<Long,Object> valueOperations;
//    @Autowired
//    private RedisService redisService;
//    @Autowired
//    private TokenMapper tokenMapper;
//
//    public static final int TOKEN_EXPIRE_TIME =500;
//    /**
//     * 创建/寻找token
//     * @param userId,client,ip
//     * @return String
//     */
//    public String findToken(Long userId,int client, String ip){
////      id=>token 键值对映射
//        ValueOperations<String, Token> operations = redisTemplate.opsForValue();
////      redisService.expireKey("name",20, TimeUnit.SECONDS);
////      redis拿token,redis没法存long，所以String.valueOf转化存储
//        Token currentToken = operations.get(String.valueOf(userId));
//
//        if(currentToken == null){
//            currentToken = tokenMapper.getToken(userId, client);
//        }
////        System.out.println(currentToken.getToken());
//        String newToken = Utils.getUUID();
//        long currentTime = Utils.createTimestamp();
//
////      数据库拿token
//
//        if(currentToken != null){
//            currentToken.setLoginTime(currentTime);
//            currentToken.setExpirationTime(currentTime + TOKEN_EXPIRE_TIME);
//            currentToken.setToken(newToken);
//            operations.getAndSet(String.valueOf(userId),currentToken);
////      缓存和数据库更新token
//            redisService.expireKey(String.valueOf(userId),500, TimeUnit.SECONDS);
//            tokenMapper.updateToken(currentToken);
//            return currentToken.getToken();
//        }else{
//            Token token = new Token();
//
//            token.setToken(newToken);
//            token.setExpirationTime(currentTime + TOKEN_EXPIRE_TIME);
//            token.setToken(newToken);
//            token.setuId(userId);
//            token.setClient(client);
//            token.setIp(ip);
//            token.setLoginTime(currentTime);
//            operations.set( String.valueOf(userId),token);
//            tokenMapper.createToken(token);
//            return token.getToken();
//        }
//
//}
//    /**
//     * 校验token
//     * @param userId,token
//     * @return boolean
//     */
//    public boolean checkToken(long userId, String token){
//        ValueOperations<String, Token> operations = redisTemplate.opsForValue();
////      redisService.expireKey("name",20, TimeUnit.SECONDS);
////      redis拿token
//        Token oldToken = operations.get(String.valueOf(userId));
////      数据库拿token
//        if(oldToken == null){
//            oldToken = tokenMapper.getTokenById(userId);
//        }
//        if(oldToken.getToken().equals(token)){
//            long currentTime = Utils.createTimestamp();
//            if (oldToken.getExpirationTime() >= currentTime){
//                return true;
//            }
//        }
//        return false;
//    }
//}
