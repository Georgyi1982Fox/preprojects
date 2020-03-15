package model;

public class User {
    private Long id;
    private String name;
    private String password;
    private String email;

    public User(){
    }

    public User(Long id){
        this.id = id;
    }


    public User(Long id, String name, String password,String email){
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
    public User(String name, String password, String email){
        this.name = name;
        this.password = password;
        this.email= email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}