package com.platzi.pizzeria.entity;

import com.platzi.pizzeria.audit.AuditTable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user", schema = "main")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class UserEntity extends AuditTable {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "user_name", nullable = false, length = 38,unique = true)
    private String userName;

    @Column(name = "user_password", nullable = false)
    private String userPassword;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "login_attempts")
    private int loginAttempts;

    @Column(name = "login_attempts_mfa")
    private int loginAttemptsMfa;

    @Column(name = "code_verification")
    private String codeVerification;

    @Column(name = "created_code_verification")
    private LocalDateTime createCodeVerification;

    @Column(name = "locked", nullable = false)
    private boolean locked;

    @Column(name = "user_administrator")
    private boolean userAdministrator;

    @OneToOne(mappedBy = "userEntity")
    private Person person;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role", schema = "main",
            joinColumns = @JoinColumn(name = "user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false),
            uniqueConstraints = @UniqueConstraint(name = "uc_user_role", columnNames = {"user_id", "role_id"}))
    private List<Role> roles;

    public void updateLoginAttempts(int attempts){
        this.loginAttempts = attempts;
    }

    public void updateLoginAttemptsMfa(int attemptsMfa){
        this.loginAttemptsMfa = attemptsMfa;
    }

    public void updateLocked(boolean locked){
        this.locked = locked;
    }

    public void addCodeVerification(String codeVerification){
        this.codeVerification = codeVerification;
        this.createCodeVerification = LocalDateTime.now();
    }

    public void resetCodeVerification(){
        this.codeVerification = null;
    }

}
