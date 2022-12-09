package com.ecommerce.webapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.ecommerce.webapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EcommerceDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EcommerceDTO.class);
        EcommerceDTO ecommerceDTO1 = new EcommerceDTO();
        ecommerceDTO1.setId(1L);
        EcommerceDTO ecommerceDTO2 = new EcommerceDTO();
        assertThat(ecommerceDTO1).isNotEqualTo(ecommerceDTO2);
        ecommerceDTO2.setId(ecommerceDTO1.getId());
        assertThat(ecommerceDTO1).isEqualTo(ecommerceDTO2);
        ecommerceDTO2.setId(2L);
        assertThat(ecommerceDTO1).isNotEqualTo(ecommerceDTO2);
        ecommerceDTO1.setId(null);
        assertThat(ecommerceDTO1).isNotEqualTo(ecommerceDTO2);
    }
}
