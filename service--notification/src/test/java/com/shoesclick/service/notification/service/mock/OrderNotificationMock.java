package com.shoesclick.service.notification.service.mock;

import com.shoesclick.service.notification.entity.OrderNotification;
import com.shoesclick.service.notification.enums.TypeTemplate;

public class OrderNotificationMock {

    private OrderNotificationMock(){}

    public static OrderNotification getOrderNotification() {
        return new OrderNotification().setIdOrder(1L).setIdCustomer(1L).setTypeTemplate(TypeTemplate.CREATE_ORDER);
    }

}
