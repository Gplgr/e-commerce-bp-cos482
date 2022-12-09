package com.ecommerce.webapp.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Ecommerce.
 */
@Entity
@Table(name = "ecommerce")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Ecommerce implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "user_id")
    private String userID;

    @Column(name = "product_quantity")
    private Integer productQuantity;

    @Column(name = "confirmation")
    private Boolean confirmation;

    @Column(name = "invoice_code")
    private Integer invoiceCode;

    @Column(name = "invoice_price")
    private Double invoicePrice;

    @Column(name = "sale_date")
    private LocalDate saleDate;

    @Column(name = "products_available")
    private Integer productsAvailable;

    @Column(name = "status")
    private Boolean status;

    @ManyToOne
    private Produto produto;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ecommerce id(Long id) {
        this.id = id;
        return this;
    }

    public String getUserID() {
        return this.userID;
    }

    public Ecommerce userID(String userID) {
        this.userID = userID;
        return this;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Integer getProductQuantity() {
        return this.productQuantity;
    }

    public Ecommerce productQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
        return this;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Boolean getConfirmation() {
        return this.confirmation;
    }

    public Ecommerce confirmation(Boolean confirmation) {
        this.confirmation = confirmation;
        return this;
    }

    public void setConfirmation(Boolean confirmation) {
        this.confirmation = confirmation;
    }

    public Integer getInvoiceCode() {
        return this.invoiceCode;
    }

    public Ecommerce invoiceCode(Integer invoiceCode) {
        this.invoiceCode = invoiceCode;
        return this;
    }

    public void setInvoiceCode(Integer invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public Double getInvoicePrice() {
        return this.invoicePrice;
    }

    public Ecommerce invoicePrice(Double invoicePrice) {
        this.invoicePrice = invoicePrice;
        return this;
    }

    public void setInvoicePrice(Double invoicePrice) {
        this.invoicePrice = invoicePrice;
    }

    public LocalDate getSaleDate() {
        return this.saleDate;
    }

    public Ecommerce saleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
        return this;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public Integer getProductsAvailable() {
        return this.productsAvailable;
    }

    public Ecommerce productsAvailable(Integer productsAvailable) {
        this.productsAvailable = productsAvailable;
        return this;
    }

    public void setProductsAvailable(Integer productsAvailable) {
        this.productsAvailable = productsAvailable;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public Ecommerce status(Boolean status) {
        this.status = status;
        return this;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Produto getProduto() {
        return this.produto;
    }

    public Ecommerce produto(Produto produto) {
        this.setProduto(produto);
        return this;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ecommerce)) {
            return false;
        }
        return id != null && id.equals(((Ecommerce) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Ecommerce{" +
            "id=" + getId() +
            ", userID='" + getUserID() + "'" +
            ", productQuantity=" + getProductQuantity() +
            ", confirmation='" + getConfirmation() + "'" +
            ", invoiceCode=" + getInvoiceCode() +
            ", invoicePrice=" + getInvoicePrice() +
            ", saleDate='" + getSaleDate() + "'" +
            ", productsAvailable=" + getProductsAvailable() +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
