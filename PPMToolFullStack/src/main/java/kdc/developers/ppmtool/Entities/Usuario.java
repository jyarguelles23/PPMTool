package kdc.developers.ppmtool.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Username needs to be an email")
    @NotBlank(message = "Username cannot be empty!")
    @Column(unique = true)
    String username;

    @NotBlank(message = "PLease enter your full name")
    String fullName;

    @NotBlank(message = "Password cannot be empty!")
    String password;
    @Transient
    String confirmPassword;

    Date created_At;
    Date updated_At;

    //One To Many Qith Projects
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER,mappedBy = "user" , orphanRemoval = true)
    List<Project> project=new ArrayList<>();


    @PrePersist
    protected void onCreate(){
        this.created_At= new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updated_At=new Date();
    }

    //User Details interface methods


    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
