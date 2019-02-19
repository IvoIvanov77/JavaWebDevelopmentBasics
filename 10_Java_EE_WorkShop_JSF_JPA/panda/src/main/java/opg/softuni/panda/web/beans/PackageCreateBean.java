package opg.softuni.panda.web.beans;

import opg.softuni.panda.domain.dto.PackageCreateBindingModel;
import opg.softuni.panda.services.PackageService;
import opg.softuni.panda.util.ApplicationUtils;

import javax.inject.Inject;
import javax.inject.Named;

@Named("createPackage")
public class PackageCreateBean {

    private PackageCreateBindingModel model;
    private final PackageService packageService;

    @Inject
    public PackageCreateBean(PackageCreateBindingModel model, PackageService packageService) {
        this.model = model;
        this.packageService = packageService;
    }

    public PackageCreateBindingModel getModel() {
        return model;
    }

    public void setModel(PackageCreateBindingModel model) {
        this.model = model;
    }

    public void create(){
        this.packageService.createPackage(this.model);
        ApplicationUtils.redirect("/");
    }
}
