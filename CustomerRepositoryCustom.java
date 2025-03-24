import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;

public interface CustomerRepositoryCustom {
    Page<Customer> searchCustomers(String name, String sex, LocalDate birthdayFrom, LocalDate birthdayTo, Pageable pageable);
}
