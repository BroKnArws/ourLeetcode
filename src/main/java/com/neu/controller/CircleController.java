package com.neu.controller;

import com.neu.common.Response;
import com.neu.dto.request.ArticleRequest;
import com.neu.dto.request.DraftRequest;
import com.neu.dto.response.ArticleDetail;
import com.neu.dto.response.ArticlePreview;
import com.neu.dto.response.OneArticle;
import com.neu.dto.response.QuestionAnswer;
import com.neu.entity.Article;
import com.neu.entity.ArticleComment;
import com.neu.entity.Draft;
import com.neu.service.CircleService;
import com.neu.vo.Reactions;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/circle")
public class CircleController {

    @Autowired
    CircleService circleService;


    @RequestMapping("/hello")
    public String sayHello(){
        return "hello";
    }

    //1
    //前端传来 [开始,结束] 下标,然后服务器根据要求开始查询数据库
    //userId从token中获取
    //查询文章表和讨论表,先分页查询10条文章,10条讨论.然后根据 排序算法,对这20条数据进行排序.最后返回给前端
    @RequestMapping("/{operation}/getPreviews")
    public Response getAllTopicAll(@RequestParam(defaultValue = "0",required = false) int pageStart,
                                   @RequestParam(defaultValue = "20",required = false) int pageEnd,
                                   @PathVariable String operation){

        Response response = new Response();

        //根据operationName,判断查询的是全部,讨论还是文章,还是官方题解

        if(operation.equals("Article")){
            //首先是获取20条文章预览
            //获取文章列表,需要传入  当前用户id(可为null),开始页码,结束页码
            List<ArticlePreview> articlePreviews = circleService.getArticlePreviewList(null,pageStart,pageEnd);
            response.setCode(200);
            response.setData(articlePreviews);
        }


        return response;

    }





    /**
     * 2
     * 参数:文章id
     */
    //获取文章详情,具体返回值见解包
    @RequestMapping("/{articleId}/getArticleDetail")
    public Response getOneArticle(@PathVariable Integer articleId){
        Response response = new Response();


        ArticleDetail articleDetail = circleService.getArticleDetail(articleId);
        response.setCode(200);
        response.setData(articleDetail);

        return response;
    }


    /** 3
     * 写文章
     */
    @RequestMapping("/newArticle")
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

        Integer newId = circleService.addOneArticle(article);

        response.setCode(200);
        response.setData(newId);

        return response;

    }



    /**
     * 4
     * 这个user信息应该从token中获取,暂时设置为从前端获取
     */
    @RequestMapping("/{authorId}/myArticle")
    public Response getMyArticle(@PathVariable Integer authorId,
                                 @RequestParam(defaultValue = "0",required = false) int pageStart,
                                 @RequestParam(defaultValue = "20",required = false) int pageEnd){


        Response response = new Response();
        List<ArticlePreview> articlePreviews = circleService.getMyArticleList(authorId,pageStart,pageEnd);
        response.setCode(200);
        response.setData(articlePreviews);

        return response;

    }





    /**
     * 5
     * 输入用户id
     * 获取草稿列表
     */
    @RequestMapping("/{authorId}/draftList")
    public Response getDraftList(@PathVariable Integer authorId,
                                 @RequestParam(defaultValue = "0",required = false) int pageStart,
                                 @RequestParam(defaultValue = "10",required = false) int pageEnd){


        Response response = new Response();
        List<Draft> drafts = circleService.getDraftList(authorId,pageStart,pageEnd);
        response.setCode(200);
        response.setData(drafts);

        return response;

    }




    /**
     * 6
     * 输入草稿id
     * 获取草稿详情
     */
    @RequestMapping("/{draftId}/draftDetail")
    public Response getDraftDetail(@PathVariable Integer draftId){


        Response response = new Response();
        Draft draft = circleService.getDraftDetail(draftId);
        response.setCode(200);
        response.setData(draft);

        return response;

    }

    /**
     * 7
     * 保存草稿
     *
     */
    @RequestMapping("/saveDraft")
    public Response saveDraft(@RequestBody DraftRequest request){



        //绑定数据
        Draft draft = new Draft(request);


        Response response = new Response();
        Integer draftId = circleService.addOneDraft(draft);
        response.setCode(200);
        response.setData(draftId);

        return response;

    }


    /**
     * 8
     * 更新草稿
     *
     */
    @RequestMapping("/putDraft")
    public Response putDraft(@RequestBody DraftRequest request){


        //绑定数据
        Draft draft = new Draft(request);


        Response response = new Response();
        Boolean hasUpdate = circleService.putOneDraft(draft);
        response.setCode(200);
        response.setData(hasUpdate);

        return response;

    }





    /**
     * 9
     * 删除草稿
     *
     */
    @RequestMapping("/{draftId}/delete")
    public Response deleteDraft(@PathVariable Integer draftId){


        Response response = new Response();
        Boolean hasDelete = circleService.deleteOneDraft(draftId);
        response.setCode(200);
        response.setData(hasDelete);

        return response;

    }

    /**
     * 10
     * 修改文章
     *
     */
    @RequestMapping("/article/put")
    public Response putArticle(@RequestBody Article article){


        Response response = new Response();
        Boolean hasUpdate = circleService.putOneArticle(article);
        response.setCode(200);
        response.setData(hasUpdate);

        return response;

    }

    /**
     * 11
     * 删除文章
     *
     */
    @RequestMapping("/{articleId}/deleteArticle")
    public Response deleteArticle(@PathVariable Integer articleId){


        Response response = new Response();
        Boolean hasDelete = circleService.deleteOneArticle(articleId);
        response.setCode(200);
        response.setData(hasDelete);

        return response;

    }



























}
