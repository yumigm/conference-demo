package com.pluralsight.conferencedemo.controllers;


import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController                     //means this will respond to payloads incoming and outgoing as JSON rest endpoints
@RequestMapping("/api/v1/sessions")                  //tells the router what the mapping url will look like
public class SessionsController {

    @Autowired    //When our session controller is built, spring will autowire this to create and instance of the session repository and put it onto our class
    private SessionRepository sessionRepository;


    /*
    Spring MVM will then pass it over to Jackson (serialization library), which will turn those Sessions to JSON and return to caller
     */
    @GetMapping             //list endpoint that will return everything when called
    public List<Session> list() {       //
        return sessionRepository.findAll();     //query all sessions in database and return a list of session objects
    }

    /*
    INFORMATION
    One thing to know about Rest Controllers in Spring MVC is the return types
    By default the Rest controller will return 200's for response status to all calls
    Even @PostMapping, will return a 201
    How Do we fix this?
    We use @ResponseStatus
    */
    @GetMapping
    @RequestMapping("{id}")             //is adding the request mapping in on the class, so adding "id" to url, looking for a specific ID
    public Session get(@PathVariable Long id){
        return sessionRepository.getOne(id);        //returns and queries the session for that id back to the caller
    }

    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED)                         //This will allow me to specify the exact response I need
    //Spring MVC is taking in all attributes in a JSON Payload and automatically marshalling them in a session object
    public Session create(@RequestBody final Session session){  //Create new session after you pass a session info to the api endpoint
        return sessionRepository.saveAndFlush(session);         //you can save objects, but won't commit to database until you flush it
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)  //requiring that you need to pass in an id
    public void delete(@PathVariable Long id){
        sessionRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)                 //requiring Id on url and
    public Session update(@PathVariable Long id, @RequestBody Session session) {
        Session existingSession = sessionRepository.getOne(id);                    //in order to update a record we need the find the existing one and this finds the id
        BeanUtils.copyProperties(session, existingSession, "session_id");          //This takes the existing session and copies the incoming session data onto it, session_id focuses on the id's so we don't copy extra data into the existing data
        return sessionRepository.saveAndFlush(existingSession);
    }
}
