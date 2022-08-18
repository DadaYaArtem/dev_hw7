package model.developers;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "developers")
@Entity
@Data
public class Developer {
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        private long id;
        @Column
        private String first_name;
        @Column
        private String last_name;
        @Column
        private int age;
        @Column
        private int salary;
}