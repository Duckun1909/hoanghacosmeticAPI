package com.example.cosmeticapi.dtos;

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
public class ProductDTO {
    private Long cat_id;
    private Long brand_id;
    private String prd_name;
    private String prd_thumb;
    private double prd_price;
    private String prd_desc;
    private double prd_rate;
    private double prd_discount;
    private int prd_status;
}
