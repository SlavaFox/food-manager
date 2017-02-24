package belymenko.food.manager.service.impl;

import belymenko.food.manager.domain.Category;
import belymenko.food.manager.domain.Product;
import belymenko.food.manager.domain.Purchase;
import belymenko.food.manager.repository.PurchaseRepository;
import belymenko.food.manager.service.PurchaseService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Viacheslav_Belymenko on 2/23/2017.
 */
public class PurchaseServiceImpl implements PurchaseService {

    private PurchaseRepository repository;

    public PurchaseServiceImpl(){

    }

    public PurchaseServiceImpl(PurchaseRepository repository) {
        this.repository = repository;
    }

    @Override
    public void add(Product product) {
        repository.add(product);
    }

    @Override
    public Map<Date, Purchase> getPurchases() {
        return repository.getPurchases();
    }

    @Override
    public Map<Date, Purchase> getPurchasesBetweenDates(Date from, Date to) {
        return repository.getPurchasesBetweenDates(from, to);
    }

    @Override
    public List<Map.Entry<Category, Double>> productExpenses(boolean desc) {
        return repository.productExpenses(desc);
    }
}
