package usst.web.service;

import usst.web.entity.Advertisement;
import java.util.List;

public interface AdvertisementService {
    void createAdvertisement(Advertisement advertisement);
    void updateAdvertisement(Advertisement advertisement);
    void deleteAdvertisement(int adId);
    Advertisement getAdvertisementById(int adId);
    Advertisement getAdvertisementByArticleId(int adId);
    List<Advertisement> getAllAdvertisements();
    List<Advertisement> getAllAdvertisementsByAdvertiserId(int advertiserId);
}
