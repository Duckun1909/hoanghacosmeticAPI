package com.cosmetic.demo.dtos;

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
    private Long order_id;
    private Long prd_id;
    private int quantity;
    private double unit_price;
}
