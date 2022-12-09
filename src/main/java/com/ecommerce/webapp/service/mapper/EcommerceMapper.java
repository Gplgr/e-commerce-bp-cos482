package com.ecommerce.webapp.service.mapper;

import com.ecommerce.webapp.domain.*;
import com.ecommerce.webapp.service.dto.EcommerceDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Ecommerce} and its DTO {@link EcommerceDTO}.
 */
@Mapper(componentModel = "spring", uses = { ProdutoMapper.class })
public interface EcommerceMapper extends EntityMapper<EcommerceDTO, Ecommerce> {
    @Mapping(target = "produto", source = "produto", qualifiedByName = "name")
    EcommerceDTO toDto(Ecommerce s);
}
