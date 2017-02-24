package belymenko.food.manager.repository.impl;

import belymenko.food.manager.domain.Category;
import belymenko.food.manager.repository.CategoryRepository;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Viacheslav_Belymenko on 22.02.2017.
 */
public class CategoryRepositoryImpl implements CategoryRepository {

    private List<Category> foodCategories;
    private List<Category> drinkCategories;
    private ObjectMapper mapper;
    private File drinkCategoryFile;
    private File foodCategoryFile;

    public CategoryRepositoryImpl(String path) {
        this.drinkCategoryFile = new File(path + "\\drink-category.json");
        this.foodCategoryFile = new File(path + "\\food-category.json");
        this.mapper = new ObjectMapper();
        this.foodCategories = initFoodRepository().stream().distinct().collect(Collectors.toList());
        this.drinkCategories = initDrinkRepository().stream().distinct().collect(Collectors.toList());
    }

    @Override
    public List<Category> getFoodCategories() {
        return foodCategories;
    }

    @Override
    public List<Category> getDrinkCategories() {
        return drinkCategories;
    }

    @Override
    public void addFoodCategory(String category) {
        foodCategories.add(new Category(category));
        save(foodCategoryFile, foodCategories);
    }

    @Override
    public void addDrinkCategory(String category) {
        drinkCategories.add(new Category(category));
        save(drinkCategoryFile, drinkCategories);
    }

    private List<Category> initFoodRepository() {
        try {
            return mapper.readValue(foodCategoryFile, new TypeReference<List<Category>>() {
            });
        } catch (FileNotFoundException e) {
            System.out.println("You indicate incorrect path to file with food categories! Pleas check it!");
            return new ArrayList<>();
        } catch (IOException e) {
            System.out.println("You have incorrect structure of file! Pleas check documentation!");
            return new ArrayList<>();
        }
    }

    private List<Category> initDrinkRepository() {
        try {
            return mapper.readValue(drinkCategoryFile, new TypeReference<List<Category>>() {
            });
        } catch (FileNotFoundException e) {
            System.out.println("You indicate incorrect path to file with drink categories! Pleas check it!");
            return new ArrayList<>();
        } catch (IOException e) {
            System.out.println("You have incorrect structure of file! Pleas check documentation!");
            return new ArrayList<>();
        }
    }

    private void save(File file, List<Category> categories) {
        try {
            mapper.writeValue(file, categories);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
