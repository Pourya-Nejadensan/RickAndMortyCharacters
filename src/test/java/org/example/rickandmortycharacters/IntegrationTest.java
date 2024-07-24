package org.example.rickandmortycharacters;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    private static MockWebServer mockWebServer;

    @BeforeAll
    static void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("app.rickandmorty.api.url", () -> mockWebServer.url("/").toString());
    }


    @Test
    public void testApiCall() throws Exception {

        mockWebServer.enqueue(new MockResponse()
                        .setResponseCode(200)
                        .addHeader("Content-Type", "application/json")
                        .setBody("""
                 {
                    "info": {
                    },
                    "results": [
                     {
                         "id": 1,
                         "name": "Rick Sanchez",
                         "status": "Alive",
                         "species": "Human",
                         "type": "",
                         "gender": "Male",
                         "location": {
                             "name": "Citadel of Ricks",
                             "url": "https://rickandmortyapi.com/api/location/3"
                         },
                         "image": "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                         "episode": [
                             "https://rickandmortyapi.com/api/episode/1",
                             "https://rickandmortyapi.com/api/episode/2"
                         ],
                         "url": "https://rickandmortyapi.com/api/character/1",
                         "created": "2017-11-04T18:48:46.250Z"
                     }
                    ]
                 }
                 """));


        mockMvc.perform(MockMvcRequestBuilders.get("/api/characters"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                             {
                             "id": 1,
                             "name": "Rick Sanchez",
                             "species": "Human"
                             }
                        ]
                        """));
    }
}
