package org.example.rickandmortycharacters.service;

import org.example.rickandmortycharacters.model.Character;

import java.util.List;

public interface CharacterService {
    List<Character> getAllCharacters();
    Character getCharacterById(int id);
    List<Character> getCharactersByStatus(String status);

}
