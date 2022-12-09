package com.ecommerce.webapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ecommerce.webapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EcommerceTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Ecommerce.class);
        Ecommerce ecommerce1 = new Ecommerce();
        ecommerce1.setId(1L);
        Ecommerce ecommerce2 = new Ecommerce();
        ecommerce2.setId(ecommerce1.getId());
        assertThat(ecommerce1).isEqualTo(ecommerce2);
        ecommerce2.setId(2L);
        assertThat(ecommerce1).isNotEqualTo(ecommerce2);
        ecommerce1.setId(null);
        assertThat(ecommerce1).isNotEqualTo(ecommerce2);
    }
}
