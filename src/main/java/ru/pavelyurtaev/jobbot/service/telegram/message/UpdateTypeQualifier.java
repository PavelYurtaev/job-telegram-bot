package ru.pavelyurtaev.jobbot.service.telegram.message;

import com.pengrad.telegrambot.model.*;

public interface UpdateTypeQualifier {
    boolean isRestartBotUpdate(Update update);

    boolean isPhotoMessage(Update update);

    boolean isAudioMessage(Update update);

    boolean isPollAnswer(Update update);

    boolean isTextMessage(Update update);

    boolean isEditedMessage(Update update);

    boolean isCallbackQuery(Update update);

    User getTelegramUser(Update update);

    Long getUserChatId(CallbackQuery callbackQuery);

    Long getUserChatId(Message message);

    Long getUserChatId(PollAnswer pollAnswer);

    boolean isDocumentMessage(Update update);
}
