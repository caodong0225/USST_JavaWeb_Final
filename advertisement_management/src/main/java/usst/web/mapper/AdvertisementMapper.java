package usst.web.mapper;
import org.apache.ibatis.annotations.*;
import usst.web.entity.Advertisement;

import java.util.List;

public interface AdvertisementMapper {

    @Insert("INSERT INTO advertisement(" +
            "ad_name, article_id, ad_description, ad_image_url, ad_url, ad_feature, keywords, ad_category_id, " +
            "ad_start_time, ad_end_time, ad_status, visit_count, click_count, conversion_count, " +
            "click_through_rate, conversion_rate, revenue, advertiser_id, placement_location, " +
            "target_audience, impression_count, cost_per_click, cost_per_conversion, ad_content, " +
            "ad_detailed_page_url, created_by, updated_by" +
            ") VALUES(" +
            "#{adName}, #{articleId}, #{adDescription}, #{adImageUrl}, #{adUrl}, #{adFeature}, #{keywords}, #{adCategoryId}, " +
            "#{adStartTime}, #{adEndTime}, #{adStatus}, #{visitCount}, #{clickCount}, #{conversionCount}, " +
            "#{clickThroughRate}, #{conversionRate}, #{revenue}, #{advertiserId}, #{placementLocation}, " +
            "#{targetAudience}, #{impressionCount}, #{costPerClick}, #{costPerConversion}, #{adContent}, " +
            "#{adDetailedPageUrl}, #{createdBy}, #{updatedBy}" +
            ")")
    @Options(useGeneratedKeys = true, keyProperty = "adId")
    void insertAdv(Advertisement advertisement);

    @Update("UPDATE advertisement SET " +
            "ad_name=#{adName}, " +
            "article_id=#{articleId}, " +
            "ad_description=#{adDescription}, " +
            "ad_image_url=#{adImageUrl}, " +
            "ad_url=#{adUrl}, " +
            "ad_feature=#{adFeature}, " +
            "keywords=#{keywords}, " +
            "ad_category_id=#{adCategoryId}, " +
            "ad_start_time=#{adStartTime}, " +
            "ad_end_time=#{adEndTime}, " +
            "ad_status=#{adStatus}, " +
            "visit_count=#{visitCount}, " +
            "click_count=#{clickCount}, " +
            "conversion_count=#{conversionCount}, " +
            "click_through_rate=#{clickThroughRate}, " +
            "conversion_rate=#{conversionRate}, " +
            "revenue=#{revenue}, " +
            "advertiser_id=#{advertiserId}, " +
            "placement_location=#{placementLocation}, " +
            "target_audience=#{targetAudience}, " +
            "impression_count=#{impressionCount}, " +
            "cost_per_click=#{costPerClick}, " +
            "cost_per_conversion=#{costPerConversion}, " +
            "ad_content=#{adContent}, " +
            "ad_detailed_page_url=#{adDetailedPageUrl}, " +
            "updated_by=#{updatedBy} " +
            "WHERE ad_id=#{adId}")
    void updateAdv(Advertisement advertisement);

    @Delete("DELETE FROM advertisement WHERE ad_id=#{adId}")
    void deleteAdv(int adId);

    @Select("SELECT * FROM advertisement WHERE ad_id=#{adId}")
    Advertisement selectAdvById(int adId);

    @Select("SELECT * FROM advertisement WHERE article_id=#{articleId}")
    Advertisement selectAdvByArticleId(int articleId);

    @Select("SELECT * FROM advertisement")
    List<Advertisement> selectAll();

    @Select("SELECT * FROM advertisement WHERE advertiser_id=#{advertiserId}")
    List<Advertisement> selectAllByAdvertiserId(int advertiserId);
}
