package kdc.developers.ppmtool.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BackLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer PTSequence=0;

    String projectidentifier;

    //Oneto One whith project
    @OneToOne(fetch = FetchType.EAGER)
   // @MapsId
    @JoinColumn(name = "project_id",nullable = false)
    @JsonIgnore
    Project project;
    //cascade Refresh hace q si se borra un project task inmediatamente el backlog se pa q ese task ya no existe
    //OnetoMany ProjectTask
    @OneToMany(fetch= FetchType.EAGER,cascade = CascadeType.REFRESH,mappedBy ="backlog",orphanRemoval = true)
    List<ProjectTask> projectTasks=new ArrayList<>();
}
