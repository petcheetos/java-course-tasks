package edu.hw3.task6;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StockMarketTest {

    @Test
    void testMostValuableStockWithOneStock() {
        Stocks stocks = new Stocks();
        StockMarket.Stock stock = new StockMarket.Stock("first", 100);
        stocks.add(stock);
        assertEquals(stock, stocks.mostValuableStock());
    }

    @Test
    void testMostValuableStockWithSomeStock() {
        Stocks stocks = new Stocks();
        StockMarket.Stock stock = new StockMarket.Stock("first", 100);
        stocks.add(stock);
        stocks.add(new StockMarket.Stock("second", 50));
        assertEquals(stock, stocks.mostValuableStock());
    }

    @Test
    void testMostValuableStockWithSomeStockAfterRemove() {
        Stocks stocks = new Stocks();
        StockMarket.Stock firstStock = new StockMarket.Stock("first", 100);
        StockMarket.Stock secondStock = new StockMarket.Stock("second", 50);
        stocks.add(firstStock);
        stocks.add(secondStock);
        stocks.remove(firstStock);
        assertEquals(secondStock, stocks.mostValuableStock());
    }
}
