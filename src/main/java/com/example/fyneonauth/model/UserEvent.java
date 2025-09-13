package com.example.fyneonauth.model;

public class UserEvent {
    private Long id;
    private String email;
    private String name;

    public UserEvent() {}



    public UserEvent(Long id, String username, String email) {
        this.id = id;
        this.name = username;
        this.email = email;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return name;
    }

    public void setUsername(String username) {
        this.name = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserEvent{" +
                "id=" + id +
                ", username='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
