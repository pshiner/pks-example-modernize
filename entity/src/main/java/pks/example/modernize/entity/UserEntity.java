package pks.example.modernize.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity @Table(name = "EX_APP_USER")
public class UserEntity {
    
    @Id @Column(name = "C_USERNAME")
    private String username;

    @Column(name = "C_NAME")
    private String name;
    
    public UserEntity() {
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) 
            return false;

        UserEntity other = (UserEntity)obj;
        return this.username.equals(other.username);
    }

}
