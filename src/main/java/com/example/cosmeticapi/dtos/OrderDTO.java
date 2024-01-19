package com.example.cosmeticapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class OrderDTO {
    private Long user_id;
    private String order_code;
    private LocalDateTime order_date;
    private int order_status;
    private String order_note;
    private double total_amount;
}
