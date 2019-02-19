package opg.softuni.panda.services;

import opg.softuni.panda.domain.dto.PackageCreateBindingModel;
import opg.softuni.panda.domain.dto.PackageViewHomePageModel;
import opg.softuni.panda.domain.dto.PackageViewTableModel;
import opg.softuni.panda.domain.entities.Package;
import opg.softuni.panda.domain.entities.enums.Status;
import opg.softuni.panda.repositories.PackageRepository;
import opg.softuni.panda.util.ApplicationUtils;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;

public class PackageServiceImpl implements PackageService {
    private final PackageRepository packageRepository;

    private final ModelMapper modelMapper;

    @Inject
    public PackageServiceImpl(PackageRepository packageRepository, ModelMapper modelMapper) {
        this.packageRepository = packageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createPackage(PackageCreateBindingModel model){
        Package aPackage = this.modelMapper.map(model, Package.class);
        aPackage.setStatus(Status.PENDING);
        this.packageRepository.save(aPackage);
    }

    @Override
    public List<PackageViewTableModel> listPackagesByStatus(Status status){
        return this.packageRepository.getPackagesTableByStatus(status);
    }

    @Override
    public List<PackageViewHomePageModel> listPackagesDescriptionByStatusAndRecipient(Status status){
        String username = (String) ApplicationUtils.getSession().getAttribute("username");
        return this.packageRepository.getPackagesDescriptionByStatus(status, username);
    }

    @Override
    public void updateStatus(String id, Status status){
        Package p = this.packageRepository.findById(id);
        p.setStatus(status);
        this.packageRepository.save(p);
    }
}
