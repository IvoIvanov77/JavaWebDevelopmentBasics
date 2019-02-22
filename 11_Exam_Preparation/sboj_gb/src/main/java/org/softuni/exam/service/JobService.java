package org.softuni.exam.service;

import org.softuni.exam.domain.models.service.JobServiceModel;

import java.util.List;

public interface JobService {
    void createJob(JobServiceModel model);

    List<JobServiceModel> getAllJobs();

    JobServiceModel getOneById(String id);

    void deleteJob(String id);
}
