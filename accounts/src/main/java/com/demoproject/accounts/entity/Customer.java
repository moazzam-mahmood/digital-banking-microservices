package com.demoproject.accounts.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter@Setter@ToString@AllArgsConstructor@NoArgsConstructor
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private long customerId;

    private String name;

    private String email;

    private String mobileNumber;




}
