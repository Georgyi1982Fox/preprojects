package model;

import javax.persistence.*;


@Entity
@Table(name = "users")
public class User {
   // private  String role;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    private  String role;

    public User(){

    }

    public User(Long id, String name, String password, String email){
        this.id = id;
        this.name = name;
        this.password= password;
        this.email = email;
    }

    public User(Long id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;

    }

    public User(String password){
        this.password = password;
    }

    public User(String name, String password){
        this.name=name;
        this.password=password;
    }


    public User(String name, String password, String email, String role){
        this.name = name;
        this.password = password;
        this.email= email;
        this.role = role;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole(){
        return role;
    }
}





