package usst.web.mapper;

import org.apache.ibatis.annotations.Select;
import usst.web.entity.Advertisement;
import usst.web.pojo.DailyVisited;

import java.util.List;

/**
 * @author jyzxc
 * @since 2024-12-27
 */
public interface StatisticsMapper {
    @Select("select * from advertisement order by visit_count desc limit 10;")
    List<Advertisement> getTop10();

    @Select("select count(*) from advertisement;")
    Integer getTotalNum();

    @Select("select sum(visit_count) from advertisement;")
    Integer getTotalVisit();

    @Select("""
            select Count(*) as count ,DATE(ad_create_time) as date from advertisement where advertisement.ad_create_time > date_sub(curdate(), interval 10 day)
            GROUP BY
                DATE(ad_create_time)
            ORDER BY
                DATE(ad_create_time);
    """)
    List<DailyVisited> getRecentVisited();
}
