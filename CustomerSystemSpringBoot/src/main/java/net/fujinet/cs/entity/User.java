// filepath: src/main/java/net/fujinet/cs/entity/User.java
package net.fujinet.cs.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private LocalDate deleteYmd;

    // Getters and setters
}