package com.shoesclick.bff.site.shoes.mapper;

import com.shoesclick.bff.site.shoes.entity.Address;
import com.shoesclick.bff.site.shoes.entity.Customer;
import com.shoesclick.bff.site.shoes.entity.Status;
import com.shoesclick.bff.site.shoes.openapi.model.domain.*;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel= MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {

    CustomerResponse map(Customer response);

    Customer map(CustomerRequest request);

    Address map(AddressRequest request);

    StatusResponse map(Status response);


}
