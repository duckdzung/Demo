// filepath: src/main/java/net/fujinet/cs/repository/CustomerRepository.java
package net.fujinet.cs.repository;

import net.fujinet.cs.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByDeleteYmdIsNull();
    List<Customer> findByNameContainingAndSexAndBirthdayBetweenAndDeleteYmdIsNull(String name, String sex, LocalDate birthdayFrom, LocalDate birthdayTo);
    List<Customer> findByDeleteYmdIsNotNull();
}