package com.anton.bot.telegramfamilybot.repo;

import com.anton.bot.telegramfamilybot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByChatId(long id);

}
