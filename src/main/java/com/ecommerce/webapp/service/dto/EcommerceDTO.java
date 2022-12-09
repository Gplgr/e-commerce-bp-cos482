package com.ecommerce.webapp.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.ecommerce.webapp.domain.Ecommerce} entity.
 */
public class EcommerceDTO implements Serializable {

    private Long id;

    private String userID;

    private Integer productQuantity;

    private Boolean confirmation;

    private Integer invoiceCode;

    private Double invoicePrice;

    private LocalDate saleDate;

    private Integer productsAvailable;

    private Boolean status;

    private ProdutoDTO produto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Boolean getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(Boolean confirmation) {
        this.confirmation = confirmation;
    }

    public Integer getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(Integer invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public Double getInvoicePrice() {
        return invoicePrice;
    }

    public void setInvoicePrice(Double invoicePrice) {
        this.invoicePrice = invoicePrice;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public Integer getProductsAvailable() {
        return productsAvailable;
    }

    public void setProductsAvailable(Integer productsAvailable) {
        this.productsAvailable = productsAvailable;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ProdutoDTO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoDTO produto) {
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EcommerceDTO)) {
            return false;
        }

        EcommerceDTO ecommerceDTO = (EcommerceDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, ecommerceDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EcommerceDTO{" +
            "id=" + getId() +
            ", userID='" + getUserID() + "'" +
            ", productQuantity=" + getProductQuantity() +
            ", confirmation='" + getConfirmation() + "'" +
            ", invoiceCode=" + getInvoiceCode() +
            ", invoicePrice=" + getInvoicePrice() +
            ", saleDate='" + getSaleDate() + "'" +
            ", productsAvailable=" + getProductsAvailable() +
            ", status='" + getStatus() + "'" +
            ", produto=" + getProduto() +
            "}";
    }
}
