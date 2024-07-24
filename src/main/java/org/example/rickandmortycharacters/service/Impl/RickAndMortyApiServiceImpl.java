package org.example.rickandmortycharacters.service.Impl;

import org.example.rickandmortycharacters.model.RickAndMortyApiResponseModel.RickAndMortyApiResponse;
import org.example.rickandmortycharacters.model.RickAndMortyApiResponseModel.RickAndMortyCharacter;
import org.example.rickandmortycharacters.service.RickAndMortyApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class RickAndMortyApiServiceImpl implements RickAndMortyApiService {

    private final RestClient restClient;

    public RickAndMortyApiServiceImpl(@Value("${app.rickandmorty.api.url}") String basicUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(basicUrl)
                .build();
    }

    @Override
    public List<RickAndMortyCharacter> loadAllCharacters() {
        RickAndMortyApiResponse responseBody = restClient
                .get()
                .uri("/character")
                .retrieve()
                .body(RickAndMortyApiResponse.class);
        return responseBody.results();
    }
}