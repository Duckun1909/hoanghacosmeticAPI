package com.example.cosmeticapi.dtos;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class ProductDTO {
    private Integer catId;
    private Integer brandId;
    private String prdName;
    private String prdThumb;
    private double prdPrice;
    @Length(max = 10, message = "Product description must be less than 10 characters!")
    private String prdDesc;
    private double prdRate;
    private double prdDiscount;
    private int prdStatus;
}
