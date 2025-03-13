// filepath: src/main/java/net/fujinet/cs/repository/UserRepository.java
package net.fujinet.cs.repository;

import net.fujinet.cs.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPasswordAndDeleteYmdIsNull(String username, String password);
}