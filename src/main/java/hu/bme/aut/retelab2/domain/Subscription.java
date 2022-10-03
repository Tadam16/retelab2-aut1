package hu.bme.aut.retelab2.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Subscription {

    @Id
    @GeneratedValue
    private Long id;

    private Long subscribedTo;

    private String email;

    private String name;

    public void setId(Long id){this.id = id;}

    public void setSubscribedTo(Long id){this.subscribedTo = id;}

    public void setEmail(String email){this.email = email;}

    public void setName(String name){this.name = name;}

    public Long getId(){return id;}

    public Long getSubscribedTo(){return subscribedTo;}

    public String getEmail(){return email;}

    public String getName(){return name;}
}
