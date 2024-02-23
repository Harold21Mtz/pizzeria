package com.platzi.pizzeria.entity;

import com.platzi.pizzeria.audit.AuditTable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "role", schema = "main")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Role extends AuditTable {

    @Id
    @GeneratedValue
    @Column(name = "role_id", nullable = false)
    private UUID roleId;

    @Column(name = "role_name", unique = true, length = 30, nullable = false)
    private String roleName;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @ManyToMany(mappedBy = "roles")
    private List<UserEntity> userEntities;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_permission", schema = "main",
            joinColumns = @JoinColumn(name = "role_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "permission_id", nullable = false),
            uniqueConstraints = @UniqueConstraint(columnNames = {"role_id", "permission_id"}, name = "uc_role_permission")
    )
    private List<Permission> permissions;
}
