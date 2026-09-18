package com.tyagi.fintech1.entity;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String name;
    @Column(nullable =false,unique = true)
    private String email;
    @Column(nullable = false, unique = true, length = 15)
    private String mobile;

    @JsonIgnore
    private String password;
    public User(){

    }
            public User(String name,String email,String password){
        this.name=name;
        this.email=email;
        this.password=password;
                this.mobile = "";
    }
            public User(String name,String email,String mobile,String password){
                this.name=name;
                this.email=email;
                this.mobile=mobile;
                this.password=password;
            }
      public String getId() {
          return id;
      }
      public void setId(String id) {
          this.id = id;
      }
      public String getName() {
          return name;
      }
      public void setName(String name) {
          this.name = name;
      }
      public String getEmail() {
          return email;
      }
      public void setEmail(String email) {
          this.email = email;
      }
      public String getMobile() {
          return mobile;
      }
      public void setMobile(String mobile) {
          this.mobile = mobile;
      }
      public String getPassword() {
          return password;
      }
      public void setPassword(String password) {
          this.password = password;
      }
    
    
}