package opg.softuni.panda.services;

import opg.softuni.panda.domain.dto.PackageCreateBindingModel;
import opg.softuni.panda.domain.dto.PackageViewHomePageModel;
import opg.softuni.panda.domain.dto.PackageViewTableModel;
import opg.softuni.panda.domain.entities.enums.Status;

import java.util.List;

public interface PackageService {

    void createPackage(PackageCreateBindingModel model);

    List<PackageViewTableModel> listPackagesByStatus(Status status);

    List<PackageViewHomePageModel> listPackagesDescriptionByStatusAndRecipient(Status status);

    void updateStatus(String id, Status status);
}
