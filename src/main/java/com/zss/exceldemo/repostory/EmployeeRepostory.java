package com.zss.exceldemo.repostory;

import com.zss.exceldemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepostory extends JpaRepository<Employee, String> {

    @Override
    List<Employee> findAll();

    Employee findByUsername(String username);

}
