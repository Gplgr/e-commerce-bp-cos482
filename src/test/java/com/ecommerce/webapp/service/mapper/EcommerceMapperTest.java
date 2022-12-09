package com.ecommerce.webapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EcommerceMapperTest {

    private EcommerceMapper ecommerceMapper;

    @BeforeEach
    public void setUp() {
        ecommerceMapper = new EcommerceMapperImpl();
    }
}
