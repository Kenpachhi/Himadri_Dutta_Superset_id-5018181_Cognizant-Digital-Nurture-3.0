package com.employeemanagementsystem.projection;

import org.springframework.beans.factory.annotation.Value;

public interface CustomEmployeeProjection {
    @Value("#{target.name} - #{target.email}")
    String getEmployeeInfo();
}
