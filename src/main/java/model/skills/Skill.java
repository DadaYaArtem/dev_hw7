package model.skills;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "skills")
@Entity
@Data
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    @Column
    private Branch branch;
    @Enumerated(EnumType.STRING)
    @Column
    private Skillset skill;

    public enum Branch {
        Java,
        CSHARP,
        CPLUSPLUS,
        JS
    }

    public enum Skillset {
        Junior,
        Middle,
        Senior
    }
}