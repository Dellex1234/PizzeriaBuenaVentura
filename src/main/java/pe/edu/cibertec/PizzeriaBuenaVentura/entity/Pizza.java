package pe.edu.cibertec.PizzeriaBuenaVentura.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pizzaId;
    private String taste;
    private String description;
    private Integer price;
    private String size;

    @OneToMany(mappedBy = "pizza")
    private List<Client> films;

}
