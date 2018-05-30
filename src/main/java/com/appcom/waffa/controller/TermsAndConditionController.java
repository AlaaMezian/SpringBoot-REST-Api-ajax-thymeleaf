package com.appcom.waffa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appcom.waffa.entity.TermsAndCondition;
import com.appcom.waffa.service.TermsAndConditionService;
import com.appcom.waffa.success.code.CommonSuccessCode;
import com.appcom.waffa.utils.CustomResponse;

@RestController
@RequestMapping("/api/v1")
public class TermsAndConditionController {

	@Autowired
	private TermsAndConditionService termsAndConditionService;
	
	@RequestMapping(value = "/termsAndCondition", method = RequestMethod.GET)
	public ResponseEntity<CustomResponse> getTermsAndCondition()
	{
		TermsAndCondition terms = termsAndConditionService.getTermsAndCondition();
		return new ResponseEntity<CustomResponse>(new CustomResponse(CommonSuccessCode.Success, terms),
				HttpStatus.OK);
	}

}
