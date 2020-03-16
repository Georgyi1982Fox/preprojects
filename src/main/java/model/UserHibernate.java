package model;

import javax.persistence.*;


@Entity
@Table(name = "users")
public class UserHibernate {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "id")
    private String password;

    @Column(name = "id")
    private String email;

    public UserHibernate(){

    }

    public UserHibernate(Long id, String name, String password,String email){
        this.id = id;
        this.name = name;
        this.password= password;
        this.email = email;
    }

    public UserHibernate(Long id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public UserHibernate(String password){
        this.password = password;
    }

    public UserHibernate(String name, String password, String email){
        this.name = name;
        this.password = password;
        this.email= email;
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
}





