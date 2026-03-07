package imoexbot.service;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

// Загрузка данных с сайта Смартлаб
public class DataLoader {
    
    public static Document getPageContent(String telegramMessage){
        try{
        Document hTMLDocument = getPageText("https://smart-lab.ru/q/shares/");
        }
        catch(IOException e){
            System.out.println("Webpage loading error: " + e.getMessage());
        }
        return pageText;
    }

    private static Document getPageText(String address) throws IOException
    {
        //URL url = new URL("https://smart-lab.ru/q/shares/"); // + message + "?parammode=2");
        return Jsoup.connect(address).userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com").get();
    }

    private static parseHTMLDocument(Document hTMLDocument){
        
    }
}
