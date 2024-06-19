package com.example.scaffolding.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;

@Getter
@Setter
@NoArgsConstructor
public class MatchModel {

    private Long id;

    private UserModel user;

    private MatchDiffficulty difficulty;

    private Integer numberToGuess;

    private Integer remainingTries;

    private MatchStatus status;
}
