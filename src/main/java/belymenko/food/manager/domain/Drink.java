package belymenko.food.manager.domain;

import java.util.Objects;

/**
 * Created by Viacheslav_Belymenko on 22.02.2017.
 */
public class Drink extends Product {

    private Double volume;

    public Drink(){

    }

    public Drink(Double price, String name, Category category, Double volume) {
        super(price, name, category);
        this.volume = volume;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Drink drink = (Drink) o;
        return Objects.equals(volume, drink.volume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), volume);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append(", volume=").append(volume);
        sb.append('}');
        return sb.toString();
    }
}
