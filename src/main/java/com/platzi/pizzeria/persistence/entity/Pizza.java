package com.platzi.pizzeria.persistence.entity;

import com.platzi.pizzeria.persistence.audit.AuditPizzaListener;
import com.platzi.pizzeria.persistence.audit.AuditTable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pizza", schema = "main")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners({AuditingEntityListener.class, AuditPizzaListener.class})
public class Pizza extends AuditTable implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "pizza_id", nullable = false)
    private UUID pizzaId;

    @Column(name = "pizza_name", unique = true, length = 30, nullable = false)
    private String pizzaName;

    @Column(name = "pizza_description", nullable = false, length = 150)
    private String pizzaDescription;

    @Column(name = "pizza_price", nullable = false)
    private Double pizzaPrice;

    @Column(name = "pizza_vegetarian")
    private Boolean pizzaVegetarian;

    @Column(name = "pizza_vegan")
    private Boolean pizzaVegan;

    @Column(name = "pizza_available")
    private Boolean pizzaAvailable;

    @OneToMany(mappedBy = "pizza", fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;

    @Override
    public String toString() {
        return "Pizza{" +
                "pizzaId=" + pizzaId +
                ", pizzaName='" + pizzaName + '\'' +
                ", pizzaDescription='" + pizzaDescription + '\'' +
                ", pizzaPrice=" + pizzaPrice +
                ", pizzaVegetarian=" + pizzaVegetarian +
                ", pizzaVegan=" + pizzaVegan +
                ", pizzaAvailable=" + pizzaAvailable +
                '}';
    }
}
