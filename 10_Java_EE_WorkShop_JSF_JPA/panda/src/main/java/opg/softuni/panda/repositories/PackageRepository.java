package opg.softuni.panda.repositories;

import opg.softuni.panda.domain.dto.PackageViewHomePageModel;
import opg.softuni.panda.domain.dto.PackageViewTableModel;
import opg.softuni.panda.domain.entities.Package;
import opg.softuni.panda.domain.entities.enums.Status;

import java.util.List;

public interface PackageRepository extends Repository<String, Package> {

    @SuppressWarnings("unchecked")
    List<PackageViewTableModel> getPackagesTableByStatus(Status status);

    @SuppressWarnings("unchecked")
    List<PackageViewHomePageModel> getPackagesDescriptionByStatus(Status status, String username);
}
