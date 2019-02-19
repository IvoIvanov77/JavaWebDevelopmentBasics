package opg.softuni.panda.web.beans;

import opg.softuni.panda.domain.entities.enums.Status;
import opg.softuni.panda.services.PackageService;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "updatePackageStatus")
public class UpdatePackageStatusBean {
    private final PackageService packageService;

    @Inject
    public UpdatePackageStatusBean(PackageService packageService) {
        this.packageService = packageService;
    }

    public String update(String id, String newStatus)  {
        Status status = Status.valueOf(newStatus.toUpperCase());
        this.packageService.updateStatus(id, status);
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true";
    }


}
