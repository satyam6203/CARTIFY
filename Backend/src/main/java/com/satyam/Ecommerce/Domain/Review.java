package com.satyam.Ecommerce.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.satyam.Ecommerce.Entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String reviewText;

    @Column(nullable = false)
    private double rating;

    @ElementCollection
    private List<String> productImage;

    @JsonIgnore
    @ManyToOne
    private Product product;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private LocalDateTime createdAt=LocalDateTime.now();
}
