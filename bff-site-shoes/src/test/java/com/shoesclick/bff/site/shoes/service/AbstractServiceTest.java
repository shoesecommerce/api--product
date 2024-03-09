package com.shoesclick.bff.site.shoes.service;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import com.shoesclick.bff.site.shoes.aspect.ReturnRepositoryElement;
public abstract class AbstractServiceTest {


    protected AspectJProxyFactory factory;

    public <T> T setupServiceTest(T service) {
        this.factory = new AspectJProxyFactory(service);
        this.factory.addAspect(new ReturnRepositoryElement());
        return service = this.factory.getProxy();
    }

}
