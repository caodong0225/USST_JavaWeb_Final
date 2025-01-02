package usst.web.service;

import java.util.Map;

/**
 * @author jyzxc
 * @since 2025-1-1
 */
public interface ITagService {
    Integer getRecommendationUri(Map<String, Double> userPreferences);
}
