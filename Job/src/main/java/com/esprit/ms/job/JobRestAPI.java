package com.esprit.ms.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobRestAPI {

    private String hello="Hello, i'm the Job MS";

    @RequestMapping("/helloJ")
    public String sayHello(){
        return hello;
    }

    @Autowired
    private JobService jobService;

    @RequestMapping
    public ResponseEntity<List<Job>> getAll() {
        return new ResponseEntity<>(jobService.getAll(), HttpStatus.OK);
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Job> createCandidat(@RequestBody Job job) {
        return new ResponseEntity<>(jobService.addJob(job), HttpStatus.CREATED);
    }


    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteCandidat(@PathVariable(value = "id") int id){
        return new ResponseEntity<String>(jobService.deleteJob(id), HttpStatus.OK);
    }

    @RequestMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable int id) {
        return new ResponseEntity<>(jobService.getJobById(id), HttpStatus.FOUND);
    }


}
