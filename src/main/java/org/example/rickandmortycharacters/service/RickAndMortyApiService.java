package org.example.rickandmortycharacters.service;

import org.example.rickandmortycharacters.model.RickAndMortyApiResponseModel.RickAndMortyCharacter;

import java.util.List;

public interface RickAndMortyApiService {
    List<RickAndMortyCharacter> loadAllCharacters();
}
