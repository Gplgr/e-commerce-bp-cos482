package com.ecommerce.webapp.delegates;

import com.ecommerce.webapp.domain.Ecommerce;
import com.ecommerce.webapp.domain.Produto;
import com.ecommerce.webapp.repository.EcommerceRepository;
import com.ecommerce.webapp.repository.ProdutoRepository;
import com.ecommerce.webapp.service.dto.EcommerceDTO;
import com.ecommerce.webapp.service.dto.EcommerceProcessDTO;
import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmissaoDelegate implements JavaDelegate {

    private final EcommerceRepository ecommerceRepo;
    private final ProdutoRepository produtoRepo;

    public EmissaoDelegate(EcommerceRepository ecommerceRepo, ProdutoRepository produtoRepo) {
        this.ecommerceRepo = ecommerceRepo;
        this.produtoRepo = produtoRepo;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        EcommerceProcessDTO pi = (EcommerceProcessDTO) delegateExecution.getVariable("pi");
        EcommerceDTO ecommerce = (EcommerceDTO) pi.getEcommerce();
        Ecommerce ecommerceObj = ecommerceRepo.findById(ecommerce.getId()).get();

        int randomCode = ThreadLocalRandom.current().nextInt(10000, 100000);

        ecommerce.setInvoiceCode(randomCode);
        ecommerceObj.setInvoiceCode(randomCode);

        if (ecommerce.getProduto() != null) {
            Produto produto = this.produtoRepo.findById(ecommerce.getProduto().getId()).get();
            double preco = produto.getPrice();
            int quantidade = ecommerce.getProductQuantity();
            double custo = (double) preco * quantidade;
            ecommerce.setInvoicePrice(custo);
            ecommerceObj.setInvoicePrice(custo);
        }
        ecommerceRepo.save(ecommerceObj);
        //pi.setEcommerce(ecommerce);
        //int test = pi.getEcommerce().getInvoiceCode();
        //System.out.println("=============== " +test + "======================");
        //delegateExecution.setVariable("pi",pi);
    }
}
