package com.satyam.Ecommerce.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SellerReports {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Seller seller;

    private Long totalEarning=0L;

    private Long totalSales=0L;

    private Long totalRefunds=0L;

    private Long totalTex=0L;

    private Long netEarnings=0L;

    private Integer totalOrders=0;

    private Integer canceledOrder=0;

    private Integer totalTransaction=0;

}
