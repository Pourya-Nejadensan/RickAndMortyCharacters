package org.example.rickandmortycharacters.model.RickAndMortyApiResponseModel;

public record RickAndMortyApiResponseInfo(
        int count,
        int pages,
        String next,
        String prev
) {
}
