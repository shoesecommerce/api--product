package com.shoesclick.api.product.service;

import com.shoesclick.api.product.aspect.ReturnRepositoryElement;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
public abstract class AbstractServiceTest {


    protected AspectJProxyFactory factory;

    public <T> T setupServiceTest(T service) {
        this.factory = new AspectJProxyFactory(service);
        this.factory.addAspect(new ReturnRepositoryElement());
        return service = this.factory.getProxy();
    }

}
