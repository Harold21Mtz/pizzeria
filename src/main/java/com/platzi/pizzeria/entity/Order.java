package com.platzi.pizzeria.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.platzi.pizzeria.audit.AuditTable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pizza_order", schema = "main")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Order extends AuditTable {

    @Id
    @GeneratedValue
    @Column(name = "order_id", nullable = false)
    private UUID orderId;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    @OrderBy("orderPrice DESC")
    private List<OrderItem> orderItems;

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "order_total", nullable = false)
    private Double orderTotal;

    @Column(name = "order_method", nullable = false, columnDefinition = "CHAR(1)")
    private String orderMethod;

    @Column(name = "order_additional_notes", length = 200)
    private String orderAdditionalNotes;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore
    private Person person;

}
