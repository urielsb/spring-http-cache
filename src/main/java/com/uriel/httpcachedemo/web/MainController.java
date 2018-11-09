/**
 * 
 */
package com.uriel.httpcachedemo.web;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Uriel Santoyo
 *
 */
@RestController
public class MainController {
	
	private static Logger log = LoggerFactory.getLogger(MainController.class);

	@GetMapping("/")
	public String index() {
		log.info("Default request -->");
		return "Hello!";
	}
	
	@GetMapping("/testcache")
	public ResponseEntity<String> cachedValue() {
		log.info("testcache Request -->");
		String message = "Cached Value: "
				.concat(String.valueOf(ThreadLocalRandom.current().nextInt()));
		log.info("Message to send {}", message);
		CacheControl cacheControl = CacheControl.maxAge(5, TimeUnit.MINUTES);
		return ResponseEntity.ok()
				.cacheControl(cacheControl)
				.body(message);
	}
}
