package org.example.rickandmortycharacters.model.RickAndMortyApiResponseModel;

public record RickAndMortyCharacter(
        String id,
        String name,
        String status,
        String species,
        RickAndMortyCharacterOrigin origin
) {
}
