package imoexbot.service;

import imoexbot.model.StockModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//import static org.mockito.ArgumentMatchers.matches;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

//@Service
public class StockService
{ 

    public void getStockData() throws IOException, ParseException
    {
        final int STOCK_NUMBER = 0;
        final int STOCK_TICKER = 2;
        HashMap<String, StockModel> stockModels;
        DataLoader dataLoader;

       // String stockNameToCompare = "";
        dataLoader = new DataLoader();
    Elements tags = dataLoader.getPageContent("https://smart-lab.ru/q/shares/");
       // StockModel foundStock = new StockModel();
    StockModel currentStock = null;
       for (Element tag : tags)
        {
        System.out.println(tag.text());
            String stockParams[] = tag.text().split(" ");
            if (isValidStockNumber(stockParams[STOCK_NUMBER]) )
            {
                String stockNameSuggested = "";
                for (int i = STOCK_NUMBER + 1; i < stockParams.length; i++)
                { 
                    
                    if (isValidStockTicker(stockParams[i]))
                    {
                        currentStock = new StockModel();
                        currentStock.setStockTicker(stockParams[STOCK_TICKER]);
                        currentStock.setStockName(stockNameSuggested);
                        break;
                    }
                    stockNameSuggested = stockNameSuggested.concat(stockParams[i]);
                }
                
                currentStock.setStockNumber(Integer.parseInt(stockParams[STOCK_NUMBER]));
                
            }
        }
    }

    public boolean isValidStockNumber(String number)
    {
        final String STOCK_NUMBER_PATTERN = "[0-9]{1,3}";
        return number.matches(STOCK_NUMBER_PATTERN);
    }

     public boolean isValidStockTicker(String ticker)
    {
        final String STOCK_TICKER_PATTERN = "[A-Za-z]{1,5}";
        return ticker.matches(STOCK_TICKER_PATTERN);
    }
               
    private void methodN()
    {
        // if (stockParams[i].matches(STOCK_TICKER_PATTERN)){
        //                 currentStock.setStockTicker(stockParams[i]);
        //                 try {
        //                     currentStock.setStockPrice(Double.valueOf(stockParams[i + 1]));
        //                 }
        //                 catch (NumberFormatException e){
        //                     currentStock.setStockPrice(-9999);
        //                 }
        //                 currentStock.setStockName("");
        //                 // why 3rd loop?
        //                 for (int j = 1; j < i; j++){
        //                     currentStock.setStockName(currentStock.getStockName() + stockParams[j]);
        //                 }
        //             }
        // stockNameToCompare = currentStock.getStockName();
        //         if (stockNameToCompare == null)
        //         {
        //             continue;
        //         }
                
        //         if (stockNameToCompare.equals(message) || currentStock.getStockTicker().equals(message)){
        //             foundStock = currentStock;
        //         }
        //         boolean infoGotFromSite = true;
        //         Document wikidoc = new Document("");
        //         try {
        //             wikidoc = Jsoup.connect("https://ru.wikipedia.org/wiki/" + currentStock.getStockName()).userAgent("Chrome/4.0.249.0 Safari/532.5")
        //                 .referrer("http://www.google.com").get();}
        //         catch (IOException e){
        //             System.out.println(e.getMessage());
        //             infoGotFromSite = false;
        //         }
        //         if (infoGotFromSite){
        //         System.out.println(wikidoc.text().substring(401,800));}

            //}
            /*if (tag.attr("class").equals("trades-table__ticker") || tag.attr("class").equals("trades-table__name")){
                String stockName = tag.text();

                    if (stockName.equals(message)){

                }
            }*/
       // }
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

        //return  (foundStock.getStockName() + System.lineSeparator() + foundStock.getStockTicker() + System.lineSeparator() + foundStock.getStockPrice()); //"Current price of the stock is " + model.getCur_Abbreviation() + "\n" +
                //"on the date: " + "\n" +
                //"is: " + model.getCur_OfficialRate() + " per " + model.getCur_Scale() + " " + model.getCur_Abbreviation();

    }

    // private static String getFormatDate(StockModel model) {
    //     return ""; // new SimpleDateFormat("dd MM yyyy").format(model.getDate());
    // }

}
