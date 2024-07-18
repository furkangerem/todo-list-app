package com.furkangerem.todo_list_app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Table(name = "users")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    @Column(name = "first_name", length = 32, nullable = false)
    private String firstName;
    @Column(name = "last_name", length = 64, nullable = false)
    private String lastName;
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "user_name", length = 32, nullable = false, unique = true)
    private String userName;
    @Column(name = "password", length = 256, nullable = false)
    private String password;

    @Column(name = "email", length = 256, nullable = false, unique = true)
    private String email;
}
