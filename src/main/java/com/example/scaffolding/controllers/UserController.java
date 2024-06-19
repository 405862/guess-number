package com.example.scaffolding.controllers;

import com.example.scaffolding.dtos.*;
import com.example.scaffolding.models.MatchModel;
import com.example.scaffolding.models.RoundMatch;
import com.example.scaffolding.models.UserModel;
import com.example.scaffolding.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/guess-number/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;
    @PostMapping("")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserModel user = userService.createUser(userDto.getUserName(), userDto.getEmail());
        UserDto userDtoCreated = modelMapper.map(user, UserDto.class);
        return ResponseEntity.ok(userDtoCreated);
    }

    @PostMapping("/{userId}/matches")
    public ResponseEntity<MatchDto> createUserMatch(@PathVariable Long userId, @RequestBody CreateUserMatch creteUserMatchDto){
        MatchModel match = userService.createUserMatch(userId, creteUserMatchDto.getDifficulty());
        MatchDto matchDto = modelMapper.map(match, MatchDto.class);
        return ResponseEntity.ok(matchDto);
    }

    @PostMapping("/{userId}/matches/{matchId}")
    public ResponseEntity<RoundMatchDto> playerUserMatch(@PathVariable Long userId, @PathVariable Long matchId, @RequestBody PlayerUserMatchDto playerUserMatchDto) {
        RoundMatch roundMatch = userService.playerUserMatch(userId, matchId, playerUserMatchDto.getNumber());
        MatchDto matchDto = modelMapper.map(roundMatch.getMatch(), MatchDto.class);
        RoundMatchDto roundMatchDto = modelMapper.map(roundMatch, RoundMatchDto.class);
        roundMatchDto.setMatchDto(matchDto);
        return ResponseEntity.ok(roundMatchDto);
    }


}
