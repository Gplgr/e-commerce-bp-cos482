package com.ecommerce.webapp.service.mapper;

import com.ecommerce.webapp.domain.*;
import com.ecommerce.webapp.service.dto.EcommerceProcessDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcommerceProcess} and its DTO {@link EcommerceProcessDTO}.
 */
@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class, EcommerceMapper.class })
public interface EcommerceProcessMapper extends EntityMapper<EcommerceProcessDTO, EcommerceProcess> {
    @Mapping(target = "processInstance", source = "processInstance")
    @Mapping(target = "ecommerce", source = "ecommerce")
    EcommerceProcessDTO toDto(EcommerceProcess s);
}
