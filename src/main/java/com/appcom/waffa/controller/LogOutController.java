package com.appcom.waffa.controller;

import static com.appcom.waffa.utils.AppConstants.SECRET;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appcom.waffa.exceptions.BadRequestException;
import com.appcom.waffa.service.LogoutService;
import com.appcom.waffa.success.code.CommonSuccessCode;
import com.appcom.waffa.utils.CustomResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MissingClaimException;

@RestController
@RequestMapping("/api/v1")
public class LogOutController {

	@Autowired
	private LogoutService logoutService;

	private Map<String, String> getHeadersInfo(HttpServletRequest request) {

		Map<String, String> map = new HashMap<String, String>();

		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}

		return map;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ResponseEntity<CustomResponse> logout(HttpServletRequest request) {
		String token = request.getHeader("authorization");
		String tokenOriginal = token.replace("Bearer ", "");
		try {
			Jws<Claims> claims = Jwts.parser().requireSubject("JwtToken").setSigningKey(SECRET.getBytes())
					.parseClaimsJws(tokenOriginal);

			Integer userId = claims.getBody().get("id", Integer.class);

			logoutService.logout(userId);

		} catch (MissingClaimException e) {
			e.printStackTrace();
			throw new BadRequestException("some thing went wrong please check your request");
		}

		return new ResponseEntity<CustomResponse>(new CustomResponse(CommonSuccessCode.Success), HttpStatus.OK);
	}
}
