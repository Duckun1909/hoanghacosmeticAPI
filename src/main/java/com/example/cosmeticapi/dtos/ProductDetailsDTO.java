package com.cosmetic.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class ProductDetailsDTO {
    private String prds_color;
    private String prds_image;
    private int prds_type;
    private Long prd_id;
}
