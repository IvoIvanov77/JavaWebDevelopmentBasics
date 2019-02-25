package org.softuni.exam.web.beans;

import org.modelmapper.ModelMapper;
import org.softuni.exam.domain.enums.Sector;
import org.softuni.exam.domain.models.binding.JobCreateBindingModel;
import org.softuni.exam.domain.models.service.JobServiceModel;
import org.softuni.exam.domain.models.view.ListJobsViewModel;
import org.softuni.exam.service.JobService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named(value = "jobsBySector")
@RequestScoped
public class JobsBySectorBean extends BaseBean {
    private  JobService jobService;

    private  ModelMapper modelMapper;

    public JobsBySectorBean() {
    }

    @Inject
    public JobsBySectorBean(JobService jobService, ModelMapper modelMapper) {
        this.jobService = jobService;
        this.modelMapper = modelMapper;
    }

    private List<ListJobsViewModel> listOfJobsBySector(Sector sector){
        return this.jobService.getAllJobs()
                .stream()
                .filter(job -> job.getSector().equals(sector))
                .map(job -> this.modelMapper.map(job, ListJobsViewModel.class))
                .collect(Collectors.toList());
    }

    public List<ListJobsViewModel> getCarJobs(){
        System.out.println(this.listOfJobsBySector(Sector.Car));
        return this.listOfJobsBySector(Sector.Car);
    }

    public List<ListJobsViewModel> getDomesticJobs(){
        return this.listOfJobsBySector(Sector.Domestic);
    }

    public List<ListJobsViewModel> getMedicineJobs(){
        return this.listOfJobsBySector(Sector.Medicine);
    }

    public List<ListJobsViewModel> getSecurityJobs(){
        return this.listOfJobsBySector(Sector.Security);
    }

    public List<ListJobsViewModel> getFoodJobs(){
        return this.listOfJobsBySector(Sector.Food);
    }

    public String getText(String text){
        return text;
    }
}
