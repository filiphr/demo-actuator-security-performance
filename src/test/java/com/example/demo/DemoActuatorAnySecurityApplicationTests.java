package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebClient(registerRestTemplate = true)
@TestPropertySource(properties = "demo.matcher=to-any")
public class DemoActuatorAnySecurityApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int serverPort;

    @Test
    public void contextLoads() {

        String greetingUrl = "http://localhost:" + serverPort + "/api/greeting";
        ResponseEntity<Object> result;
        for (int i = 0; i < 100000; i++) {
            result = restTemplate.getForEntity(greetingUrl, Object.class);
            assertThat(result).isNotNull();
        }

        String metricsUrl = "http://localhost:" + serverPort + "/actuator/metrics/http.server.requests?tag={tag}";
        ResponseEntity<Object> metrics = restTemplate.getForEntity(metricsUrl, Object.class, "uri:/api/greeting");

        System.out.println("With to-any");
        System.out.println(metrics);
    }

}
