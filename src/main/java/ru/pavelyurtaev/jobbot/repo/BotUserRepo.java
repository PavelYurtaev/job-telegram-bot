package ru.pavelyurtaev.jobbot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pavelyurtaev.jobbot.model.BotUser;

public interface BotUserRepo extends JpaRepository<BotUser, Long> {
}
