import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImportCsvService {

    @Autowired
    private CustomerRepository customerRepository;

    public ImportResult importCsv(MultipartFile file) {
        ImportResult result = new ImportResult();
        
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBean<CustomerCsvDto> csvToBean = new CsvToBeanBuilder<CustomerCsvDto>(reader)
                    .withType(CustomerCsvDto.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            
            List<CustomerCsvDto> customers = csvToBean.parse();
            for (CustomerCsvDto dto : customers) {
                if (!isValid(dto)) {
                    result.addError(dto);
                    continue;
                }
                
                Customer customer = customerRepository.findById(dto.getId()).orElse(new Customer());
                customer.updateFromDto(dto);
                customerRepository.save(customer);
                
                if (customer.getId() == null) {
                    result.incrementAdded();
                } else {
                    result.incrementUpdated();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private boolean isValid(CustomerCsvDto dto) {
        return dto.getName() != null && !dto.getName().isEmpty() && dto.getEmail().contains("@");
    }
}

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/import")
public class ImportCsvController {

    @Autowired
    private ImportCsvService importCsvService;

    @PostMapping("/upload")
    public ResponseEntity<ImportResult> uploadCsv(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty() || !file.getOriginalFilename().endsWith(".csv")) {
            return ResponseEntity.badRequest().body(new ImportResult("File không hợp lệ!"));
        }
        ImportResult result = importCsvService.importCsv(file);
        return ResponseEntity.ok(result);
    }
}

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(@ModelAttribute CustomerSearchDto searchDto,
                                @RequestParam(defaultValue = "0") int page,
                                Model model) {
        Page<Customer> customerPage = customerService.searchCustomers(searchDto, PageRequest.of(page, 10));
        model.addAttribute("customers", customerPage.getContent());
        model.addAttribute("totalPages", customerPage.getTotalPages());
        model.addAttribute("currentPage", page);
        return "customer/list";
    }
}

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Page<Customer> searchCustomers(CustomerSearchDto searchDto, PageRequest pageRequest) {
        return customerRepository.findAll(CustomerPredicate.search(searchDto), pageRequest);
    }
}

import com.querydsl.core.types.dsl.BooleanExpression;

public class CustomerPredicate {
    public static BooleanExpression search(CustomerSearchDto searchDto) {
        QCustomer qCustomer = QCustomer.customer;
        BooleanExpression predicate = qCustomer.isNotNull();
        
        if (searchDto.getName() != null && !searchDto.getName().isEmpty()) {
            predicate = predicate.and(qCustomer.name.containsIgnoreCase(searchDto.getName()));
        }
        if (searchDto.getGender() != null && !searchDto.getGender().isEmpty()) {
            predicate = predicate.and(qCustomer.gender.eq(searchDto.getGender()));
        }
        if (searchDto.getBirthDateFrom() != null && !searchDto.getBirthDateFrom().isEmpty()) {
            predicate = predicate.and(qCustomer.birthDate.goe(searchDto.getBirthDateFrom()));
        }
        if (searchDto.getBirthDateTo() != null && !searchDto.getBirthDateTo().isEmpty()) {
            predicate = predicate.and(qCustomer.birthDate.loe(searchDto.getBirthDateTo()));
        }
        
        return predicate;
    }
}
