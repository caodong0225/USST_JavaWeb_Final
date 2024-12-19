package usst.web.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class Advertisement implements Serializable {
    private Integer adId;
    private String adName;
    private String adDescription; // 广告详细描述
    private String adImageUrl; // JSON 字符串，存储图片URL列表
    private String adUrl; // 广告页面URL
    private String adFeature; // 广告特色或备注
    private String keywords; // JSON 字符串，存储关键词列表
    private Integer adCategoryId; // 广告分类ID
    private Timestamp adStartTime; // 广告开始时间
    private Timestamp adEndTime; // 广告结束时间
    private Byte adStatus; // 广告状态 (例如：1-启用，0-禁用)
    private Integer visitCount; // 访问次数
    private Integer clickCount; // 点击次数
    private Integer conversionCount; // 转化次数
    private Double clickThroughRate; // 点击率
    private Double conversionRate; // 转化率
    private Double revenue; // 广告收益
    private Timestamp lastVisitTime; // 最后访问时间
    private String advertiserId; // 广告主ID
    private String placementLocation; // JSON 字符串，存储投放位置列表
    private String targetAudience; // 目标受众描述
    private Integer impressionCount; // 展示次数
    private Double costPerClick; // 每次点击成本
    private Double costPerConversion; // 每次转化成本
    private String adContent; // 广告的文字内容
    private String adDetailedPageUrl; // 广告详细页面的URL
    private Timestamp createdTime; // 广告创建时间
    private Timestamp updatedTime; // 广告最后更新时间
    private String createdBy; // 广告创建者
    private String updatedBy; // 广告最后更新者
}
