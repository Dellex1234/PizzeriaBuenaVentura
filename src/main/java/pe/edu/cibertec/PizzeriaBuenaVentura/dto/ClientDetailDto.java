package pe.edu.cibertec.PizzeriaBuenaVentura.dto;

import java.util.Date;

public record ClientDetailDto(Integer clientId,
                              String name,
                              String lastName,
                              Integer Dni,
                              String address,
                              String city,
                              String phone,
                              Date registrationDate) {
}
