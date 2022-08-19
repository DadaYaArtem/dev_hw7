package model.projects;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Table(name = "projects")
@Entity
@Data
public class Project {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    @Column
    private String name;
    @Column
    private int cost;
    @Column
    private LocalDate foundation_date;
}
