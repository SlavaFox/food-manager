package belymenko.food.manager.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Viacheslav_Belymenko on 22.02.2017.
 */
public class Purchase {

    private List<Product> products;
    private Double totalSum;

    public Purchase() {
    }

    public Purchase(Product product) {
        this.products = new ArrayList<>();
        this.products.add(product);
        this.totalSum = calcTotalSum();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setTotalSum(Double totalSum) {
        this.totalSum = totalSum;
    }

    public void addProduct(Product product) {
        this.products.add(product);
        this.totalSum = calcTotalSum();
    }

    public Double getTotalSum() {
        return totalSum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return Objects.equals(products, purchase.products) &&
                Objects.equals(totalSum, purchase.totalSum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products, totalSum);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Purchase{");
        sb.append("products=").append(products);
        sb.append(", totalSum=").append(totalSum);
        sb.append('}');
        return sb.toString();
    }

    private Double calcTotalSum() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }
}
