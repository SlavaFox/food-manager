package belymenko.food.manager.service;

import belymenko.food.manager.domain.Category;
import belymenko.food.manager.repository.CategoryRepository;
import belymenko.food.manager.service.impl.CategoryServiceImpl;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collections;
import java.util.List;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.junit.Assert.assertEquals;

/**
 * Created by Viacheslav_Belymenko on 2/24/2017.
 */
@RunWith(EasyMockRunner.class)
public class CategoryServiceTest extends EasyMockSupport {

    @TestSubject
    private CategoryService service = new CategoryServiceImpl();

    @Mock
    private CategoryRepository repository;

    @Test
    public void testGetFoodCategories() throws Exception {
        List<Category> expected = Collections.singletonList(new Category());

        expect(repository.getFoodCategories()).andReturn(expected).once();
        replayAll();

        List<Category> result = service.getFoodCategories();

        assertEquals(expected, result);
        verifyAll();
    }

    @Test
    public void testGetDrinkCategories() throws Exception {
        List<Category> expected = Collections.singletonList(new Category());

        expect(repository.getDrinkCategories()).andReturn(expected).once();
        replayAll();

        List<Category> result = service.getDrinkCategories();

        assertEquals(expected, result);
        verifyAll();
    }

    @Test
    public void testAddFoodCategory() throws Exception {
        repository.addFoodCategory("food");

        expectLastCall();

        service.addFoodCategory("food");
    }

    @Test
    public void testAddDrinkCategory() throws Exception {
        repository.addDrinkCategory("drink");

        expectLastCall();

        service.addDrinkCategory("drink");
    }

}