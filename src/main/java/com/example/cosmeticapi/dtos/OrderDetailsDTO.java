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
public class OrderDetailsDTO {
    private int order_id;
    private int prd_id;
    private int quantity;
    private double unit_price;
}
