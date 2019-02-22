package org.softuni.exam.service;

import org.modelmapper.ModelMapper;
import org.softuni.exam.domain.entities.Job;
import org.softuni.exam.domain.models.service.JobServiceModel;
import org.softuni.exam.domain.models.view.ListJobsViewModel;
import org.softuni.exam.repository.JobRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;

    private final ModelMapper modelMapper;

    @Inject
    public JobServiceImpl(JobRepository jobRepository, ModelMapper modelMapper) {
        this.jobRepository = jobRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createJob(JobServiceModel model){
        Job job = this.modelMapper.map(model, Job.class);
        this.jobRepository.save(job);
    }

    @Override
    public List<JobServiceModel> getAllJobs(){
        List<JobServiceModel> allJobs =
                this.jobRepository.findAll()
                .stream()
                .map(job -> this.modelMapper.map(job, JobServiceModel.class))
                .collect(Collectors.toList());
        return allJobs;
    }

    @Override
    public JobServiceModel getOneById(String id){
        Job job = this.jobRepository.findById(id);
        JobServiceModel serviceModel = this.modelMapper.map(job, JobServiceModel.class);
        return serviceModel;
    }

    @Override
    public void deleteJob(String id){
        Job job = this.jobRepository.findById(id);
        this.jobRepository.delete(job);
    }


}
