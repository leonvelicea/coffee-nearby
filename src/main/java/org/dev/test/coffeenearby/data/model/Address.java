package org.dev.test.coffeenearby.data.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "addresses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "country", nullable = false, length = 150)
    private String country;
    @Column(name = "city", nullable = false, length = 150)
    private String city;
    @Column(name = "street", nullable = false, length = 150)
    private String street;
    @Column(name = "number", nullable = false, length = 50)
    private String number;
    @Column(name = "postal_code", nullable = false, length = 20)
    private String postalCode;

    @Override
    public int hashCode() {
        return Objects.hash(id, country, city, street, number, postalCode);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof CoffeeShop && Objects.equals(obj, this));
    }
}
