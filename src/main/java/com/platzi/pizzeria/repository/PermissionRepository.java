package com.platzi.pizzeria.repository;

import com.platzi.pizzeria.entity.Permission;
import com.platzi.pizzeria.entity.projection.PermissionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PermissionRepository extends JpaRepository<Permission, UUID> {

    @Query(value = " SELECT p.permission_name " +
            " FROM main.user u inner join main.user_role ur on u.user_id = ur.user_id inner join main.role r on ur.role_id  = r.role_id " +
            " inner join main.role_permission rp on r.role_id = rp.role_id inner join main.permission p on rp.permission_id = p.permission_id " +
            " where u.user_name = :userName ", nativeQuery = true)
    List<String> getAllPermissionByUserName(@Param("userName") String username);
}
