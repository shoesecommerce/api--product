package com.shoesclick.api.customer.mapper;

import com.shoesclick.api.customer.entity.Address;
import com.shoesclick.api.customer.entity.Customer;
import com.shoesclick.api.customer.entity.Status;
import com.shoesclick.api.customer.openapi.model.domain.*;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.data.domain.Page;

@Mapper(componentModel= MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {

    CustomerResponse map(Customer response);

    Customer map(CustomerRequest request);

    Address map(AddressRequest request);

    ListCustomerResponse map(Page<Customer> response);

    StatusResponse map(Status response);


}
