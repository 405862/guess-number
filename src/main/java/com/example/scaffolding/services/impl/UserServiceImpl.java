package com.example.scaffolding.services.impl;

import com.example.scaffolding.entities.UserEntity;
import com.example.scaffolding.models.MatchDiffficulty;
import com.example.scaffolding.models.MatchModel;
import com.example.scaffolding.models.RoundMatch;
import com.example.scaffolding.models.UserModel;
import com.example.scaffolding.repositories.UserRepository;
import com.example.scaffolding.services.MatchService;
import com.example.scaffolding.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MatchService matchService;

    @Override
    public UserModel createUser(String userName, String email) {
        Optional<UserEntity> userEntityOptional = userRepository.getByEmail(email);
        if (userEntityOptional.isPresent()) {
            return null;
        }
        else {
            UserEntity userEntity = new UserEntity();
            userEntity.setUserName(userName);
            userEntity.setEmail(email);
            userEntity = userRepository.save(userEntity);
            return modelMapper.map(userEntity, UserModel.class);
        }

    }

    @Override
    public MatchModel createUserMatch(Long userId, MatchDiffficulty difficulty) {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if(userEntity.isEmpty()){
            throw new EntityNotFoundException();
        }else{
            UserModel user= modelMapper.map(userEntity.get(), UserModel.class);
            return matchService.createMatch(user, difficulty);
        }
    }

    @Override
    public RoundMatch playerUserMatch(Long userId, Long matchId, Integer numberToPlay) {
        MatchModel match = matchService.getMatchById(matchId);
        if (match.getUser().getId().equals(userId)) {
            return null;

        } else {
            return matchService.playMatch(match, numberToPlay);
        }
    }

}
