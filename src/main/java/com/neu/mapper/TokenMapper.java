package com.neu.mapper;

import com.neu.entity.Token;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author: treblez
 * @className: TokenMapper
 * @description: 处理token验证
 * @data: 2020-04-05
 **/

@Repository
public interface TokenMapper {
    boolean checkToken(String token);
    Token getTokenById(@Param("userId") long userId);
    Token getToken(@Param("userId") long userId, @Param("client") int client);
    void updateToken(Token currentToken);
    void createToken(Token token);

}
