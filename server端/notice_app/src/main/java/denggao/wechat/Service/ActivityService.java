package denggao.wechat.Service;

import denggao.wechat.Beans.ActivityVo;
import denggao.wechat.Dao.ActivityMapper;
import denggao.wechat.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    public Integer createActivity(ActivityVo activityVo){
        // 将html转markdown
        System.out.println(activityVo.getActivityMarkdown());
//        activityMapper.saveActivity(activityVo.getCoverImgUrl(),activityVo.getActivityMarkdown(),activityVo.getActivityHtml(),activityVo.getActivityText());
        activityMapper.saveActivity(activityVo);
        System.out.println("getActivityId: "+activityVo.getActivityId());
        return activityVo.getActivityId();
    }

    public boolean updateActivity(ActivityVo activityVo){
        if(activityMapper.updateActivity(activityVo) > 0){
            System.out.println("更新成功");
            return true;
        }
        return false;
    }

    public List<ActivityVo> getAllActivityMarkdown(){

        /*
        List<ActivityVo> activityMarkdownResultList = new ArrayList<>();
        List<ActivityVo> activityMarkdownList = activityMapper.getAllActivityMarkdown();
        for(ActivityVo activityVo:activityMarkdownList){
            if(activityVo.getIsShow() == 1){
                String imgName = activityVo.getCoverImgUrl();
                String url = Config.ServerUrl;
                activityVo.setCoverImgUrl(url+imgName);
                activityMarkdownResultList.add(activityVo);
            }
        }
        return activityMarkdownList;
         */
        List<ActivityVo> activityMarkdownList = new ArrayList<>();
        activityMarkdownList = activityMapper.getAllActivityMarkdown();
        for(ActivityVo activityVo:activityMarkdownList){
            String imgName = activityVo.getCoverImgUrl();
            String url = Config.ServerUrl;
            activityVo.setCoverImgUrl(url+imgName);
        }
        return activityMarkdownList;
    }

    public Object getActivityMarkdownById(Integer activityId){
        ActivityVo activityVo = activityMapper.getActivityMarkdownById(activityId);
        String imgName = activityVo.getCoverImgUrl();
        String url = Config.ServerUrl;
        activityVo.setCoverImgUrl(url+imgName);
        return activityVo;
    }

    public boolean ActivityIsShow(Integer activityId, Integer isShowValue){

        if(activityMapper.ActivityIsShow(activityId, isShowValue==0?1:0) >= 0){
            return true;
        }
        return false;
    }

    public boolean ActivityDelete(Integer activityId){

        if(activityMapper.ActivityDelete(activityId) >= 0){
            return true;
        }
        return false;
    }
}
