package kdc.developers.ppmtool.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProjectTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(updatable = false)
    String projectSequence;
    @NotBlank(message = "Please include a project")
    String summary;
    String acceptanceCriteria;
    String status;
    Integer priority;
   //Many toOne with Backlogs
    @Column(updatable = false)
    String projectIdentifier;
   Date dueDate;
    @JsonFormat(pattern = "yyyy-mm-dd")
   Date created_At;
    @JsonFormat(pattern = "yyyy-mm-dd")
   Date updated_At;

    @PrePersist
    protected void onCreate(){
        this.created_At= new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updated_At=new Date();
    }

}
