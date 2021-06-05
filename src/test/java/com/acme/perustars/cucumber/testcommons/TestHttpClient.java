package com.acme.perustars.cucumber.testcommons;

import com.google.gson.Gson;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class TestHttpClient {
    private final String SERVER_URL = "http://localhost";

    @LocalServerPort
    private int port;
    private final RestTemplate restTemplate = new RestTemplate();
    private final Gson gson = new Gson();

    public String Endpoint() {
        return SERVER_URL+":"+port+"/";
    }

    public ResponseEntity Post(final String endpointUrl, final String request) {
        return restTemplate.postForEntity(Endpoint() + endpointUrl, request, Void.class);
    }

    public void Put(final String endpointUrl, final String request) {
        restTemplate.put(Endpoint() + endpointUrl, request);
    }

    public void Delete(final String endpointUrl) {
        restTemplate.delete(Endpoint() + endpointUrl);
    }

    public ResponseEntity Get(final String endpointUrl, Class aClass) {
        return restTemplate.getForEntity(Endpoint() + endpointUrl, aClass);
    }

    public String ConvertToJson(Object object) {
        return gson.toJson(object);
    }
}
