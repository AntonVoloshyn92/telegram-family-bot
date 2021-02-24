package com.anton.bot.telegramfamilybot.service;

import com.anton.bot.telegramfamilybot.model.User;
import com.anton.bot.telegramfamilybot.repo.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public User findByChatId(long id){
        return userRepository.findByChatId(id);
    }

    @Transactional
    public void addUser(User user){
        userRepository.save(user);
    }


    @Transactional
    public void updateUser(User user){
        userRepository.save(user);
    }

    @Transactional
    public void findById(long id){

    }
}
