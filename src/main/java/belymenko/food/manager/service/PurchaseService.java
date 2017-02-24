package belymenko.food.manager.service;

import belymenko.food.manager.domain.Category;
import belymenko.food.manager.domain.Product;
import belymenko.food.manager.domain.Purchase;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Viacheslav_Belymenko on 2/23/2017.
 */
public interface PurchaseService {
    /**
     * Add product to purchase.
     *
     * @param product product to add.
     */
    void add(Product product);

    /**
     * Get all purchases.
     *
     * @return map of purchases.
     */
    Map<Date, Purchase> getPurchases();

    /**
     * Get purchases between to dates.
     *
     * @param from start date.
     * @param to   end date.
     * @return map of purchase between dates.
     */
    Map<Date, Purchase> getPurchasesBetweenDates(Date from, Date to);


    /**
     * Get sort product expenses.
     *
     * @param desc false if sort by increment price, else - decrement.
     * @return
     */
    List<Map.Entry<Category, Double>> productExpenses(boolean desc);
}
