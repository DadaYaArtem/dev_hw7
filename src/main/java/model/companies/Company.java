package model.companies;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "companies")
@Entity
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String ceo;
}
