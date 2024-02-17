package com.platzi.pizzeria.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.platzi.pizzeria.persistence.audit.AuditTable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Entity
@Table(name = "order_item", schema = "main")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class OrderItem extends AuditTable {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id", nullable = false)
    private UUID orderItemId;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false, referencedColumnName = "order_id", insertable = false, updatable = false)
    @JsonIgnore
    private Order order;

    @ManyToOne
    @JoinColumn(name = "pizza_id", nullable = false, referencedColumnName = "pizza_id", insertable = false, updatable = false)
    @JsonIgnore
    private Pizza pizza;

    @Column(name = "order_quantity", nullable = false)
    private Double orderQuantity;

    @Column(name = "order_price", nullable = false)
    private Double orderPrice;
}
