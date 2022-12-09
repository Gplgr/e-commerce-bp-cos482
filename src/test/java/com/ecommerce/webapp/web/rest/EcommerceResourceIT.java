package com.ecommerce.webapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ecommerce.webapp.IntegrationTest;
import com.ecommerce.webapp.domain.Ecommerce;
import com.ecommerce.webapp.repository.EcommerceRepository;
import com.ecommerce.webapp.service.dto.EcommerceDTO;
import com.ecommerce.webapp.service.mapper.EcommerceMapper;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link EcommerceResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EcommerceResourceIT {

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_PRODUCT_QUANTITY = 1;
    private static final Integer UPDATED_PRODUCT_QUANTITY = 2;

    private static final Boolean DEFAULT_CONFIRMATION = false;
    private static final Boolean UPDATED_CONFIRMATION = true;

    private static final Integer DEFAULT_INVOICE_CODE = 1;
    private static final Integer UPDATED_INVOICE_CODE = 2;

    private static final Double DEFAULT_INVOICE_PRICE = 1D;
    private static final Double UPDATED_INVOICE_PRICE = 2D;

    private static final LocalDate DEFAULT_SALE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SALE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_PRODUCTS_AVAILABLE = 1;
    private static final Integer UPDATED_PRODUCTS_AVAILABLE = 2;

    private static final Boolean DEFAULT_STATUS = false;
    private static final Boolean UPDATED_STATUS = true;

    private static final String ENTITY_API_URL = "/api/ecommerces";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private EcommerceRepository ecommerceRepository;

    @Autowired
    private EcommerceMapper ecommerceMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEcommerceMockMvc;

    private Ecommerce ecommerce;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ecommerce createEntity(EntityManager em) {
        Ecommerce ecommerce = new Ecommerce()
            .userID(DEFAULT_USER_ID)
            .productQuantity(DEFAULT_PRODUCT_QUANTITY)
            .confirmation(DEFAULT_CONFIRMATION)
            .invoiceCode(DEFAULT_INVOICE_CODE)
            .invoicePrice(DEFAULT_INVOICE_PRICE)
            .saleDate(DEFAULT_SALE_DATE)
            .productsAvailable(DEFAULT_PRODUCTS_AVAILABLE)
            .status(DEFAULT_STATUS);
        return ecommerce;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ecommerce createUpdatedEntity(EntityManager em) {
        Ecommerce ecommerce = new Ecommerce()
            .userID(UPDATED_USER_ID)
            .productQuantity(UPDATED_PRODUCT_QUANTITY)
            .confirmation(UPDATED_CONFIRMATION)
            .invoiceCode(UPDATED_INVOICE_CODE)
            .invoicePrice(UPDATED_INVOICE_PRICE)
            .saleDate(UPDATED_SALE_DATE)
            .productsAvailable(UPDATED_PRODUCTS_AVAILABLE)
            .status(UPDATED_STATUS);
        return ecommerce;
    }

    @BeforeEach
    public void initTest() {
        ecommerce = createEntity(em);
    }

    @Test
    @Transactional
    void getAllEcommerces() throws Exception {
        // Initialize the database
        ecommerceRepository.saveAndFlush(ecommerce);

        // Get all the ecommerceList
        restEcommerceMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ecommerce.getId().intValue())))
            .andExpect(jsonPath("$.[*].userID").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].productQuantity").value(hasItem(DEFAULT_PRODUCT_QUANTITY)))
            .andExpect(jsonPath("$.[*].confirmation").value(hasItem(DEFAULT_CONFIRMATION.booleanValue())))
            .andExpect(jsonPath("$.[*].invoiceCode").value(hasItem(DEFAULT_INVOICE_CODE)))
            .andExpect(jsonPath("$.[*].invoicePrice").value(hasItem(DEFAULT_INVOICE_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].saleDate").value(hasItem(DEFAULT_SALE_DATE.toString())))
            .andExpect(jsonPath("$.[*].productsAvailable").value(hasItem(DEFAULT_PRODUCTS_AVAILABLE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.booleanValue())));
    }

    @Test
    @Transactional
    void getEcommerce() throws Exception {
        // Initialize the database
        ecommerceRepository.saveAndFlush(ecommerce);

        // Get the ecommerce
        restEcommerceMockMvc
            .perform(get(ENTITY_API_URL_ID, ecommerce.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ecommerce.getId().intValue()))
            .andExpect(jsonPath("$.userID").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.productQuantity").value(DEFAULT_PRODUCT_QUANTITY))
            .andExpect(jsonPath("$.confirmation").value(DEFAULT_CONFIRMATION.booleanValue()))
            .andExpect(jsonPath("$.invoiceCode").value(DEFAULT_INVOICE_CODE))
            .andExpect(jsonPath("$.invoicePrice").value(DEFAULT_INVOICE_PRICE.doubleValue()))
            .andExpect(jsonPath("$.saleDate").value(DEFAULT_SALE_DATE.toString()))
            .andExpect(jsonPath("$.productsAvailable").value(DEFAULT_PRODUCTS_AVAILABLE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingEcommerce() throws Exception {
        // Get the ecommerce
        restEcommerceMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
