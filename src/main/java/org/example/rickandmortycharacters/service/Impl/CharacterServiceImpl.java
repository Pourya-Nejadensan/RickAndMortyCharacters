package org.example.rickandmortycharacters.service.Impl;

import org.example.rickandmortycharacters.model.Character;
import org.example.rickandmortycharacters.service.CharacterService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class CharacterServiceImpl implements CharacterService {

    private final RickAndMortyApiServiceImpl rickAndMortyApiService;

    public CharacterServiceImpl(RickAndMortyApiServiceImpl rickAndMortyApiService) {
        this.rickAndMortyApiService = rickAndMortyApiService;
    }

    @Override
    public List<Character> getAllCharacters() {
        return rickAndMortyApiService.loadAllCharacters().stream()
                .map(x -> new Character(
                Integer.parseInt(x.id()),
                x.name(),
                x.species()
        ))
                .collect(Collectors.toList());
    }

    @Override
    public Character getCharacterById(int id) {
        return getAllCharacters().stream().filter(x -> x.id() == id).findFirst().orElseThrow();
    }

    @Override
    public List<Character> getCharactersByStatus(String status) {
        return rickAndMortyApiService.loadAllCharacters().stream()
                .filter(x -> x.status().equals(status))
                .map(x -> new Character(
                        Integer.parseInt(x.id()),
                        x.name(),
                        x.species()
                ))
                .collect(Collectors.toList());
    }
}
