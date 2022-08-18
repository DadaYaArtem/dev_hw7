package model.projects;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Project {
    private long id;
    private String name;
    private int cost;
    private LocalDate foundation_date;
}
