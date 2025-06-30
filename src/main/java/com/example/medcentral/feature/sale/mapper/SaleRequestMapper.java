package com.example.medcentral.feature.sale.mapper;

import com.example.medcentral.feature.sale.model.entity.Sale;
import com.example.medcentral.feature.sale.model.request.CreateSaleRequest;
import com.example.medcentral.feature.sale.model.request.UpdateSaleRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SaleRequestMapper {

    Sale toEntity(CreateSaleRequest request);
    Sale toEntity(UpdateSaleRequest request);
}
