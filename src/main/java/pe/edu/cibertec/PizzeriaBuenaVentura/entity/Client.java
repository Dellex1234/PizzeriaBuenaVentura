package pe.edu.cibertec.PizzeriaBuenaVentura.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clientId;
    private String name;
    private String lastName;
    private Integer Dni;
    private String address;
    private String city;
    private String phone;
    private Date registrationDate;

    @ManyToOne
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;
}
