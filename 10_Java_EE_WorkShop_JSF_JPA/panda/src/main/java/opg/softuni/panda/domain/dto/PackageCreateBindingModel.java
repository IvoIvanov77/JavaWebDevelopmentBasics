package opg.softuni.panda.domain.dto;

import opg.softuni.panda.domain.entities.User;

import java.math.BigDecimal;

public class PackageCreateBindingModel {
    private String description;

    private BigDecimal weight;

    private String shippingAddress;

    private String recipientId;

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

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }
}
