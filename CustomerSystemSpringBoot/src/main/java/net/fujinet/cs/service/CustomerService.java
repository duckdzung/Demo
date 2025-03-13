// filepath: src/main/java/net/fujinet/cs/service/CustomerService.java
package net.fujinet.cs.service;

import net.fujinet.cs.entity.Customer;
import net.fujinet.cs.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers(String name, String sex, LocalDate birthdayFrom, LocalDate birthdayTo) {
        return customerRepository.findByNameContainingAndSexAndBirthdayBetweenAndDeleteYmdIsNull(name, sex, birthdayFrom, birthdayTo);
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            customer.setDeleteYmd(LocalDate.now());
            customerRepository.save(customer);
        }
    }

    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void deleteMultipleCustomers(List<Long> ids) {
        List<Customer> customers = customerRepository.findAllById(ids);
        for (Customer customer : customers) {
            customer.setDeleteYmd(LocalDate.now());
        }
        customerRepository.saveAll(customers);
    }
}