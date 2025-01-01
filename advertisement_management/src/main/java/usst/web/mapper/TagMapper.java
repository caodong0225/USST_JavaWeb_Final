package usst.web.mapper;

import org.apache.ibatis.annotations.Select;
import usst.web.entity.Advertisement;

import java.util.List;

/**
 * @author jyzxc
 * @since 2025-1-1
 */
public interface TagMapper {
    @Select("select * from advertisement")
    List<Advertisement> getRecommendation();
}
