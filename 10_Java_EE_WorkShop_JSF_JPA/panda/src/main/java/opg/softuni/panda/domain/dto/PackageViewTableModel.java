package opg.softuni.panda.domain.dto;

import opg.softuni.panda.domain.entities.Package;

import java.math.BigDecimal;

public class PackageViewTableModel {
    private String id;

    private String description;

    private BigDecimal weight;

    private String shippingAddress;

    private String recipient;

    public PackageViewTableModel() {
    }

    public PackageViewTableModel(Package p) {
        this.id = p.getId();
        this.description = p.getDescription();
        this.weight = p.getWeight();
        this.shippingAddress = p.getShippingAddress();
        this.recipient = p.getRecipient().getUsername();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
}
