package com.platzi.pizzeria.persistence.entity;

import com.platzi.pizzeria.persistence.audit.AuditTable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "customer", schema = "main")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Customer extends AuditTable {

    @Id
    @GeneratedValue
    @Column(name = "customer_id", nullable = false)
    private UUID customerId;

    @Column(name = "customer_name", unique = true, length = 60, nullable = false)
    private String customerName;

    @Column(name = "customer_address", unique = true, length = 100, nullable = false)
    private String customerAddress;

    @Column(name = "customer_email", unique = true, length = 50, nullable = false)
    private String customerEmail;

    @Column(name = "customer_phone_number", unique = true, length = 20, nullable = false)
    private String customerPhoneNumber;

    @Column(name = "customer_document", unique = true, length = 20, nullable = false)
    private String customerDocument;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<Order> orders;

}
