package com.example.cosmeticapi.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class BrandDTO {
    @NotNull(message = "Brand name cannot be null!")
    private String brd_name;

    private String brd_img;
    private String brd_website;
}
