package denggao.wechat.Beans;

import javax.naming.event.ObjectChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityVo {
    private Integer activityId;
    private String activityText;
    private String activityHtml;
    private String coverImgUrl;
    private String activityMarkdown;
    private Integer isShow;
    private String createType;

    public String getCreateType() {
        return createType;
    }

    public void setCreateType(String createType) {
        this.createType = createType;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityMarkdown() {
        return activityMarkdown;
    }

    public void setActivityMarkdown(String activityMarkdown) {
        this.activityMarkdown = activityMarkdown;
    }

    public String getActivityText() {
        return activityText;
    }

    public void setActivityText(String activityText) {
        this.activityText = activityText;
    }

    public String getActivityHtml() {
        return activityHtml;
    }

    public void setActivityHtml(String activityHtml) {
        this.activityHtml = activityHtml;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    //    private Map<String, Object> coverStyleMap = new HashMap<>();
//    private HashMap<String, Object> activityJson = new HashMap<>();
//    private String coverImgUrl;
//    private ArrayList<Object> richTextNodes = new ArrayList<>();
//
//
//    public Map<String, Object> getCoverStyleMap() {
//        return coverStyleMap;
//    }
//
//    public void setCoverStyleMap(Map<String, Object> coverStyleMap) {
//        this.coverStyleMap = coverStyleMap;
//    }
//    public void putCoverStyleMap(String key, Object value) {
//        this.coverStyleMap.put(key, value);
//    }
//
//    public HashMap<String, Object> getActivityJson() {
//        return activityJson;
//    }
//
//    public void setSelfActivityJson() {
//        this.activityJson.put("coverStyle", this.coverStyleMap);
//        this.activityJson.put("coverImgUrl", this.coverImgUrl);
//        this.activityJson.put("richTextNodes", this.richTextNodes);
//    }
//
//    public String getCoverImgUrl() {
//        return coverImgUrl;
//    }
//
//    public void setCoverImgUrl(String coverImgUrl) {
//        this.coverImgUrl = coverImgUrl;
//    }
//
//    public ArrayList<Object> getRichTextNodes() {
//        return richTextNodes;
//    }
//
//    public void setRichTextNodes(ArrayList<Object> richTextNodes) {
//        this.richTextNodes = richTextNodes;
//    }
//    public void addRichTextNodes(Object value) {
//        this.richTextNodes.add(value);
//    }

/*
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
        }
     */
}
