package ru.pavelyurtaev.jobbot.service.telegram;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.ChatMember;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.pavelyurtaev.jobbot.constants.Command;
import ru.pavelyurtaev.jobbot.model.BotUser;
import ru.pavelyurtaev.jobbot.model.Job;
import ru.pavelyurtaev.jobbot.repo.BotUserRepo;
import ru.pavelyurtaev.jobbot.service.job.JobApi;
import ru.pavelyurtaev.jobbot.service.telegram.message.UpdateTypeQualifier;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BotHandler {

    @Value("${bot.token}")
    private String token;

    private TelegramBot bot;
    private final UpdateTypeQualifier updateTypeQualifier;
    private final JobApi jobApi;
    private final ObjectMapper objectMapper;
    private final BotUserRepo userRepo;

    @PostConstruct
    public void runBot() {
        bot = new TelegramBot(token);
        serve();
    }

    public void serve() {
        bot.setUpdatesListener(updates -> {
            updates.forEach(this::process);
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });

    }

    private BotUser getUser(final Update update) {
        final User user = updateTypeQualifier.getTelegramUser(update);
        return userRepo.findById(user.id()).orElseGet(() -> createNewUser(user));
    }

    @SneakyThrows
    private BotUser createNewUser(final User user) {

        final BotUser newUser = BotUser.builder()
                .id(user.id())
                .createdAt(LocalDateTime.now())
                .isActive(true)
                .username(user.username())
                .firstName(user.firstName())
                .lastName(user.lastName())
                .build();
        return userRepo.save(newUser);

    }

    @SneakyThrows
    private void process(Update update) {
        final BotUser botUser = getUser(update);

        if (updateTypeQualifier.isRestartBotUpdate(update)) {
            final ChatMember.Status status = update.myChatMember().newChatMember().status();
            log.info("Status of Update: " + status);
            switch (status) {
                case member -> botUser.setIsActive(true); // перезапустил бота
                case kicked -> botUser.setIsActive(false); // остановил бота
            }
            userRepo.save(botUser);
            return;
        }

        if (!botUser.getIsActive()) {
            log.error("User is not active, ID = " + botUser.getId());
            return;
        }

        if (updateTypeQualifier.isEditedMessage(update)) {
            executeBotSendMessage(botUser.getId(), "Извините, редактирование сообщений не поддерживается");
            return;
        }

        if (updateTypeQualifier.isTextMessage(update)) {
            final String text = update.message().text();
            if (Command.START.text.equals(text)) {
                executeBotSendMessage(botUser.getId(), """
                        🤘 Welcome to JobBot
                                                
                        Enter your job request:
                        """);
                return;
            }

            final List<Job> jobs = jobApi.getJobs(text);

            final SendMessage sendMessage = new SendMessage(botUser.getId(),
                    jobs.isEmpty() ? "No jobs on your request " : jobs.get(0).getTitle()
            );
            bot.execute(sendMessage);
            executeBotSendMessage(botUser.getId(), "Enter another job request:");


        } else if (updateTypeQualifier.isCallbackQuery(update)) {

        } else if (updateTypeQualifier.isPollAnswer(update)) {


        } else {
            executeBotSendMessage(botUser.getId(), "Message is not supported");
        }

    }

    private void executeBotSendMessage(final Long chatId, final String message) {
        final SendMessage sendMessage = new SendMessage(chatId, message);
        bot.execute(sendMessage);
    }

}
