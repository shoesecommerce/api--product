package com.shoesclick.api.customer.service;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import com.shoesclick.api.customer.aspect.ReturnRepositoryElement;
public abstract class AbstractServiceTest {


    protected AspectJProxyFactory factory;

    public <T> T setupServiceTest(T service) {
        this.factory = new AspectJProxyFactory(service);
        this.factory.addAspect(new ReturnRepositoryElement());
        return service = this.factory.getProxy();
    }

}
