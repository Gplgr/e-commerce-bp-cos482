package com.ecommerce.webapp.delegates;

import com.ecommerce.webapp.domain.Ecommerce;
import com.ecommerce.webapp.repository.EcommerceRepository;
import com.ecommerce.webapp.service.dto.EcommerceDTO;
import com.ecommerce.webapp.service.dto.EcommerceProcessDTO;
import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FinalizacaoDelegate implements JavaDelegate {

    private final EcommerceRepository ecommerceRepo;

    public FinalizacaoDelegate(EcommerceRepository ecommerceRepo) {
        this.ecommerceRepo = ecommerceRepo;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        EcommerceProcessDTO pi = (EcommerceProcessDTO) delegateExecution.getVariable("pi");
        EcommerceDTO ecommerce = (EcommerceDTO) pi.getEcommerce();
        Ecommerce ecommerceObj = ecommerceRepo.findById(ecommerce.getId()).get();

        ecommerce.setStatus(true);
        ecommerceObj.setStatus(true);
        ecommerceRepo.save(ecommerceObj);
    }
}
