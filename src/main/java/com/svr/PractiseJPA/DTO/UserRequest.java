package com.svr.PractiseJPA.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserRequest {
        @NotNull(message = "Name is Required")
        @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
        private String name;

        @Min(value = 1, message = "Age must be atleast 1")
        @Max(value = 70, message = "Age cannot exceed 70")
        private int yearsOld;
}
