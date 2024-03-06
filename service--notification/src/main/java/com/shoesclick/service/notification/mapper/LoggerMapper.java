package com.shoesclick.service.notification.mapper;

import com.shoesclick.service.notification.entity.Log;
import com.shoesclick.service.notification.enums.TypeLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel= MappingConstants.ComponentModel.SPRING)
public interface LoggerMapper {

    @Mapping(target = "className", source = "className")
    @Mapping(target = "methodName", source = "methodName")
    @Mapping(target = "details", source = "message")
    @Mapping(target = "typeLog", source = "typeLog")
    Log map(String className, String methodName, String message, TypeLog typeLog);

}
