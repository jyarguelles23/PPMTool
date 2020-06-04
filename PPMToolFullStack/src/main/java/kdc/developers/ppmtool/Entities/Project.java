package kdc.developers.ppmtool.Entities;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message="Project Name is required")
    String projectName;
    @NotBlank(message="Project Identifier is required")
    @Size(min=4,max=10, message = "Please use 4 to 10 characters")
    @Column(updatable = false,unique = true) // para q no se pueda updatear y sea unico el atributo
    String projectIdentifier;
    @NotBlank(message="Description is required")
    String description;
    @JsonFormat(pattern = "yyyy-mm-dd")
    Date start_date;
    @JsonFormat(pattern = "yyyy-mm-dd")
    Date end_date;
    @JsonFormat(pattern = "yyyy-mm-dd")
    LocalDateTime created_At;
    @JsonFormat(pattern = "yyyy-mm-dd")
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
