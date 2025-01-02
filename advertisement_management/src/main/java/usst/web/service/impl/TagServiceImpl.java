package usst.web.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usst.web.entity.Advertisement;
import usst.web.mapper.AdvertisementMapper;
import usst.web.mapper.TagMapper;
import usst.web.service.ITagService;

import java.util.*;

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
    private final Random random = new Random();

    @Override
    public Integer getRecommendationUri(Map<String, Double> userPreferences) {
        List<Advertisement> advertisements = advertisementMapper.selectAll();
        if (advertisements.isEmpty()) {
            return 0;
        }

        // 创建一个列表来存储广告及其相似度
        List<Map.Entry<Advertisement, Double>> adSimilarityList = new ArrayList<>();

        for (Advertisement ad : advertisements) {
            try {
                // 解析广告特征
                Map<String, Double> adFeatures = objectMapper.readValue(ad.getAdFeature(), Map.class);

                // 计算相似度
                double similarity = cosineSimilarity(userPreferences, adFeatures);

                // 将广告和相似度存入列表
                adSimilarityList.add(new AbstractMap.SimpleEntry<>(ad, similarity));
            } catch (Exception e) {
                e.printStackTrace();
                // 处理解析错误（可以记录日志或跳过该广告）
            }
        }

        // 根据相似度降序排序
        adSimilarityList.sort((entry1, entry2) -> Double.compare(entry2.getValue(), entry1.getValue()));

        // 选择相似度最高的前五个广告
        List<Advertisement> topFiveAds = new ArrayList<>();
        for (int i = 0; i < Math.min(5, adSimilarityList.size()); i++) {
            topFiveAds.add(adSimilarityList.get(i).getKey());
        }

        // 从前五个广告中随机选择一个
        if (!topFiveAds.isEmpty()) {
            return topFiveAds.get(random.nextInt(topFiveAds.size())).getAdId();
        }

        return 0; // 如果没有广告可选，返回0或适当的默认值
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
