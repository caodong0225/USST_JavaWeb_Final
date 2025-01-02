package usst.web.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usst.web.entity.Advertisement;
import usst.web.mapper.AdvertisementMapper;
import usst.web.mapper.TagMapper;
import usst.web.service.ITagService;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author jyzxc
 * @since 2025-1-1
 */
@Service
public class TagServiceImpl implements ITagService {
    @Resource
    TagMapper tagMapper;

    @Autowired
    private AdvertisementMapper advertisementMapper;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Integer getRecommendationUri(Map<String, Double> userPreferences) {
        List<Advertisement> advertisements = advertisementMapper.selectAll();
        double maxSimilarity = 0;
        Integer bestAdId = advertisements.isEmpty() ? 0 : advertisements.get(0).getAdId();
        for (Advertisement ad : advertisements) {
            try {
                // 解析广告特征
                Map<String, Double> adFeatures = objectMapper.readValue(ad.getAdFeature(), Map.class);

                // 计算相似度（使用余弦相似度作为示例）
                double similarity = cosineSimilarity(userPreferences, adFeatures);

                // 更新最佳匹配广告
                if (similarity > maxSimilarity) {
                    maxSimilarity = similarity;
                    bestAdId = ad.getAdId();
                }
            } catch (Exception e) {
                e.printStackTrace();
                // 处理解析错误（可以记录日志或跳过该广告）
            }
        }
        return bestAdId;
        // 抽取随机广告
    }

    private double cosineSimilarity(Map<String, Double> userPrefs, Map<String, Double> adFeatures) {
        double dotProduct = 0;
        double userNorm = 0;
        double adNorm = 0;

        for (Map.Entry<String, Double> entry : userPrefs.entrySet()) {
            String key = entry.getKey().toLowerCase();
            Double userValue = entry.getValue();
            Double adValue = adFeatures.get(key);

            if (adValue != null) {
                dotProduct += userValue * adValue;
                userNorm += userValue * userValue;
                adNorm += adValue * adValue;
            }
        }

        if (userNorm == 0 || adNorm == 0) {
            return 0;
        }

        return dotProduct / (Math.sqrt(userNorm) * Math.sqrt(adNorm));
    }
}
