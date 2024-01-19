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
public class AddressDTO {
    private String fullname;
    private String adr_phone ;
    private String adr_shipping;
    private Long user_id;
}
