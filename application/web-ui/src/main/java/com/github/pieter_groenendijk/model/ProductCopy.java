package com.github.pieter_groenendijk.model;


public class ProductCopy {

    private Long productCopyId;


    private PhysicalProduct physicalProduct;


    private ProductCopyStatus availabilityStatus;


    public Long getProductCopyId() {
        return productCopyId;
    }

    public void setProductCopyId(long productCopyId) {
        this.productCopyId = productCopyId;
    }

    public ProductCopyStatus getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(ProductCopyStatus availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public PhysicalProduct getPhysicalProductId() {
        return physicalProduct;
    }

    public void setPhysicalProduct(PhysicalProduct physicalProduct) {
        this.physicalProduct = physicalProduct;
    }
}
