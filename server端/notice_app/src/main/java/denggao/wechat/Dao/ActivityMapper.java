package denggao.wechat.Dao;

import denggao.wechat.Beans.ActivityVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ActivityMapper {
//    Integer saveActivity(@Param("coverImgUrl") String coverImgUrl, @Param("activityMarkdown") String activityMarkdown, @Param("activityHtml") String activityHtml, @Param("activityText") String activityText);

    Integer saveActivity(ActivityVo activityVo);

    Integer updateActivity(ActivityVo activityVo);

    List<ActivityVo> getAllActivityMarkdown();

    ActivityVo getActivityMarkdownById(@Param("activityId") Integer activityId);

    Integer ActivityIsShow(@Param("activityId") Integer activityId, @Param("isShowValue") Integer isShowValue);

    Integer ActivityDelete(@Param("activityId") Integer activityId);
}
