package com.ecommerce.webapp.process.ecommerceProcess;

import com.ecommerce.webapp.domain.Ecommerce;
import com.ecommerce.webapp.domain.EcommerceProcess;
import com.ecommerce.webapp.domain.Produto;
import com.ecommerce.webapp.service.dto.EcommerceDTO;
import com.ecommerce.webapp.service.dto.EcommerceProcessDTO;
import com.ecommerce.webapp.service.dto.ProdutoDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class })
public interface TaskSelecaoMapper {
    @Mapping(target = "processInstance", source = "processInstance", qualifiedByName = "loadTaskContext")
    EcommerceProcessDTO toEcommerceProcessDTO(EcommerceProcess ecommerceProcess);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "userID", source = "userID")
    @Mapping(target = "productQuantity", source = "productQuantity")
    @Mapping(target = "produto", source = "produto")
    EcommerceDTO toEcommerceDTO(Ecommerce ecommerce);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    ProdutoDTO toProdutoDTO(Produto name);
}
