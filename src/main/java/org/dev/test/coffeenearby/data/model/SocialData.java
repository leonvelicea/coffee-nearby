package org.dev.test.coffeenearby.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "social_data")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SocialData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "telephone", nullable = false, length = 15)
    private String telephone;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "instagram_url")
    private String instagramUrl;

    @Column(name = "facebook_url")
    private String facebookUrl;

    @Column(name = "trip_advisor_url")
    private String tripAdvisorUrl;

    /** Coffee shop instance */
    @OneToOne
    @JoinColumn(name = "coffee_shop_id")
    private CoffeeShop coffeeShop;

    @Override
    public int hashCode() {
        return Objects.hash(id, telephone, email, instagramUrl, facebookUrl, tripAdvisorUrl);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof CoffeeShop && Objects.equals(obj, this));
    }
}
