package com.platzi.pizzeria.entity;

import com.platzi.pizzeria.audit.AuditTable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "person", schema = "main")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Person extends AuditTable {

    @Id
    private UUID personId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @Column(name = "person_name", unique = true, length = 60, nullable = false)
    private String personName;

    @Column(name = "person_last_name", unique = true, length = 60, nullable = false)
    private String personLastName;

    @Column(name = "person_document", unique = true, length = 15, nullable = false)
    private String personDocument;

    @Column(name = "person_address", unique = true, length = 100, nullable = false)
    private String personAddress;

    @Column(name = "person_email", unique = true, length = 50, nullable = false)
    private String personEmail;

    @Column(name = "person_phone_number", unique = true, length = 20, nullable = false)
    private String personPhoneNumber;

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
    private List<Order> orders;

}
