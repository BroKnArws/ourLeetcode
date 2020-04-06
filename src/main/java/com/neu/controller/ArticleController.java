package com.neu.controller;

import com.neu.common.Response;
import com.neu.common.Utils;
import com.neu.dto.request.CreateArticleRequest;
import com.neu.dto.request.EditArticleRequest;
import com.neu.dto.response.ArticleDetail;
import com.neu.dto.response.ArticlePreview;
import com.neu.entity.Article;
import com.neu.exception.BaseException;
import com.neu.exception.UnknownException;
import com.neu.exception.general.FormValidatorException;
import com.neu.exception.general.PermissionDeniedException;
import com.neu.exception.general.ResourceNotExistException;
import com.neu.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.rmi.CORBA.Util;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    ArticleService articleService;


    //1
    //根据topicId获取话题下对应文章列表,若topicId=0,则查询全部topic下文章
    //前端传来 [开始,结束] 下标,然后服务器根据要求开始查询数据库
    //userId从token中获取
    //查询文章表和讨论表,先分页查询10条文章,10条讨论.然后根据 排序算法,对这20条数据进行排序.最后返回给前端
    @GetMapping("/list/{topicId}")
    public Response getAllTopicAll(@RequestParam(defaultValue = "0",required = false) int pageStart,
                                   @RequestParam(defaultValue = "20",required = false) int pageEnd,
                                   @PathVariable Integer topicId){

        List<ArticlePreview> articlePreviews=null;

        if(topicId==0){//查询全部文章
            //首先是获取20条文章预览
            //获取文章列表,需要传入  当前用户id(可为null),开始页码,结束页码
            articlePreviews = articleService.getArticlePreviewList(null,pageStart,pageEnd);
        }else {
            articlePreviews = articleService.getArticlePreviewListByTopicId(null,topicId,pageStart,pageEnd);
        }




        return new Response(0,articlePreviews);

    }









    /**
     * 2
     * 参数:文章id
     */
    //获取文章详情
    @GetMapping("/{articleId}")
    public Response getOneArticle(@PathVariable Integer articleId){

        ArticleDetail articleDetail = articleService.getArticleDetail(articleId);

        return new Response(0,articleDetail);
    }



    /** 3
     * 写文章
     */
    @PostMapping("")
    public Response newArticle(@RequestBody @Valid CreateArticleRequest request,
                               BindingResult bindingResult) throws BaseException {

        if(bindingResult.hasErrors()) {
            throw new FormValidatorException(bindingResult);
        }

        Integer userId=1;

        //数据绑定
        Article article = new Article(request);
        article.setCreatorId(userId);
        //存进数据库的是UTC时间,传给前端时设置时区即可

        Integer newId = articleService.addOneArticle(article);


        return new Response(0,newId);

    }



    /**
     * 4
     * 这个user信息应该从token中获取,暂时设置为从前端获取
     */
    @GetMapping("/MyArticleList/{authorId}")
    public Response getMyArticle(@PathVariable Integer authorId,
                                 @RequestParam(defaultValue = "0",required = false) int pageStart,
                                 @RequestParam(defaultValue = "20",required = false) int pageEnd){


        List<ArticlePreview> articlePreviews = articleService.getMyArticleList(authorId,pageStart,pageEnd);

        return new Response(0,articlePreviews);

    }


    /**
     * 10
     * 修改文章
     *
     */
    @PutMapping("")
    public Response putArticle(@RequestBody @Valid EditArticleRequest editRequest
                                ,BindingResult bindingResult) throws BaseException {

        //1 参数校验发现错误
        if(bindingResult.hasErrors()) {
            throw new FormValidatorException(bindingResult);
        }

        Integer userId = 1;//以后从token中获取

        //2 查一下该id对应文章是否存在
        Article origin = articleService.getById(editRequest.getId());
        if(origin==null){
            throw new ResourceNotExistException("文章");
        }

        //3 鉴权
        if(origin.getCreatorId()!=userId){
            throw new PermissionDeniedException();
        }

        //绑定数据
        Article article = new Article(editRequest);

        //4 向数据库update
        if(!articleService.putOneArticle(article)){
            throw new UnknownException("修改保存失败");
        }

        return new Response(0,"修改成功");

    }




    /**
     * 11
     * 删除文章
     *
     */
    @DeleteMapping("/{articleId}")
    public Response deleteArticle(@PathVariable Integer articleId) throws BaseException {

        Integer userId = 1;//以后从token中获取
        //查询文章是否存在
        Article origin = articleService.getById(articleId);
        if(origin==null){
            throw new ResourceNotExistException("文章");
        }
        //鉴权
        if(origin.getCreatorId()!=userId){
            throw new PermissionDeniedException();
        }

        //向数据库delete
        if(!articleService.deleteOneArticle(articleId)){
            throw new UnknownException("数据库删除失败");
        }

        return new Response(0,"删除成功");
    }














}
