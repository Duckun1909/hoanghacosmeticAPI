package com.example.cosmeticapi.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class AddressDTO {
    @NotNull(message = "Fullname can't be null!")
    @Size(min = 6, max = 50, message = "Fullname must be between 6 and 50 characters!")
    private String fullname;

    @NotNull(message = "Phone can't be null!")
    @Pattern(regexp = "^(0|84)(2(0[3-9]|1[0-6|8|9]|2[0-2|5-9]|3[2-9]|4[0-9]|5[1|2|4-9]|6[0-3|9]|7[0-7]|8[0-9]|9[0-4|6|7|9])|3[2-9]|5[5|6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])([0-9]{7})$", message = "Phone is invalid!")
    private String adr_phone ;

    @NotNull(message = "Address shipping can't be null!")
    @Size(max = 200, message = "Address shipping can't be more than 200 characters!'")
    private String adr_shipping;

    @NotNull(message = "User id can't be null!")
    private int user_id;
    private int active;
}
