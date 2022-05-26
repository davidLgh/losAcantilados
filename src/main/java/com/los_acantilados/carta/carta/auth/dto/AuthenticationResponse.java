package com.los_acantilados.carta.carta.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Data
@AllArgsConstructor
public class AuthenticationResponse {
    private String jwt;

}
