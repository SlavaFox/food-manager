package belymenko.food.manager.service;

import belymenko.food.manager.domain.Category;
import belymenko.food.manager.domain.Food;
import belymenko.food.manager.domain.Product;
import belymenko.food.manager.domain.Purchase;
import belymenko.food.manager.repository.PurchaseRepository;
import belymenko.food.manager.service.impl.PurchaseServiceImpl;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.junit.Assert.assertEquals;

/**
 * Created by Viacheslav_Belymenko on 2/24/2017.
 */
@RunWith(EasyMockRunner.class)
public class PurchaseServiceTest extends EasyMockSupport {

    @TestSubject
    private PurchaseService service = new PurchaseServiceImpl();

    @Mock
    private PurchaseRepository repository;

    private Product initProduct() {
        return new Food(2.2, "name", new Category("category"), 2.3);
    }

    @Test
    public void testAdd() {
        repository.add(initProduct());

        expectLastCall();

        service.add(initProduct());
    }

    @Test
    public void testGetPurchases() throws Exception {
        Map<Date, Purchase> expected = new HashMap<>();
        expected.put(new Date(), new Purchase());

        expect(repository.getPurchases()).andReturn(expected).once();
        replayAll();

        Map<Date, Purchase> result = service.getPurchases();

        assertEquals(expected, result);
        verifyAll();
    }

    @Test
    public void testGetPurchasesBetweenDates() throws Exception {
        Date from = new Date(123);
        Date to = new Date(321);
        Map<Date, Purchase> expected = new HashMap<>();
        expected.put(new Date(231), new Purchase());

        expect(repository.getPurchasesBetweenDates(from, to)).andReturn(expected).once();
        replayAll();

        Map<Date, Purchase> result = service.getPurchasesBetweenDates(from, to);

        assertEquals(expected, result);
        verifyAll();
    }

    @Test
    public void testProductExpenses() throws Exception {
        List<Map.Entry<Category, Double>> expected = new ArrayList<>();
        expected.add(new AbstractMap.SimpleEntry<>(new Category(), 2.2));

        expect(repository.productExpenses(true)).andReturn(expected).once();
        replayAll();

        List<Map.Entry<Category, Double>> result = service.productExpenses(true);

        assertEquals(expected, result);
        verifyAll();
    }

}