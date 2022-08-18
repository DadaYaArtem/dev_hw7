package model.customers;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "customers")
@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String first_name;
    @Column
    private String last_name;
    @Column
    private String phone_number;

}
