package usst.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usst.web.entity.Advertisement;
import usst.web.mapper.AdvertisementMapper;
import usst.web.service.AdvertisementService;

import java.util.List;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    private AdvertisementMapper advertisementMapper;

    @Override
    public void createAdvertisement(Advertisement advertisement) {
        advertisementMapper.insertAdv(advertisement);
    }

    @Override
    public void updateAdvertisement(Advertisement advertisement) {
        advertisementMapper.updateAdv(advertisement);
    }

    @Override
    public void deleteAdvertisement(int adId) {
        advertisementMapper.deleteAdv(adId);
    }

    @Override
    public Advertisement getAdvertisementById(int adId) {
        return advertisementMapper.selectAdvById(adId);
    }

    @Override
    public Advertisement getAdvertisementByArticleId(int adId) {
        return advertisementMapper.selectAdvByArticleId(adId);
    }

    @Override
    public List<Advertisement> getAllAdvertisements() {
        return advertisementMapper.selectAll();
    }

    @Override
    public List<Advertisement> getAllAdvertisementsByAdvertiserId(int advertiserId) {
        return advertisementMapper.selectAllByAdvertiserId(advertiserId);
    }
}
