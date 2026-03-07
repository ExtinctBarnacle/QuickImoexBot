package imoexbot.service;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

// Загрузка данных с сайта Смартлаб
public class DataLoader
{
    
    public Elements getPageContent(String address)
    {
        Document hTMLDocument = null;
        try
        {
            hTMLDocument = getPageText(address);
        }
        catch(IOException e)
        {
            System.out.println("Webpage loading error: " + e.getMessage());
        }

        Elements tags = parseHTMLDocument(hTMLDocument, "tr");

        //lock-in
        return tags;
    }

    private Document getPageText(String address) throws IOException
    {
        return Jsoup.connect(address).userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com").get();
    }

    private Elements parseHTMLDocument(Document hTMLDocument, String tagToSearch)
    {
        return hTMLDocument.select(tagToSearch);

    }
}
