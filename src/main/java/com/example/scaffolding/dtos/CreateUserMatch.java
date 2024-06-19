package com.example.scaffolding.dtos;

import com.example.scaffolding.models.MatchDiffficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserMatch {
    private MatchDiffficulty difficulty;
}
