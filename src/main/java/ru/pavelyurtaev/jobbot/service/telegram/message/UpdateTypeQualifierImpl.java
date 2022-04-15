package ru.pavelyurtaev.jobbot.service.telegram.message;

import com.pengrad.telegrambot.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.pavelyurtaev.jobbot.exception.JobBotException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateTypeQualifierImpl implements UpdateTypeQualifier {

    @Override
    public boolean isTextMessage(final Update update) {
        return update.message() != null && update.message().text() != null;
    }

    @Override
    public boolean isEditedMessage(final Update update) {
        return update.editedMessage() != null;
    }

    @Override
    public boolean isRestartBotUpdate(final Update update) {
        return update.myChatMember() != null;
    }

    @Override
    public boolean isPhotoMessage(final Update update) {
        return isMessage(update) && update.message().photo() != null && update.message().photo().length > 0;
    }

    @Override
    public boolean isDocumentMessage(final Update update) {
        return isMessage(update) && update.message().document() != null;
    }

    @Override
    public boolean isAudioMessage(final Update update) {
        return update.message() != null && update.message().audio() != null;
    }

    @Override
    public boolean isPollAnswer(final Update update) {
        return update.pollAnswer() != null; // работает только если не анонимный опрос
    }

    @Override
    public boolean isCallbackQuery(final Update update) {
        return update.callbackQuery() != null;
    }

    @Override
    public User getTelegramUser(final Update update) {
        if (isMessage(update)) {
            return update.message().from();
        } else if (isCallbackQuery(update)) {
            return update.callbackQuery().from();
        } else if (isPollAnswer(update)) {
            return update.pollAnswer().user();
        } else if (isEditedMessage(update)) {
            return update.editedMessage().from(); // todo process further
        } else if (isRestartBotUpdate(update)) {
            return update.myChatMember().from();
        } else {
            log.info("Unknown update " + update);
            throw new JobBotException("Unknown update type");
        }
    }

    @Override
    public Long getUserChatId(final CallbackQuery callbackQuery) {
        return callbackQuery.from().id();
    }

    @Override
    public Long getUserChatId(final Message message) {
        return message.from().id();
    }

    @Override
    public Long getUserChatId(final PollAnswer pollAnswer) {
        return pollAnswer.user().id();
    }

    private boolean isMessage(final Update update) {
        return update.message() != null;
    }
}
