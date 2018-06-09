package com.appcom.waffa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcom.waffa.constant.Status;
import com.appcom.waffa.entity.SliderImage;
import com.appcom.waffa.exceptions.InternalServerErrorException;
import com.appcom.waffa.exceptions.NotFoundException;
import com.appcom.waffa.model.SliderImageModel;
import com.appcom.waffa.respository.SliderImagesRepository;

@Service
public class SliderImagesServiceImp implements SliderImagesService {

	@Autowired
	private SliderImagesRepository sliderImagesRepository;

	@Override
	public List<SliderImageModel> getActiveSliderImages(Status active) {
		List<SliderImageModel> sliderImageMdl = new ArrayList<SliderImageModel>();
		try {
			List<SliderImage> sliderImages = sliderImagesRepository.findAllByIsActive(Status.Y);

			for (SliderImage sliderImage : sliderImages) {
				SliderImageModel imageModel = new SliderImageModel();
				imageModel.setId(sliderImage.getId());
				imageModel.setItemImageUrl(sliderImage.getItemImageUrl());
				sliderImageMdl.add(imageModel);
			}
		} catch (Exception e) {
			throw new InternalServerErrorException("Some Thing went wrong while fetching from db");
		}
		return sliderImageMdl;
	}

	@Override
	public List<SliderImage> getAllSliderImages() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveSliderImages(SliderImageModel sldModel) {
      SliderImage sliderEntity = new SliderImage();
      sliderEntity.setId(sldModel.getId());
      sliderEntity.setItemImageUrl(sldModel.getItemImageUrl());
      sliderEntity.setIsActive(Status.Y);
      sliderImagesRepository.save(sliderEntity);
      
	}

	@Override
	public String deleteSliderImages(SliderImageModel sliderImageMdl) {
		try {
			SliderImage sldImg = sliderImagesRepository.findOneSliderImageById(sliderImageMdl.getId());
			if (sldImg == null) {
				throw new NotFoundException("the category you are trying to delete does not exist");
			} else {
				sldImg.setIsActive(Status.N);
				sliderImagesRepository.save(sldImg);
				return "Deleted";
			}
		} catch (Exception e) {
			throw new InternalServerErrorException("failed to delete item");

		}

	}

}
