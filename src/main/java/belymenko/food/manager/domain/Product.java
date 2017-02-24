package belymenko.food.manager.domain;

import org.codehaus.jackson.annotate.JsonTypeInfo;

import java.util.Objects;

/**
 * Created by Viacheslav_Belymenko on 22.02.2017.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY, property = "@type")
public abstract class Product {

    private Double price;
    private String name;
    private Category category;

    public Product() {
    }

    public Product(Double price, String name, Category category) {
        this.price = price;
        this.name = name;
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(price, product.price) &&
                Objects.equals(name, product.name) &&
                Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, name, category);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("price=").append(price);
        sb.append(", name=").append(name);
        sb.append(", category=").append(category.getCategory());
        return sb.toString();
    }
}
