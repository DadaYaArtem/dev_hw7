package model.developers;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import model.projects.Project;
import model.skills.Skill;

import java.util.HashSet;
import java.util.Set;

@Setter
@Table(name = "developers")
@Entity
public class Developer {
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    @Setter
    @Getter
    private String first_name;
    @Setter
    @Getter
    private String last_name;
    @Setter
    @Getter
    private int age;
    @Setter
    @Getter
    private int salary;


    @Getter
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "DEVELOPERS_PROJECTS",
            joinColumns = @JoinColumn(name = "DEVELOPER_ID"),
            inverseJoinColumns = @JoinColumn(name = "PROJECT_ID")
    )
    private Set<Project> projects = new HashSet<>();


    @Getter
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SKILLS_DEVELOPERS",
            joinColumns = @JoinColumn(name = "DEVELOPER_ID"),
            inverseJoinColumns = @JoinColumn(name = "SKILL_ID")
    )
    private Set<Skill> skills = new HashSet<>();



    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}