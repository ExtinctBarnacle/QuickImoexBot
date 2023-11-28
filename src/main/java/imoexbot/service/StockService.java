package imoexbot.service;

import imoexbot.model.StockModel;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

//@Service
public class StockService {
    public static String getStockRate(String message, StockModel model) throws IOException, ParseException {
        URL url = new URL("https://smart-lab.ru/q/shares/"); // + message + "?parammode=2");
        Scanner scanner = new Scanner((InputStream) url.getContent());
        String result = "";
        while (scanner.hasNext()){
            result +=scanner.nextLine();
        }

        System.out.println(result);
        JSONObject object = new JSONObject(result);

        /*imoexbot.model.setCur_ID(object.getInt("Cur_ID"));
        imoexbot.model.setDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(object.getString("Date")));
        imoexbot.model.setCur_Abbreviation(object.getString("Cur_Abbreviation"));
        imoexbot.model.setCur_Scale(object.getInt("Cur_Scale"));
        imoexbot.model.setCur_Name(object.getString("Cur_Name"));
        imoexbot.model.setCur_OfficialRate(object.getDouble("Cur_OfficialRate"));*/

        return "Current price of the stock is " + model.getCur_Abbreviation() + "\n" +
                "on the date: " + getFormatDate(model) + "\n" +
                "is: " + model.getCur_OfficialRate() + " per " + model.getCur_Scale() + " " + model.getCur_Abbreviation();

    }

    private static String getFormatDate(StockModel model) {
        return new SimpleDateFormat("dd MMM yyyy").format(model.getDate());
    }
}