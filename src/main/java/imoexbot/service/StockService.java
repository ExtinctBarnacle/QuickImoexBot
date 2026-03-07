package imoexbot.service;

import imoexbot.model.StockModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.text.ParseException;

//@Service
public class StockService { 

    public String getStockRate(String message, StockModel model) throws IOException, ParseException {
        DataLoader dataLoader;
        String stockNameToCompare = "";
        dataLoader = new DataLoader();
        Elements tags = dataLoader.getPageContent("https://smart-lab.ru/q/shares/");
        StockModel foundStock = new StockModel();
        for (Element tag : tags){
            StockModel curStock = new StockModel();
            String stockParams[] = tag.text().split(" ");
            if (stockParams[0].matches("[0-9]{1,3}")){
                for (int i = 0; i < stockParams.length; i++){
                    if (stockParams[i].matches("[A-Za-z]{4,5}")){
                        curStock.setStockTicker(stockParams[i]);
                        try {
                            curStock.setStockPrice(Double.valueOf(stockParams[i + 1]));
                        }
                        catch (NumberFormatException e){
                            curStock.setStockPrice(-9999);
                        }
                        curStock.setStockName("");
                        for (int j=1;j<i;j++){
                            curStock.setStockName(curStock.getStockName()+stockParams[j]);
                        }
                    }
                }
                
                stockNameToCompare = curStock.getStockName();
                if (stockNameToCompare == null)
                {
                    continue;
                }
                
                if (stockNameToCompare.equals(message) || curStock.getStockTicker().equals(message)){
                    foundStock = curStock;
                }
                boolean infoGotFromSite = true;
                Document wikidoc = new Document("");
                try {
                    wikidoc = Jsoup.connect("https://ru.wikipedia.org/wiki/" + curStock.getStockName()).userAgent("Chrome/4.0.249.0 Safari/532.5")
                        .referrer("http://www.google.com").get();}
                catch (IOException e){
                    System.out.println(e.getMessage());
                    infoGotFromSite = false;
                }
                if (infoGotFromSite){
                System.out.println(wikidoc.text().substring(401,800));}

            }
            /*if (tag.attr("class").equals("trades-table__ticker") || tag.attr("class").equals("trades-table__name")){
                String stockName = tag.text();

                    if (stockName.equals(message)){

                }
            }*/
        }
        //Scanner scanner = new Scanner((InputStream) url.getContent());
        //String result = "";
        //while (scanner.hasNext()){
        //    result +=scanner.nextLine();
        //}

        //System.out.println(result);
        //JSONObject object = new JSONObject(result);

        /*imoexbot.model.setCur_ID(object.getInt("Cur_ID"));
        imoexbot.model.setDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(object.getString("Date")));
        imoexbot.model.setCur_Abbreviation(object.getString("Cur_Abbreviation"));
        imoexbot.model.setCur_Scale(object.getInt("Cur_Scale"));
        imoexbot.model.setCur_Name(object.getString("Cur_Name"));
        imoexbot.model.setCur_OfficialRate(object.getDouble("Cur_OfficialRate"));*/

        return  (foundStock.getStockName() + System.lineSeparator() + foundStock.getStockTicker() + System.lineSeparator() + foundStock.getStockPrice()); //"Current price of the stock is " + model.getCur_Abbreviation() + "\n" +
                //"on the date: " + "\n" +
                //"is: " + model.getCur_OfficialRate() + " per " + model.getCur_Scale() + " " + model.getCur_Abbreviation();

    }

    // private static String getFormatDate(StockModel model) {
    //     return ""; // new SimpleDateFormat("dd MM yyyy").format(model.getDate());
    // }
}