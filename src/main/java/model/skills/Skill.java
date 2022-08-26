package model.skills;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import model.developers.Developer;

import java.util.HashSet;
import java.util.Set;

@Table(name = "skills")
@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;
    @Column
    @Getter
    @Setter
    private String branch;
    @Enumerated(EnumType.STRING)
    @Column
    @Getter
    @Setter
    private Skillset skill;

    public enum Branch {
        Java,
        CPLUSPLUS,
        CSHARP,
        JS
    }

    public enum Skillset {
        Junior,
        Middle,
        Senior
    }

    @Getter
    @ManyToMany(mappedBy = "skills")
    private Set<Developer> developers = new HashSet<>();

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", branch='" + branch + '\'' +
                ", skill=" + skill +
                '}';
    }
}