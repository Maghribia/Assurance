package com.esprit.ms.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class JobService {
    @Autowired

    private JobRepository jobRepository;

    public Job addJob(Job job) {
        return jobRepository.save(job);
    }

    public List<Job> getAll(){
        return jobRepository.findAll();
    }

    public Job getJobById(int id) {
        return jobRepository.findById(id).orElse(null);
    }

    public Job updateJob(int id, Job newJob) {
        if (jobRepository.findById(id).isPresent()) {

           Job existingJob = jobRepository.findById(id).get();
            existingJob.setService(newJob.getService());
            existingJob.setEtat(newJob.isEtat());

            return jobRepository.save(existingJob);
        } else
            return null;
    }


    public String deleteJob(int id) {
        if (jobRepository.findById(id).isPresent()) {
            jobRepository.deleteById(id);
            return "Job supprimé";
        } else
            return "Job Not Found";
    }
}
