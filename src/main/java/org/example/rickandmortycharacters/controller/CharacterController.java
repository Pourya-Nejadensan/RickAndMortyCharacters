package org.example.rickandmortycharacters.controller;

import lombok.RequiredArgsConstructor;
import org.example.rickandmortycharacters.model.Character;
import org.example.rickandmortycharacters.service.CharacterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService characterService;

//    @GetMapping
//    public List<Character> getCharacters() {
//        return characterService.getAllCharacters();
//    }

    @GetMapping("/{id}")
    public Character getCharacterById(@PathVariable int id) {
        return characterService.getCharacterById(id);
    }

    @GetMapping
    public List<Character> getCharactersByStatus(@RequestParam(required = false) String status) {
        if (status != null) {
            return characterService.getCharactersByStatus(status);
        }
        return characterService.getAllCharacters();
    }
}
