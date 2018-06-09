package com.appcom.waffa.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appcom.waffa.constant.Status;
import com.appcom.waffa.entity.WorkingTimes;

@Repository
public interface WorkingTimesRepository  extends JpaRepository<WorkingTimes,Integer> {
	 public List<WorkingTimes> findAllWorkingTimesByIsActive(Status isActive);
	 
	 
	}

