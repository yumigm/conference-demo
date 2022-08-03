package com.pluralsight.conferencedemo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity(name = "sessions")  //sessions is the name of our database table
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Session {      //one row of the data in sessions

    //The Id annotation specifies which attribute is the primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //These match the columns of the database
    private Long session_id;                    //Sets the session_id as the primary key and has a sequence number that is generated every time you insert a record
    private String session_name;
    private String session_description;
    private Integer session_length;
    @ManyToMany         //means that you are setting a many to many JPA relationship and that you have a mapping joint table in your database
    @JoinTable(         //defines that joint table and the foreign key columns
            name = "session_speakers",                             //table name
            joinColumns = @JoinColumn(name = "session_id"),         //column in table (also foreign key)
            inverseJoinColumns = @JoinColumn(name = "speaker_id")   //column in table (also a foreign key)
    )
    private List<Speaker> speakers;     //pulls in a list of speakers

    public Session(){}

    public Long getSession_id() {
        return session_id;
    }

    public void setSession_id(Long session_id) {
        this.session_id = session_id;
    }

    public String getSession_name() {
        return session_name;
    }

    public void setSession_name(String session_name) {
        this.session_name = session_name;
    }

    public String getSession_description() {
        return session_description;
    }

    public void setSession_description(String session_description) {
        this.session_description = session_description;
    }

    public Integer getSession_length() {
        return session_length;
    }

    public void setSession_length(Integer session_length) {
        this.session_length = session_length;
    }

    public List<Speaker> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<Speaker> speakers) {
        this.speakers = speakers;
    }

}
