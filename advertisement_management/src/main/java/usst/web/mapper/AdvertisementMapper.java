package usst.web.mapper;
import org.apache.ibatis.annotations.*;
import usst.web.entity.Advertisement;

import java.util.List;

public interface AdvertisementMapper {

    @Insert("INSERT INTO advertisement(ad_id, ad_name, ad_url, ad_image, ad_start_time, ad_end_time, ad_use, ad_feature) VALUES(#{adId}, #{adName}, #{adUrl}, #{adImage}, #{adStartTime}, #{adEndTime}, #{adUse}, #{adFeature})")
    @Options(useGeneratedKeys = true, keyProperty = "adId")
    void insertAdv(Advertisement advertisement);

    @Update("UPDATE advertisement SET ad_name=#{adName}, ad_url=#{adUrl}, ad_image=#{adImage}, ad_start_time=#{adStartTime}, ad_end_time=#{adEndTime}, ad_use=#{adUse}, ad_feature=#{adFeature} WHERE ad_id=#{adId}")
    void updateAdv(Advertisement advertisement);

    @Delete("DELETE FROM advertisement WHERE ad_id=#{adId}")
    void deleteAdv(int adId);

    @Select("SELECT * FROM advertisement WHERE ad_id=#{adId}")
    Advertisement selectAdvById(int adId);

    @Select("SELECT * FROM advertisement")
    List<Advertisement> selectAll();
}
