package kdc.developers.ppmtool.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
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
    //OnetoMany ProjectTask
    @OneToMany(fetch= FetchType.EAGER,cascade = CascadeType.ALL,mappedBy ="backlog")
    List<ProjectTask> projectTasks=new ArrayList<>();
}