package com.iamkhs.fooddelivery.entity;

import com.iamkhs.fooddelivery.annotations.NotEmptyNotBlank;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@DynamicUpdate
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Size(min = 3)
    @NotEmptyNotBlank
    private String name;

    @Email(message = "Please provide a valid email!")
    @Column(unique = true)
    private String email;

    @Size(min = 5, message = "Password must be contains minimum 5 characters")
    private String password;

    @Transient
    @Size(min = 5, message = "Password must be contains minimum 5 characters")
    private String confirmPassword;

    @NotEmptyNotBlank
    private String phone;

    @NotEmptyNotBlank
    private String address;

    private Role role;

    private boolean enable = true;
    private LocalDateTime userRegistrationDate;
    private String verificationCode;

    @OneToMany(mappedBy = "user")
    private List<CustomerOrder> customerOrderList;

}
