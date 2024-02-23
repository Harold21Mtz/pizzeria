package com.platzi.pizzeria.entity;

import com.platzi.pizzeria.audit.AuditTable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "permission", schema = "main")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Permission extends AuditTable {

    @Id
    @GeneratedValue
    @Column(name = "permission_id", nullable = false)
    private UUID permissionId;

    @Column(name = "permission_name", unique = true, length = 30, nullable = false)
    private String permissionName;

    @Transient
    @Column(name = "is_checked", nullable = false)
    private boolean isChecked;

    @ManyToMany(mappedBy = "permissions")
    @Fetch(FetchMode.SUBSELECT)
    private List<Role> roles;

    @Column(name = "module_id", nullable = false)
    private UUID moduleId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "module_id", nullable = false,insertable = false, updatable = false)
    private Module module;
}
