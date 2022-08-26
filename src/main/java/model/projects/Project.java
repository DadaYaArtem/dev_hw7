package model.projects;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import model.developers.Developer;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Table(name = "projects")
@Entity
public class Project {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Getter
    @Setter
    private long id;
    @Column
    @Setter
    @Getter
    private String name;
    @Column
    @Setter
    @Getter
    private int cost;
    @Column
    @Setter
    @Getter
    private LocalDate foundation_date;

    @ManyToMany(mappedBy = "projects")
    private Set<Developer> developers = new HashSet<>();


    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", foundation_date=" + foundation_date +
                '}';
    }
}
