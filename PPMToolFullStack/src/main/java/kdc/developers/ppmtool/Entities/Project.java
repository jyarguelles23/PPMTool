package kdc.developers.ppmtool.Entities;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String projectName;
    String projectIdentifier;
    String description;
    Date start_date;
    Date end_date;

    LocalDateTime created_At;
    LocalDateTime updated_At;

    @PrePersist
    protected void onCreate(){
        this.created_At= LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updated_At=LocalDateTime.now();
    }

}
