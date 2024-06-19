package com.example.scaffolding.services.impl;

import com.example.scaffolding.entities.MatchEntity;
import com.example.scaffolding.entities.UserEntity;
import com.example.scaffolding.models.*;
import com.example.scaffolding.repositories.MatchRepository;
import com.example.scaffolding.services.MatchService;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import jakarta.persistence.EntityNotFoundException;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private ModelMapper modelMapper;

    private final Random random = new Random();

    @Override
    public MatchModel createMatch(UserModel user, MatchDiffficulty matchDifficulty) {
        MatchEntity matchEntity = new MatchEntity();
        matchEntity.setUserEntity(modelMapper.map(user, UserEntity.class));
        matchEntity.setDifficulty(matchDifficulty);
        switch (matchDifficulty) {
            case HARD -> matchEntity.setRemainingTries(5);
            case MEDIUM -> matchEntity.setRemainingTries(8);
            case EASY -> matchEntity.setRemainingTries(10);
        }
        matchEntity.setNumberToGuess(random.nextInt(100));
        matchEntity.setStatus(MatchStatus.PLAYING);
        matchEntity.setUpdatedAt(LocalDateTime.now());
        matchEntity.setCreatedAt(LocalDateTime.now());
        MatchEntity matchEntitySaved=matchRepository.save(matchEntity);
        return modelMapper.map(matchEntitySaved, MatchModel.class);
    }

    @Override
    public MatchModel getMatchById(Long matchId) {
        Optional<MatchEntity> matchEntityOptional = matchRepository.findById(matchId);
        if (matchEntityOptional.isEmpty()) {
            throw new EntityNotFoundException();
        } else {
            return modelMapper.map(matchEntityOptional.get(), MatchModel.class);
        }
    }

    @Override
    public RoundMatch playMatch(MatchModel match, Integer number) {
        RoundMatch roundMatch = new RoundMatch();
        roundMatch.setMatch(match);
        if(match.getStatus().equals(MatchStatus.FINISH)){
            roundMatch.setRespuesta("JUEGO FINALIZADO");
        }
        if(match.getNumberToGuess().equals(number)){
            match.setStatus(MatchStatus.FINISH);
            roundMatch.setRespuesta("GANO");
        }else{
            match.setRemainingTries(match.getRemainingTries()-1);
            if(match.getRemainingTries().equals(0)){
                match.setStatus(MatchStatus.FINISH);
                roundMatch.setRespuesta("PERDIO");
            }else{
                if(number>match.getNumberToGuess()){
                    roundMatch.setRespuesta("MENOR");

            }else{
                roundMatch.setRespuesta("MAYOR");
                }
        }
    }
        UserEntity userEntity = modelMapper.map(match.getUser(), UserEntity.class);
        MatchEntity matchEntity = modelMapper.map(match, MatchEntity.class);
        matchEntity.setUserEntity(userEntity);
        matchRepository.save(matchEntity);
        matchEntity.setUpdatedAt(LocalDateTime.now());
        return roundMatch;
    }
}
