package com.appcom.waffa.notification;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;


import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.appcom.waffa.utils.HeaderRequestInterceptor;

@Service
public class PushNotificationService {

	private static final String FIREBASE_SERVER_KEY = "AAAAn5zRY0Y:APA91bH2p1yEQQaJjdgKIrhbP1bSO2GHNJzRK4HXGJcfOnqfcPKzFRv1UHvcXv9Atv5usJNszmxsUqrLvmQM3qGb0rVn8gXBKQ-rOpRPkTgLQ4vwwQ5CCYKg9tmFrO3WCJHVXqAf8ka9";

	private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";

	
	/**
	 * send push notification to API FCM
	 * 
	 * Using CompletableFuture with @Async to provide Asynchronous call.
	 * 
	 * 
	 * @param entity
	 * @return
	 */
	
	  @Async
	    public CompletableFuture<FirebaseResponse> send(HttpEntity<String> entity) {

	        RestTemplate restTemplate = new RestTemplate();

	        ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
	        interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
	        interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
	        restTemplate.setInterceptors(interceptors);

	        FirebaseResponse firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, FirebaseResponse.class);

	        return CompletableFuture.completedFuture(firebaseResponse);
	    }

	/**
	 * 
	 * @param push to all
	 * @return
	 */

//	public FirebaseResponse sendNotification(Push push) {
//
//		HttpEntity<Push> request = new HttpEntity<>(push);
//
//		CompletableFuture<FirebaseResponse> pushNotification = this.send(request);
//		CompletableFuture.allOf(pushNotification).join();
//
//		FirebaseResponse firebaseResponse = null;
//
//		try {
//			firebaseResponse = pushNotification.get();
//		} catch (InterruptedException | ExecutionException e) {
//			e.printStackTrace();
//		}
//		return firebaseResponse;
//	}

}
