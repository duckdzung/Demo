package net.fujinet.cs.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public class CustomerForm {
    private Long id;

    @NotEmpty(message = "Name is required")
    private String name;

    @NotEmpty(message = "Sex is required")
    private String sex;

    @NotNull(message = "Birthday is required")
    @Past(message = "Birthday must be a past date")
    private LocalDate birthday;

    // Getters and setters
}