package org.softuni.exam.web.beans;

import org.modelmapper.ModelMapper;
import org.softuni.exam.domain.models.service.JobServiceModel;
import org.softuni.exam.domain.models.view.JobsDetailsViewModel;
import org.softuni.exam.service.JobService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "jobDetails")
@RequestScoped
public class JobDetailsBean extends BaseBean {
    private  JobService jobService;

    private  ModelMapper modelMapper;

    private JobsDetailsViewModel viewModel;

    public JobDetailsBean() {
    }

    @Inject
    public JobDetailsBean(JobService jobService, ModelMapper modelMapper) {
        this.jobService = jobService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init(){
        String id = this.getRequest().getParameter("id");
        JobServiceModel serviceModel = this.jobService.getOneById(id);
        this.viewModel = this.modelMapper.map(serviceModel, JobsDetailsViewModel.class);
    }

    public JobsDetailsViewModel getViewModel() {
        return viewModel;
    }
}
