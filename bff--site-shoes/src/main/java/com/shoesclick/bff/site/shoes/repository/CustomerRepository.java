package com.shoesclick.bff.site.shoes.repository;



import com.shoesclick.bff.site.shoes.entity.Customer;
import com.shoesclick.bff.site.shoes.entity.Status;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "customerClient", url = "${backend.services.customerUrl}")
public interface CustomerRepository {

    @RequestMapping(method = RequestMethod.GET, value = "/api/customer/{id}", produces = "application/json")
    Customer findById(@PathVariable("id") Long id);

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/customer/{id}", produces = "application/json")
    Status delete(@PathVariable("id") Long id);

    @RequestMapping(method = RequestMethod.POST, value = "/api/customer/save", produces = "application/json")
    Status save(Customer customer);

}
