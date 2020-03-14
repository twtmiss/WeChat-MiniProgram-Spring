package denggao.wechat.Controller;

import denggao.wechat.Beans.ActivityVo;
import denggao.wechat.Service.ActivityService;
import denggao.wechat.util.toResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
 描述: 获取activity
 */
@RestController
@RequestMapping("/activity")
public class ActivityController extends toResponse{
    private static final Logger logger = LoggerFactory.getLogger(ActivityController.class);
    private toResponse toResponse = new toResponse();

    @Autowired
    private ActivityService activityService;
    /*
        获取活动内容
     */
    @GetMapping("/GetActivity")
    public Object getAllActivity (){
        logger.info("执行getAllActivity");
        return toResponse.toResponsMsgSuccess("查询成功", activityService.getAllActivityMarkdown());
    }

    @GetMapping("/GetActivityById")
    public Object getActivityById (Integer activityId){
        return toResponse.toResponsMsgSuccess("查询成功", activityService.getActivityMarkdownById(activityId));
    }

    @PostMapping("/PostCreateActivity")
    public Object createActivity (@RequestBody ActivityVo activityVo){
        if(activityVo.getCoverImgUrl() == null || activityVo.getCoverImgUrl().equals("")){
            return toResponse.toResponsFail("封面图片为空");
        }
//        System.out.println("Html: "+activityVo.getActivityHtml());
//        System.out.println("Text: "+activityVo.getActivityText());
        if(activityVo.getCreateType().equals("create")){
            activityVo.setIsShow(0);

            // 保存到数据库
            int id = activityService.createActivity(activityVo);

            return toResponse.toResponsMsgSuccess("提交成功", id);
        }
        if(activityVo.getCreateType().equals("edit")){
            // 保存到数据库
//            int id = activityService.updateActivity(activityVo);
            if(activityService.updateActivity(activityVo)){
                return toResponse.toResponsMsgSuccess("提交成功", "id");
            }
            return toResponse.toResponsFail("更新失败");
        }

        return toResponse.toResponsFail("提交失败");
    }

    @PostMapping("/PostActivityByIdForIsShow")
    public Object ActivityIsShow (Integer activityId, Integer isShowValue){

        if(activityService.ActivityIsShow(activityId, isShowValue)){
            return toResponse.toResponsSuccess("提交成功" );
        }

        return toResponse.toResponsFail("提交失败");
    }

    @PostMapping("/PostActivityByIdForDelete")
    public Object ActivityDelete (Integer activityId){

        if(activityService.ActivityDelete(activityId)){
            return toResponse.toResponsSuccess("提交成功" );
        }

        return toResponse.toResponsFail("提交失败");
    }



    /*
    response:{
      "activity":[
        {
          "style":{
            img_style:{
              height:"80%"
            }
          },
          "imgsrc":"/static/testimg/t1.png",
          "nodes": [
            {
              name: 'p',
              attrs: {
                class: 'div_class',
                style: 'margin-top:20px;line-height: 30px; color: #9c9c9f;font-size:16px;letter-spacing:1.5px;'
              },
              children: [{
                type: 'text',
                text: '&nbsp;&nbsp;&nbsp;日照登高网络科技有限公司是一家专业从事网络游戏开发与运营的创新型科技公司。公司团队成员有着6年以上的游戏行业从业经验，专注于游戏的研发及运营。'
              }]
            },
            {
              name: 'img',
              attrs: {
                class: 'div_class',
                style: 'border-radius: 10px;margin-top:20px;width:100%;margin-top:20px;',
                src: 'http://192.168.10.115:8000/images/activity/chrome_lGdwADg9M0.png'
              }
            },
            {
              name: 'p',
              attrs: {
                class: 'div_class',
                style: 'margin-top:20px;line-height: 30px; color: #9c9c9f;font-size:16px;letter-spacing:1.5px;'
              },
              children: [{
                type: 'text',
                text: '自成立以来，团队坚持共同的游戏梦想，致力于创作真正好玩的游戏。经过多年的运营积累，逐步形成了游戏研发、发行一体化的业务运营模式，与主流渠道和平台建立了合作关系，拥有了丰富的市场资源。在游戏市场稳步前进的同时，我们也在逐步开拓信息化管理服务业务领域，多元化发展。创新突破、原创精品的产品理念，激情付出，利益共享的团队机制，使我们汇聚富有才情和创意的团队成员。成长路上，我们一起品尝着酸甜苦辣，一起克服了一个又一个困难，一起在这种创造中微笑前行。'
              }]
            }],
        },
        {
          "style": {
            img_style: {
              height: "100%"
            }
          },
          "imgsrc": "/static/testimg/t2.png",
          "nodes": [
            {
              name: 'p',
              attrs: {
                class: 'div_class',
                style: 'margin-top:20px;line-height: 30px; color: #9c9c9f;font-size:16px;letter-spacing:1.5px;'
              },
              children: [{
                type: 'text',
                text: '&nbsp;&nbsp;&nbsp;日照登高网络科技有限公司是一家专业从事网络游戏开发与运营的创新型科技公司。公司团队成员有着6年以上的游戏行业从业经验，专注于游戏的研发及运营。自成立以来，团队坚持共同的游戏梦想，致力于创作真正好玩的游戏。经过多年的运营积累，逐步形成了游戏研发、发行一体化的业务运营模式，与主流渠道和平台建立了合作关系，拥有了丰富的市场资源。在游戏市场稳步前进的同时，我们也在逐步开拓信息化管理服务业务领域，多元化发展。创新突破、原创精品的产品理念，激情付出，利益共享的团队机制，使我们汇聚富有才情和创意的团队成员。成长路上，我们一起品尝着酸甜苦辣，一起克服了一个又一个困难，一起在这种创造中微笑前行。'
              }]
            }],
        },
        {
          "style": {
            img_style: {
              height: "100%"
            }
          },
          "imgsrc": "/static/testimg/t3.png",
          "nodes": [
            {
              name: 'p',
              attrs: {
                class: 'div_class',
                style: 'margin-top:20px;line-height: 30px; color: #9c9c9f;font-size:16px;letter-spacing:1.5px;'
              },
              children: [{
                type: 'text',
                text: '&nbsp;&nbsp;&nbsp;日照登高网络科技有限公司是一家专业从事网络游戏开发与运营的创新型科技公司。公司团队成员有着6年以上的游戏行业从业经验，专注于游戏的研发及运营。自成立以来，团队坚持共同的游戏梦想，致力于创作真正好玩的游戏。经过多年的运营积累，逐步形成了游戏研发、发行一体化的业务运营模式，与主流渠道和平台建立了合作关系，拥有了丰富的市场资源。在游戏市场稳步前进的同时，我们也在逐步开拓信息化管理服务业务领域，多元化发展。创新突破、原创精品的产品理念，激情付出，利益共享的团队机制，使我们汇聚富有才情和创意的团队成员。成长路上，我们一起品尝着酸甜苦辣，一起克服了一个又一个困难，一起在这种创造中微笑前行。'
              }]
            }],
        },
        {
          "style": {
            img_style: {
              height: "100%"
            }
          },
          "imgsrc": "/static/testimg/t4.png",
          "nodes": [
            {
              name: 'p',
              attrs: {
                class: 'div_class',
                style: 'margin-top:20px;line-height: 30px; color: #9c9c9f;font-size:16px;letter-spacing:1.5px;'
              },
              children: [{
                type: 'text',
                text: '&nbsp;&nbsp;&nbsp;日照登高网络科技有限公司是一家专业从事网络游戏开发与运营的创新型科技公司。公司团队成员有着6年以上的游戏行业从业经验，专注于游戏的研发及运营。自成立以来，团队坚持共同的游戏梦想，致力于创作真正好玩的游戏。经过多年的运营积累，逐步形成了游戏研发、发行一体化的业务运营模式，与主流渠道和平台建立了合作关系，拥有了丰富的市场资源。在游戏市场稳步前进的同时，我们也在逐步开拓信息化管理服务业务领域，多元化发展。创新突破、原创精品的产品理念，激情付出，利益共享的团队机制，使我们汇聚富有才情和创意的团队成员。成长路上，我们一起品尝着酸甜苦辣，一起克服了一个又一个困难，一起在这种创造中微笑前行。'
              }]
            }],
        }
      ]
    }
     */
}
