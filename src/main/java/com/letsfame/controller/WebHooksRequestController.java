package com.letsfame.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.letsfame.bean.WebHooksResponse;
import com.letsfame.repository.WebHooksResponseRepository;
//import com.razorpay.RazorpayClient;

@RestController()
public class WebHooksRequestController {

//	@Autowired
//	private RazorpayClient razorpayClient;

	@Autowired
	private WebHooksResponseRepository webHooksResponseRepository;

	@PostMapping(path = "/payment/webhooks", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> plan(@RequestBody final WebHooksResponse webHooksResponse) {

		JSONObject message = new JSONObject();
		try {
			webHooksResponseRepository.save(webHooksResponse);

		} catch (Exception e) {

			// Handle Exception
			message.put("error", e.getMessage());
			System.out.println(e.getMessage());

		}
		return new ResponseEntity<>(message.toString(), HttpStatus.OK);
	}

	@GetMapping(path = "/FetchAllWebhooks", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> Allplans() {

		JSONObject message = new JSONObject();
		try {

			List<WebHooksResponse> webHooksObj = webHooksResponseRepository.findAll();

			return new ResponseEntity<>(webHooksObj.toString(), HttpStatus.OK);

		} catch (Exception e) {

			// Handle Exception
			message.put("error", e.getMessage());
			System.out.println(e.getMessage());

		}
		return new ResponseEntity<>(message.toString(), HttpStatus.OK);
	}

}
