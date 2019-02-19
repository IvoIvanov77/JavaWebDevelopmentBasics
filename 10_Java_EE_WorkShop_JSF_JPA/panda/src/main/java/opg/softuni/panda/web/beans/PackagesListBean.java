package opg.softuni.panda.web.beans;

import opg.softuni.panda.domain.dto.PackageViewTableModel;
import opg.softuni.panda.domain.entities.enums.Status;
import opg.softuni.panda.services.PackageService;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named(value = "packagesList")
public class PackagesListBean {
    private final PackageService packageService;


    @Inject
    public PackagesListBean(PackageService packageService) {
        this.packageService = packageService;
    }

    public List<PackageViewTableModel> getAllPendinPackages() {
        return this.packageService.listPackagesByStatus(Status.PENDING);
    }

    public List<PackageViewTableModel> getAllShipedPackages() {
        return this.packageService.listPackagesByStatus(Status.SHIPPED);
    }

    public List<PackageViewTableModel> getAllDeliveredPackages() {
        return this.packageService.listPackagesByStatus(Status.DELIVERED);
    }


}
