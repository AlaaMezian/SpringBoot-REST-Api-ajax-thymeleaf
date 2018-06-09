package com.appcom.waffa.service;

import java.util.List;

import com.appcom.waffa.constant.Status;
import com.appcom.waffa.entity.SliderImage;
import com.appcom.waffa.model.SliderImageModel;


public interface SliderImagesService {
public List<SliderImageModel>  getActiveSliderImages(Status active);
public List<SliderImage> getAllSliderImages();
public void saveSliderImages(SliderImageModel sldModel);
public String deleteSliderImages(SliderImageModel sliderImageMdl);


 
}
