package belymenko.food.manager.repository;

import belymenko.food.manager.domain.Category;

import java.util.List;

/**
 * Created by Viacheslav_Belymenko on 22.02.2017.
 */
public interface CategoryRepository {

    /**
     * Get all food categories.
     *
     * @return list of food categories.
     */
    List<Category> getFoodCategories();

    /**
     * Get all drink categories.
     *
     * @return list of drink categories.
     */
    List<Category> getDrinkCategories();

    /**
     * Add new food categories.
     *
     * @param category category.
     */
    void addFoodCategory(String category);

    /**
     * Add new drink categories.
     *
     * @param category category.
     */
    void addDrinkCategory(String category);
}
