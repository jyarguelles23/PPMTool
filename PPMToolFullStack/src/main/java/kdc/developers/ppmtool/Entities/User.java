package kdc.developers.ppmtool.Entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Username needs to be an email")
    @NotBlank(message = "Username cannot be empty!")
    String username;

    @NotBlank(message = "PLease enter your full name")
    String fullname;

    @NotBlank(message = "Password cannot be empty!")
    String password;
    @Transient
    String confirmPassword;

    Date created_At;
    Date updated_At;

    //One To Many Qith Projects

    @PrePersist
    protected void onCreate(){
        this.created_At= new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updated_At=new Date();
    }
}
