package com.example.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Filip Hrisafov
 */
@RestController
@RequestMapping("/api")
public class DummyController {

    @GetMapping("/greeting")
    public Object greeting() {
        Map<String, String> payload = new HashMap<>();
        payload.put("key", UUID.randomUUID().toString());
        return payload;
    }

}
