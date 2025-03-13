// filepath: src/main/java/net/fujinet/cs/controller/CustomerController.java

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import net.fujinet.cs.service.CustomerService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class T002Controller {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model model) {
        List<Customer> customers = customerService.getAllCustomers("", "", null, null);
        model.addAttribute("customers", customers);
        return "customer-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("customerDTO", new CustomerDTO());
        return "customer-form";
    }

    @PostMapping("/add")
    public String addCustomer(@Valid @ModelAttribute("customerDTO") CustomerDTO customerDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "customer-form";
        }
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setSex(customerDTO.getSex());
        customer.setBirthday(customerDTO.getBirthday());
        customerService.updateCustomer(customer);
        return "redirect:/customer/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        if (customer == null) {
            return "redirect:/customer/list";
        }
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setSex(customer.getSex());
        customerDTO.setBirthday(customer.getBirthday());
        model.addAttribute("customerDTO", customerDTO);
        return "customer-form";
    }

    @PostMapping("/edit")
    public String editCustomer(@Valid @ModelAttribute("customerDTO") CustomerDTO customerDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "customer-form";
        }
        Customer customer = customerService.getCustomerById(customerDTO.getId());
        if (customer != null) {
            customer.setName(customerDTO.getName());
            customer.setSex(customerDTO.getSex());
            customer.setBirthday(customerDTO.getBirthday());
            customerService.updateCustomer(customer);
        }
        return "redirect:/customer/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/customer/list";
    }
}