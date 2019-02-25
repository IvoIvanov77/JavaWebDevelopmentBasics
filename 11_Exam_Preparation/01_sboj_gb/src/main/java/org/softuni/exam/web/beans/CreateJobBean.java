package org.softuni.exam.web.beans;

import org.modelmapper.ModelMapper;
import org.softuni.exam.domain.models.binding.JobCreateBindingModel;
import org.softuni.exam.domain.models.service.JobServiceModel;
import org.softuni.exam.service.JobService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "createJob")
@RequestScoped
public class CreateJobBean extends BaseBean {
    private JobService jobService;

    private ModelMapper modelMapper;

    private JobCreateBindingModel bindingModel;

    public CreateJobBean() {
    }

    @Inject
    public CreateJobBean(JobService jobService, ModelMapper modelMapper) {
        this.jobService = jobService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init(){
        this.bindingModel = new JobCreateBindingModel();
    }

    public JobCreateBindingModel getBindingModel() {
        return bindingModel;
    }

    public void setBindingModel(JobCreateBindingModel bindingModel) {
        this.bindingModel = bindingModel;
    }

    public void create(){
        JobServiceModel serviceModel = this.modelMapper.map(this.bindingModel, JobServiceModel.class);
        this.jobService.createJob(serviceModel);
        this.redirect("/");
    }
}
