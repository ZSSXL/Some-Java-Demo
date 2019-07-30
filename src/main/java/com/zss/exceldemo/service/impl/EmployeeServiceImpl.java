package com.zss.exceldemo.service.impl;

import com.zss.exceldemo.entity.Employee;
import com.zss.exceldemo.repostory.EmployeeRepostory;
import com.zss.exceldemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepostory employeeRepostory;

    public Employee createQRCode(String username){
        Employee tom = this.employeeRepostory.findByUsername(username);
        return tom;
    }

}
