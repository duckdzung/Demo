// filepath: src/main/java/net/fujinet/cs/entity/Customer.java
package net.fujinet.cs.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String sex;
    private LocalDate birthday;
    private LocalDate deleteYmd;

    // Getters and setters
}