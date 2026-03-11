package imoexbot.model;

import java.util.Calendar;

import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.stereotype.Component;

//import java.util.Date;

@Data
//@Entity (name = "index_s")
//@NoArgsConstructor
//@Component
public class StockModel {
    int stockNumber;
    String stockName;
    String stockTicker;
    double stockPrice;
    double dayPercentage;
    int volume;
    Calendar time;
    double weekPercentage;
    double monthPercentage;
    double yearToDayPercentage;
    double yearPercentage;
    double capitalRub;
    double capitalDollar;
    double volumeChange; // (Изм. объёма = Объём текущий / Объём прев.т.д. - 1) * 100

    public int getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(int stockNumber) {
        this.stockNumber = stockNumber;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockTicker() {
        return stockTicker;
    }

    public void setStockTicker(String stockTicker) {
        this.stockTicker = stockTicker;
    }

    public double getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
    }

// Геттеры

    public double getDayPercentage() {
        return dayPercentage;
    }

    public int getVolume() {
        return volume;
    }

    public Calendar getTime() {
        return time;
    }

    public double getWeekPercentage() {
        return weekPercentage;
    }

    public double getMonthPercentage() {
        return monthPercentage;
    }

    public double getYearToDayPercentage() {
        return yearToDayPercentage;
    }

    public double getYearPercentage() {
        return yearPercentage;
    }

    public double getCapitalRub() {
        return capitalRub;
    }

    public double getCapitalDollar() {
        return capitalDollar;
    }

    public double getVolumeChange() {
        return volumeChange;
    }

    // Сеттеры

    public void setDayPercentage(double dayPercentage) {
        this.dayPercentage = dayPercentage;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public void setWeekPercentage(double weekPercentage) {
        this.weekPercentage = weekPercentage;
    }

    public void setMonthPercentage(double monthPercentage) {
        this.monthPercentage = monthPercentage;
    }

    public void setYearToDayPercentage(double yearToDayPercentage) {
        this.yearToDayPercentage = yearToDayPercentage;
    }

    public void setYearPercentage(double yearPercentage) {
        this.yearPercentage = yearPercentage;
    }

    public void setCapitalRub(double capitalRub) {
        this.capitalRub = capitalRub;
    }

    public void setCapitalDollar(double capitalDollar) {
        this.capitalDollar = capitalDollar;
    }

    public void setVolumeChange(double volumeChange) {
        this.volumeChange = volumeChange;
    }

//Double cur_OfficialRate;
}
