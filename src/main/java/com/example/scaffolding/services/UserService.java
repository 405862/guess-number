package com.example.scaffolding.services;

import com.example.scaffolding.models.MatchDiffficulty;
import com.example.scaffolding.models.MatchModel;
import com.example.scaffolding.models.RoundMatch;
import com.example.scaffolding.models.UserModel;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserModel createUser(String userName, String email);
    MatchModel createUserMatch(Long userId, MatchDiffficulty difficulty);
    RoundMatch playerUserMatch(Long userId, Long matchId, Integer numberToPlay);
}
