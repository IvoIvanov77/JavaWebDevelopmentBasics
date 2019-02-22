package org.softuni.exam.web.beans;

import org.modelmapper.ModelMapper;
import org.softuni.exam.domain.models.binding.JobCreateBindingModel;
import org.softuni.exam.domain.models.service.JobServiceModel;
import org.softuni.exam.domain.models.view.JobsDetailsViewModel;
import org.softuni.exam.service.JobService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "deleteJob")
@RequestScoped
public class DeleteJobBean extends BaseBean {
    private  JobService jobService;

    private  ModelMapper modelMapper;

    private JobsDetailsViewModel viewModel;

    private String id;

    public DeleteJobBean() {
    }

    @Inject
    public DeleteJobBean(JobService jobService, ModelMapper modelMapper) {
        this.jobService = jobService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init(){
        this.id = this.getRequest().getParameter("id");
        JobServiceModel serviceModel = this.jobService.getOneById(this.id);
        this.viewModel = this.modelMapper.map(serviceModel, JobsDetailsViewModel.class);
    }

    public JobsDetailsViewModel getViewModel() {
        return viewModel;
    }

    public void delete(){
        this.jobService.deleteJob(this.id);
        this.redirect("/");
    }
}
