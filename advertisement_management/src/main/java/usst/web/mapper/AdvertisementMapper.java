package usst.web.mapper;
import org.apache.ibatis.annotations.*;
import usst.web.entity.Advertisement;

import java.util.List;

public interface AdvertisementMapper {

    @Insert("INSERT INTO advertisement(ad_name, ad_description, ad_image_url, ad_url, ad_feature, ad_start_time, ad_end_time, ad_status, advertiser_id, placement_location, target_audience, keywords, ad_category_id, ad_content, ad_detailed_page_url, created_by, updated_by) VALUES(#{adName}, #{adDescription}, #{adImageUrl}, #{adUrl}, #{adFeature}, #{adStartTime}, #{adEndTime}, #{adStatus}, #{advertiserId}, #{placementLocation}, #{targetAudience}, #{keywords}, #{adCategoryId}, #{adContent}, #{adDetailedPageUrl}, #{createdBy}, #{updatedBy})")
    @Options(useGeneratedKeys = true, keyProperty = "adId")
    void insertAdv(Advertisement advertisement);

    @Update("UPDATE advertisement SET ad_name=#{adName}, ad_description=#{adDescription}, ad_image_url=#{adImageUrl}, ad_url=#{adUrl}, ad_feature=#{adFeature}, ad_start_time=#{adStartTime}, ad_end_time=#{adEndTime}, ad_status=#{adStatus}, advertiser_id=#{advertiserId}, placement_location=#{placementLocation}, target_audience=#{targetAudience}, keywords=#{keywords}, ad_category_id=#{adCategoryId}, ad_content=#{adContent}, ad_detailed_page_url=#{adDetailedPageUrl}, updated_by=#{updatedBy} WHERE ad_id=#{adId}")
    void updateAdv(Advertisement advertisement);

    @Delete("DELETE FROM advertisement WHERE ad_id=#{adId}")
    void deleteAdv(int adId);

    @Select("SELECT * FROM advertisement WHERE ad_id=#{adId}")
    Advertisement selectAdvById(int adId);

    @Select("SELECT * FROM advertisement")
    List<Advertisement> selectAll();
}
