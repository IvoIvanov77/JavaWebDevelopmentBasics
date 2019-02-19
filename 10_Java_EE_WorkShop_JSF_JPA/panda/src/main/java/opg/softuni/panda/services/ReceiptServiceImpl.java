package opg.softuni.panda.services;

import opg.softuni.panda.domain.dto.PackageCreateBindingModel;
import opg.softuni.panda.repositories.ReceiptRepository;
import opg.softuni.panda.web.beans.PackageCreateBean;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;

public class ReceiptServiceImpl implements ReceiptService {
    private final ReceiptRepository receiptRepository;

    private final ModelMapper modelMapper;

    @Inject
    public ReceiptServiceImpl(ReceiptRepository receiptRepository, ModelMapper modelMapper) {
        this.receiptRepository = receiptRepository;
        this.modelMapper = modelMapper;
    }

}
