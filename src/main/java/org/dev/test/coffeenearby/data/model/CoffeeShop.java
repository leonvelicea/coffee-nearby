package org.dev.test.coffeenearby.data.model;

import org.locationtech.jts.geom.Point;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "coffee_shops")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CoffeeShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Address address;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private SocialData socialData;

    private Point geoLocation;

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof CoffeeShop && Objects.equals(obj, this));
    }
}
