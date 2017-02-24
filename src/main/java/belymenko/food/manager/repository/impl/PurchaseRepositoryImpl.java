package belymenko.food.manager.repository.impl;

import belymenko.food.manager.domain.Category;
import belymenko.food.manager.domain.Drink;
import belymenko.food.manager.domain.Food;
import belymenko.food.manager.domain.Product;
import belymenko.food.manager.domain.Purchase;
import belymenko.food.manager.repository.PurchaseRepository;
import org.apache.commons.lang.time.DateUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.jsontype.NamedType;
import org.codehaus.jackson.type.TypeReference;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Viacheslav_Belymenko on 22.02.2017.
 */
public class PurchaseRepositoryImpl implements PurchaseRepository {

    private Map<Date, Purchase> purchases;
    private ObjectMapper mapper;
    private File file;

    public PurchaseRepositoryImpl(String path) {
        this.file = new File(path + "\\purchase.json");
        this.mapper = new ObjectMapper();
        this.mapper.registerSubtypes(new NamedType(Drink.class, "Drink"),
                new NamedType(Food.class, "Food"));
        this.purchases = initRepository();
    }

    @Override
    public void add(Product product) {
        Date date = DateUtils.truncate(new Date(), Calendar.DATE);
        if (purchases.get(date) == null) {
            purchases.put(date, new Purchase(product));
        } else {
            purchases.get(date).addProduct(product);
        }
        save();
    }

    @Override
    public Map<Date, Purchase> getPurchases() {
        return purchases;
    }

    @Override
    public Map<Date, Purchase> getPurchasesBetweenDates(Date from, Date to) {
        return purchases.entrySet().stream().filter(entry -> entry.getKey().after(from)
                && entry.getKey().before(to)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public List<Map.Entry<Category, Double>> productExpenses(boolean desc) {
        List<Map<Category, Double>> collect = this.purchases
                .entrySet()
                .stream()
                .map(entry -> entry.getValue().getProducts().stream().collect(Collectors.groupingBy(Product::getCategory,
                                                Collectors.summingDouble(Product::getPrice))))
                .collect(Collectors.toList());
        return getWestedMoneyByCategory(collect, desc);
    }

    private Map<Date, Purchase> initRepository() {
        try {
            return mapper.readValue(file, new TypeReference<Map<Date, Purchase>>() {
            });
        } catch (FileNotFoundException e) {
            System.out.println("You indicate incorrect path to file with purchases! Pleas check it!");
            return new HashMap<>();
        } catch (IOException e) {
            System.out.println("You have incorrect structure of file! Pleas check documentation!");
            return new HashMap<>();
        }
    }

    private void save() {
        try {
            mapper.writeValue(file, purchases);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Map.Entry<Category, Double>> getWestedMoneyByCategory(List<Map<Category, Double>> maps, boolean desc) {
        Map<Category, Double> map = new HashMap<>();
        maps.forEach(cat -> cat.forEach((category, aDouble) -> map.merge(category, aDouble, Double::sum)));
        return map.entrySet().stream().sorted((o1, o2) -> desc ? Double.compare(o1.getValue(), o2.getValue()) : Double.compare(o2.getValue(), o1.getValue())).collect(Collectors.toList());
    }
}
