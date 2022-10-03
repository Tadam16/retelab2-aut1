package hu.bme.aut.retelab2.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
public class Ad {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String description;

    private int price;

    @CreationTimestamp
    private Date creation;

    private String secretCode;

    private LocalDateTime expiration;

    @ElementCollection
    private List<String> tags;

    public void setId(Long id){this.id = id;}
    public void setCreation(Date creation){this.creation=creation;}

    public void setSecretCode(String code){this.secretCode=code;}

    public void setTags(List<String> tags){this.tags = tags;}

    public Long getId(){ return id; }

    public String getTitle(){ return title; }

    public String getDescription(){return description;}

    public int getPrice(){ return price; }

    public Date getCreation(){ return creation; }

    public String getSecretCode(){ return secretCode; }

    public List<String> getTags(){ return tags; }

    public LocalDateTime getExpiration(){return expiration;}
}