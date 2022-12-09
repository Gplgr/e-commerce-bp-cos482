package com.ecommerce.webapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import org.akip.service.dto.ProcessInstanceDTO;

/**
 * A DTO for the {@link com.ecommerce.webapp.domain.EcommerceProcess} entity.
 */
public class EcommerceProcessDTO implements Serializable {

    private Long id;

    private ProcessInstanceDTO processInstance;

    private EcommerceDTO ecommerce;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProcessInstanceDTO getProcessInstance() {
        return processInstance;
    }

    public void setProcessInstance(ProcessInstanceDTO processInstance) {
        this.processInstance = processInstance;
    }

    public EcommerceDTO getEcommerce() {
        return ecommerce;
    }

    public void setEcommerce(EcommerceDTO ecommerce) {
        this.ecommerce = ecommerce;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EcommerceProcessDTO)) {
            return false;
        }

        EcommerceProcessDTO ecommerceProcessDTO = (EcommerceProcessDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, ecommerceProcessDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EcommerceProcessDTO{" +
            "id=" + getId() +
            ", processInstance=" + getProcessInstance() +
            ", ecommerce=" + getEcommerce() +
            "}";
    }
}
