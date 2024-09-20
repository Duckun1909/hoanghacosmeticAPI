package com.example.cosmeticapi.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class CategoryDTO {
    @NotNull(message = "Category name cannot be null!")
    private String cat_name;
    private String cat_desc;
}
