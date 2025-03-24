import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/search")
    public Map<String, Object> searchCustomers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String sex,
            @RequestParam(required = false) String birthdayFrom,
            @RequestParam(required = false) String birthdayTo,
            @RequestParam(defaultValue = "0") int start,
            @RequestParam(defaultValue = "10") int length,
            @RequestParam(defaultValue = "name") String orderColumn,
            @RequestParam(defaultValue = "asc") String orderDir) {

        int page = start / length;
        LocalDate from = (birthdayFrom != null && !birthdayFrom.isEmpty()) ? LocalDate.parse(birthdayFrom, DateTimeFormatter.ISO_DATE) : null;
        LocalDate to = (birthdayTo != null && !birthdayTo.isEmpty()) ? LocalDate.parse(birthdayTo, DateTimeFormatter.ISO_DATE) : null;

        Page<Customer> customerPage = customerService.getCustomers(name, sex, from, to, page, length, orderColumn, orderDir);

        Map<String, Object> response = new HashMap<>();
        response.put("draw", 1);
        response.put("recordsTotal", customerPage.getTotalElements());
        response.put("recordsFiltered", customerPage.getTotalElements());
        response.put("data", customerPage.getContent());

        return response;
    }
}
