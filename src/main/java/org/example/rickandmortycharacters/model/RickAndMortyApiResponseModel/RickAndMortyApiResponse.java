package org.example.rickandmortycharacters.model.RickAndMortyApiResponseModel;

import java.util.List;

public record RickAndMortyApiResponse(
        RickAndMortyApiResponseInfo info,
        List<RickAndMortyCharacter> results
) {
}
