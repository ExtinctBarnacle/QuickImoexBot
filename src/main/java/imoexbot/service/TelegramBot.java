package imoexbot.service;

import imoexbot.config.BotConfig;
import lombok.AllArgsConstructor;
import imoexbot.model.StockModel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.text.ParseException;

//@Service
@Component
@AllArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        StockModel stockModel = new StockModel();
        String stock = "";

        if(update.hasMessage() && update.getMessage().hasText()){
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText){
                case "/start":
                    startCommandReceived (chatId, update.getMessage().getChat().getFirstName());
                    break;
                default:
                    try {
                        stock = StockService.getStockRate (messageText, stockModel);

                    } catch (IOException e) {

                        sendMessage(chatId, e.getMessage() );//+ ". We have not found such a stock." + "\n" +
                                //"Enter the stock whose current price" + "\n" +
                                //"you want to know" + "\n" +
                                //"For example: LKOH");
                    } catch (ParseException e) {
                        throw new RuntimeException("Unable to parse date");
                    }
                    sendMessage(chatId, stock);
            }
        }

    }

    private void startCommandReceived(Long chatId, String name) {
        String answer = "Hi, " + name + ", nice to meet you!" + "\n" +
                "Enter the stock name or ticker whose current price" + "\n" +
                "you want to know" + "\n" +
                "For example: LKOH or 'Лукойл'";
        sendMessage(chatId, answer);
    }

    private void sendMessage(Long chatId, String textToSend){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {

        }
    }
}