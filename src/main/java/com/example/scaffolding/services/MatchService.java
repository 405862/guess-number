package com.example.scaffolding.services;

import com.example.scaffolding.entities.UserEntity;
import com.example.scaffolding.models.MatchModel;
import com.example.scaffolding.models.RoundMatch;
import com.example.scaffolding.models.UserModel;
import org.apache.catalina.User;
import com.example.scaffolding.models.MatchDiffficulty;
import org.springframework.stereotype.Service;


@Service
public interface MatchService {
    MatchModel createMatch(UserModel user, MatchDiffficulty matchDifficulty);
    MatchModel getMatchById(Long matchId);

    RoundMatch playMatch(MatchModel match, Integer number);
}
