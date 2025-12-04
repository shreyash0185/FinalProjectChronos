package com.chronos.auth_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor@Data
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , unique = true)
    private String name;

    //Without many to many mapping
//    @JsonIgnore
//    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
//    private List<UserRole> userRoles;
//Now JPA will handle the Join
}
