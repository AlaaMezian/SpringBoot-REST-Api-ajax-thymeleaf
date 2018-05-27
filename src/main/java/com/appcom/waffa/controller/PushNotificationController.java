package com.appcom.waffa.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appcom.waffa.notification.FirebaseResponse;
import com.appcom.waffa.notification.PushNotificationService;
import com.appcom.waffa.respository.UserRepository;

@RestController
@RequestMapping("/api/v1")
public class PushNotificationController {
	/**
	 * 
	 */
	@Autowired
	private PushNotificationService pushNotificationService;

	/**
	 * 
	 */
	@Autowired
	private UserRepository personRepositoty;

	@RequestMapping(value = "/sendNotification", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> send() {

		JSONObject body = new JSONObject();
		// JsonArray registration_ids = new JsonArray();
		// body.put("registration_ids", registration_ids);
		try {
			body.put("to", "euijtqLcmhE:APA91bGcYofSRb5xUw44lFBPR3rN_4RCaEOVpNN5RjdPoujBzmcvUgp9dJNYa9t1PVvqah_-PXQ_QZ4RptMlS2oZX1ryHL429ubk6sY_5FlVcWgrTZaTktdMEPM3o7m6-hiE0ocvlNd9");
			body.put("priority", "high");
			// body.put("dry_run", true);

			JSONObject notification = new JSONObject();
			notification.put("body", "abo el bashayreh gawak :)");
			notification.put("title", "hello");
			// notification.put("icon", "myicon");

			JSONObject data = new JSONObject();
			data.put("key1", "value1");
			data.put("key2", "value2");

			body.put("notification", notification);
			body.put("data", data);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		HttpEntity<String> request = new HttpEntity<>(body.toString());

		CompletableFuture<FirebaseResponse> pushNotification = pushNotificationService.send(request);
		CompletableFuture.allOf(pushNotification).join();

		try {
			FirebaseResponse firebaseResponse = pushNotification.get();
			if (firebaseResponse.getSuccess() == 1) {
				System.out.print("push notification sent ok!");
			} else {
				System.out.print("error sending push notifications: " + firebaseResponse.toString());
			}
			return new ResponseEntity<>(firebaseResponse.toString(), HttpStatus.OK);

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("the push notification cannot be send.", HttpStatus.BAD_REQUEST);
	}
}

/**
 * save entity with token FCM from app android
 * 
 * @param person
 * @return
 */
/*
 * @RequestMapping(value = "/person", method = RequestMethod.POST) public
 * ResponseEntity<Person> saveProject(@RequestBody Person person) {
 * 
 * Person personSaved = personRepositoty.save(person);
 * 
 * return new ResponseEntity<Person>(personSaved, HttpStatus.CREATED);
 * 
 * }
 */

/**
 * send notificatin to all
 * 
 * @return
 */
// @RequestMapping(value = "/pushAll", method = RequestMethod.GET)
// public ResponseEntity<?> pushAll() {
//
// List<String> tokens = new ArrayList<>();
// List<U> persons = personRepositoty.findAll();
//
// persons.forEach(p -> tokens.add(p.getTokenFCM().getToken()));
//
// Notification notification = new Notification("default", "My App", "Test");
// Push push = new Push("high", notification, tokens);
// pushNotification.sendNotification(push);
//
// return new ResponseEntity<Person>(HttpStatus.OK);
// }
//
// /**
// * Send to singleton app
// *
// * @return
// */
// @RequestMapping(value = "/push", method = RequestMethod.GET)
// public ResponseEntity<?> push() {
//
// personRepositoty.findFirstByOrderByName().ifPresent(p -> {
// Notification notification = new Notification("default", "My app", "Teste");
// Push push = new Push(p.getTokenFCM().getToken(), "high", notification);
// pushNotification.sendNotification(push);
// });
//
// return new ResponseEntity<Person>(HttpStatus.OK);
// }
