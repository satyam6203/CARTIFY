package com.satyam.Ecommerce.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.satyam.Ecommerce.Domain.Address;
import com.satyam.Ecommerce.Domain.Coupon;
import com.satyam.Ecommerce.constants.USER_ROLE;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String Password;

    private String email;

    private String fullName;
    private String  mobile;
    private USER_ROLE role=USER_ROLE.ROLE_CUSTOMER;

    @OneToMany
    private Set<Address> address=new HashSet<>();

    @ManyToMany
    @JsonIgnore//it is user for ignore (not seem) in the fronted
    private Set<Coupon> usedCoupon=new HashSet<>();
}
