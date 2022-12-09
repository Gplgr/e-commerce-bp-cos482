package com.ecommerce.webapp.process.ecommerceProcess;

import com.ecommerce.webapp.domain.Ecommerce;
import com.ecommerce.webapp.domain.EcommerceProcess;
import com.ecommerce.webapp.service.dto.EcommerceDTO;
import com.ecommerce.webapp.service.dto.EcommerceProcessDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class })
public interface TaskConfirmacaoMapper {
    @Mapping(target = "processInstance", source = "processInstance", qualifiedByName = "loadTaskContext")
    EcommerceProcessDTO toEcommerceProcessDTO(EcommerceProcess ecommerceProcess);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "userID", source = "userID")
    @Mapping(target = "productQuantity", source = "productQuantity")
    @Mapping(target = "confirmation", source = "confirmation")
    EcommerceDTO toEcommerceDTO(Ecommerce ecommerce);
}
