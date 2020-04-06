package com.neu.controller;

import com.neu.common.Response;
import com.neu.dto.request.ArticleRequest;
import com.neu.dto.response.ArticleDetail;
import com.neu.dto.response.ArticlePreview;
import com.neu.entity.Article;
import com.neu.exception.UnknownException;
import com.neu.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{topicId}/getArticleList")
    public Response getAllTopicAll(@RequestParam(defaultValue = "0",required = false) int pageStart,
                                   @RequestParam(defaultValue = "20",required = false) int pageEnd,
                                   @PathVariable Integer topicId){

        Response response = new Response();

        //根据operationName,判断查询的是全部,讨论还是文章,还是官方题解

        if(topicId==0){//查询全部文章
            //首先是获取20条文章预览
            //获取文章列表,需要传入  当前用户id(可为null),开始页码,结束页码
            List<ArticlePreview> articlePreviews = articleService.getArticlePreviewList(null,pageStart,pageEnd);
            response.setCode(200);
            response.setData(articlePreviews);
        }else {
            List<ArticlePreview> articlePreviews = articleService.getArticlePreviewListByTopicId(null,topicId,pageStart,pageEnd);
            response.setCode(200);
            response.setData(articlePreviews);
        }




        return response;

    }









    /**
     * 2
     * 参数:文章id
     */
    //获取文章详情
    @GetMapping("/{articleId}/get")
    public Response getOneArticle(@PathVariable Integer articleId){
        Response response = new Response();


        ArticleDetail articleDetail = articleService.getArticleDetail(articleId);
        response.setCode(200);
        response.setData(articleDetail);

        return response;
    }



    /** 3
     * 写文章
     */
    @PostMapping("/post")
    public Response newArticle(@RequestBody ArticleRequest request){

        Response response = new Response();

        Article article = new Article();
        article.setCreatorId(request.getCreatorId());
        article.setTopicId(request.getTopicId());
        article.setThumbnail(request.getThumbnail());
        article.setTitle(request.getTitle());
        article.setMessage(request.getMessage());
        article.setSummary(request.getSummary());
        article.setInitializeTime(request.getInitializeTime());

        Integer newId = articleService.addOneArticle(article);

        response.setCode(200);
        response.setData(newId);

        return response;

    }



    /**
     * 4
     * 这个user信息应该从token中获取,暂时设置为从前端获取
     */
    @GetMapping("/{authorId}/getMyArticleList")
    public Response getMyArticle(@PathVariable Integer authorId,
                                 @RequestParam(defaultValue = "0",required = false) int pageStart,
                                 @RequestParam(defaultValue = "20",required = false) int pageEnd){


        Response response = new Response();
        List<ArticlePreview> articlePreviews = articleService.getMyArticleList(authorId,pageStart,pageEnd);
        response.setCode(200);
        response.setData(articlePreviews);

        return response;

    }


    /**
     * 10
     * 修改文章
     *
     */
    @PutMapping("/put")
    public Response putArticle(@RequestBody Article article){


        Response response = new Response();
        Boolean hasUpdate = articleService.putOneArticle(article);
        response.setCode(200);
        response.setData(hasUpdate);

        return response;

    }

    /**
     * 11
     * 删除文章
     *
     */
    @DeleteMapping("/{articleId}/delete")
    public Response deleteArticle(@PathVariable Integer articleId) throws UnknownException {


        Response response = new Response();
        Boolean hasDelete = articleService.deleteOneArticle(articleId);

        if(hasDelete!=true) {
            throw new UnknownException("修改保存失败");
        }

        response.setCode(200);
        response.setData(hasDelete);

        return response;

    }














}
