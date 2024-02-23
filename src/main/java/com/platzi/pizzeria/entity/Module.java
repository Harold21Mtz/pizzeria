package com.platzi.pizzeria.entity;

import com.platzi.pizzeria.audit.AuditTable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "module", schema = "main")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Module extends AuditTable {

    @Id
    @GeneratedValue
    @Column(name = "module_id", nullable = false)
    private UUID moduleId;

    @Column(name = "module_name", unique = true, length = 30, nullable = false)
    private String moduleName;

    @Column(name = "module_description", unique = true, length = 50, nullable = false)
    private String moduleDescription;

    @OneToMany(mappedBy = "module")
    private List<Permission> permissions;

}
