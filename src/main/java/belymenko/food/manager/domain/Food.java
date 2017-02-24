package belymenko.food.manager.domain;

import java.util.Objects;

/**
 * Created by Viacheslav_Belymenko on 22.02.2017.
 */
public class Food extends Product {

    private Double weight;

    public Food() {

    }

    public Food(Double price, String name, Category category, Double weight) {
        super(price, name, category);
        this.weight = weight;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Food food = (Food) o;
        return Objects.equals(weight, food.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), weight);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append(", weight=").append(weight);
        sb.append('}');
        return sb.toString();
    }
}
