package belymenko.food.manager;

import belymenko.food.manager.controller.Controller;
import belymenko.food.manager.repository.CategoryRepository;
import belymenko.food.manager.repository.PurchaseRepository;
import belymenko.food.manager.repository.impl.CategoryRepositoryImpl;
import belymenko.food.manager.repository.impl.PurchaseRepositoryImpl;
import belymenko.food.manager.service.CategoryService;
import belymenko.food.manager.service.PurchaseService;
import belymenko.food.manager.service.impl.CategoryServiceImpl;
import belymenko.food.manager.service.impl.PurchaseServiceImpl;

/**
 * Created by Viacheslav_Belymenko on 22.02.2017.
 */
public class Demo {

    private CategoryRepository categoryRepository;
    private CategoryService categoryService;
    private PurchaseRepository purchaseRepository;
    private PurchaseService purchaseService;

    public static void main(String[] args) throws Exception {
        Demo demo = new Demo();
        demo.init(args[0]);
        demo.start();
    }

    private void init(String pathToFiles) {
        categoryRepository = new CategoryRepositoryImpl(pathToFiles);
        purchaseRepository = new PurchaseRepositoryImpl(pathToFiles);
        purchaseService = new PurchaseServiceImpl(purchaseRepository);
        categoryService = new CategoryServiceImpl(categoryRepository);
    }

    private void start() {
        Controller controller = new Controller(purchaseService, categoryService);
        controller.startController();
    }
}
