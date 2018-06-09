package com.appcom.waffa.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appcom.waffa.constant.Status;
import com.appcom.waffa.entity.SliderImage;

@Repository
public interface SliderImagesRepository extends JpaRepository<SliderImage, Integer> {
List<SliderImage> findAllByIsActive(Status active);
SliderImage findOneSliderImageById(int id);
}
