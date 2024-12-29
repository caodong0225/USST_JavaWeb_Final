package usst.web.mapper;

import org.apache.ibatis.annotations.Select;
import usst.web.entity.Advertisement;

import java.util.List;

/**
 * @author jyzxc
 * @since 2024-12-27
 */
public interface StatisticsMapper {
    @Select("select * from advertisement order by visit_count desc limit 10;")
    List<Advertisement> getTop10();
}
