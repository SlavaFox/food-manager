package belymenko.food.manager.service.impl;

import belymenko.food.manager.domain.Category;
import belymenko.food.manager.repository.CategoryRepository;
import belymenko.food.manager.service.CategoryService;

import java.util.List;

/**
 * Created by Viacheslav_Belymenko on 2/23/2017.
 */
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository repository;

    public CategoryServiceImpl(){

    }

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Category> getFoodCategories() {
        return repository.getFoodCategories();
    }

    @Override
    public List<Category> getDrinkCategories() {
        return repository.getDrinkCategories();
    }

    @Override
    public void addFoodCategory(String category) {
        repository.addFoodCategory(category);
    }

    @Override
    public void addDrinkCategory(String category) {
        repository.addDrinkCategory(category);
    }
}
