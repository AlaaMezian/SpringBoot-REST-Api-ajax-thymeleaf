package com.appcom.waffa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcom.waffa.entity.TermsAndCondition;
import com.appcom.waffa.respository.TermsAndConditionRepository;

@Service("termsAndConditionService")
public class TermsAndConditionServiceImpl implements TermsAndConditionService{

	@Autowired
	private TermsAndConditionRepository termsAndConditionRepository;
	@Override
	public TermsAndCondition getTermsAndCondition() {
	 
		TermsAndCondition entity = termsAndConditionRepository.findOneById(1);
		return entity;
	}

	
	
}
