import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.concurrent.CompletableFuture;

public class Lengthbot extends TelegramLongPollingBot {

    String lastCommand = "";
    final String GET_USERNAME_COMMAND = "/get_username";
    final String GET_SQUARE_COMMAND = "/get_square";

    public String getBotUsername() {
        return "length_bot";  // comment
    }

    public String getBotToken() {
        return "1642855097:AAFK6369Kw_Wbz5NH7qszo7r6Bu33PNJFZg";
    }

    public void onUpdateReceived(Update update) {
        CompletableFuture.runAsync(()-> System.out.print(""));

        String command = update.getMessage().getText();

        if(command.equals(GET_USERNAME_COMMAND))
        {
            lastCommand = GET_USERNAME_COMMAND;
            getUserNameCommand(update);
            return ;
        } else
        if(command.equals(GET_SQUARE_COMMAND) || lastCommand.equals(GET_SQUARE_COMMAND))
        {
            lastCommand = GET_SQUARE_COMMAND;
            getSquareCommand(update, command);
        } else {
            getUnknownCommand (update);
        }
    }

    private void getUnknownCommand(Update update) {
        Long chatId = update.getMessage().getChatId();
        print(chatId, "Xato commanda kiritding nomard!!!");
    }

    private void getSquareCommand(Update update, String command) {
        Long chatId = update.getMessage().getChatId();

        if(command.equals(GET_SQUARE_COMMAND))
        {
            print(chatId, "son kiriting:");
        } else {
            try {
                int a = Integer.valueOf(command);
                a = a * a;
                print(chatId, "Ushbu sonning kvadrati: " + String.valueOf(a));
                lastCommand = "";
            } catch (Exception e) {
                print(chatId, "Xato belgi kiritili!!!!!!");
                lastCommand = "";
            }



        }

    }

    public void getUserNameCommand(Update update) {
        Long chatId = update.getMessage().getChatId();
        User user = update.getMessage().getFrom();

        print(chatId, user.getFirstName() + " " + user.getLastName());
        print(chatId, "Nima gap!");
    }

     public void print(Long chatId, String text) {
         SendMessage message = new SendMessage();
         message.setChatId(String.valueOf(chatId));
         message.setText(text);
         try {
             execute(message);
         } catch (TelegramApiException e) {
             e.printStackTrace();
         }
     }
}
