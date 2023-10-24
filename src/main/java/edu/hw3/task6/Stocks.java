package edu.hw3.task6;

import java.util.PriorityQueue;
import java.util.Queue;

public class Stocks implements StockMarket {

    private Queue stocks;

    public Stocks() {
        stocks = new PriorityQueue<Stock>((obj1, obj2) -> obj2.price() - obj1.price());
    }

    @Override
    public void add(Stock stock) {
        stocks.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        stocks.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return (Stock) stocks.peek();
    }
}
