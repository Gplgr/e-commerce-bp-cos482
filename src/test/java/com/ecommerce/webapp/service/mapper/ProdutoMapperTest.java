package com.ecommerce.webapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProdutoMapperTest {

    private ProdutoMapper produtoMapper;

    @BeforeEach
    public void setUp() {
        produtoMapper = new ProdutoMapperImpl();
    }
}
