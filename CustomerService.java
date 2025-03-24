import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Page<Customer> getCustomers(String name, String sex, LocalDate birthdayFrom, LocalDate birthdayTo, int page, int size, String sortField, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortField);
        Pageable pageable = PageRequest.of(page, size, sort);
        return customerRepository.searchCustomers(name, sex, birthdayFrom, birthdayTo, pageable);
    }
}
