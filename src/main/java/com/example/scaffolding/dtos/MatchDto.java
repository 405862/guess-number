package com.example.scaffolding.dtos;

import com.example.scaffolding.models.MatchDiffficulty;
import com.example.scaffolding.models.MatchStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;

@Getter
@Setter
@NoArgsConstructor
public class MatchDto {
    private Long id;
    private MatchDiffficulty difficulty;
    private Integer remainingTries;


}
