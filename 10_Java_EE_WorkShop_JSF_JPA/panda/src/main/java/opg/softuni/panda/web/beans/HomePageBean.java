package opg.softuni.panda.web.beans;

import opg.softuni.panda.domain.dto.PackageViewHomePageModel;
import opg.softuni.panda.domain.entities.enums.Status;
import opg.softuni.panda.services.PackageService;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named(value = "homePage")
public class HomePageBean {
    private final PackageService packageService;


    @Inject
    public HomePageBean(PackageService packageService) {
        this.packageService = packageService;
    }

    public List<PackageViewHomePageModel> getAllPendingPackages() {
        return this.packageService.listPackagesDescriptionByStatusAndRecipient(Status.PENDING);
    }

    public List<PackageViewHomePageModel> getAllShippedPackages() {
        return this.packageService.listPackagesDescriptionByStatusAndRecipient(Status.SHIPPED);
    }

    public List<PackageViewHomePageModel> getAllDeliveredPackages() {
        return this.packageService.listPackagesDescriptionByStatusAndRecipient(Status.DELIVERED);
    }
}
