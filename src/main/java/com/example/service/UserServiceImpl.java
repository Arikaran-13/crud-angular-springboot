package com.example.service;

import com.example.entity.UserEntity;
import com.example.model.User;
import com.example.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    private static final Logger log= LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository,ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<User> registerUser(User userDto) {
            var user = userRepository.save(convertToUserEntity(userDto));
            return new ResponseEntity<>(convertToUserDto(user), HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<User> getUserById(int userId) {
       var user =  userRepository.findById(userId);
       if(user.isPresent()){
           return new ResponseEntity<>(convertToUserDto(user.get()),HttpStatus.OK);
       }
       else{
           throw new NoSuchElementException("User not found");
       }
    }

    @Override
    public ResponseEntity<List<User>> getAllUser() {
        return new ResponseEntity<>(convertToListOfUserDto(userRepository.findAll()),HttpStatus.OK);
    }

    public  User convertToUserDto(UserEntity userEntity){
        return modelMapper.map(userEntity,User.class);
    }

    public UserEntity convertToUserEntity(User user){
        return modelMapper.map(user,UserEntity.class);
    }

    public List<User> convertToListOfUserDto(List<UserEntity> userEntities){
        return userEntities
                .stream()
                .map(userEntity -> modelMapper.map(userEntity,User.class))
                .toList();
    }

}
