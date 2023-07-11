package com.example.practica.controllers;

import com.example.practica.entities.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    @LocalServerPort
    private int port;

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    void findAll() {
        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/api/v2/read", Laptop[].class);

        List<Laptop> laptopList = Arrays.asList(Objects.requireNonNull(response.getBody()));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());

    }

    @Test
    void findById() {
        ResponseEntity<Laptop> response = testRestTemplate.getForEntity("/api/v2/read/1", Laptop.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    void insert() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                
                {
                        "brand": "Apple",
                        "model": "Macbook Pro M1",
                        "price": 29999.0
                }
                
                """;

        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/v2/insert", HttpMethod.POST, entity, Laptop.class);

        Laptop result = response.getBody();

        assertEquals(1L, result.getId());
        assertEquals("Apple", result.getBrand());
        assertEquals("Macbook Pro M1", result.getModel());

    }

    @Test
    void update() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        String json = """
                
                {
                        "id": 1,
                        "brand": "Applee",
                        "model": "Macbook Pro M6",
                        "price": 29999.0
                }
                
                """;

        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/v2/update", HttpMethod.PUT, entity, Laptop.class);

        Laptop result = response.getBody();

        assertEquals(1L, result.getId());
        assertEquals("Applee", result.getBrand());
        assertEquals("Macbook Pro M6", result.getModel());
    }

    @Test
    void delete() {

    }

    @Test
    void testDelete() {

    }
}