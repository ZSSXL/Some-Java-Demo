package com.zss.exceldemo;

import com.zss.exceldemo.entity.Employee;
import com.zss.exceldemo.repostory.EmployeeRepostory;
import com.zss.exceldemo.service.ExportExcelService;
import com.zss.exceldemo.util.QRCodeUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ExcelDemoApplication.class)
public class ExcelDemoApplicationTests {

    @Autowired
    private EmployeeRepostory employeeRepostory;

    @Autowired
    private ExportExcelService exportExcelServiceImpl;

    @Before
    public void before() {
        System.out.println("=========开始测试=========");
    }

    @After
    public void after() {
        System.out.println("=========结束测试=========");
    }

    @Test
    public void saveTest(){

        Employee employee = new Employee().builder()
                .userId("hinihkjnk")
                .username("Jarry")
                .password("123456")
                .phone("13455556666")
                .email("123456@163.com")
                .employee_id("1002")
                .enterprise("阿里妈妈").build();
        this.employeeRepostory.save(employee);
    }

    @Test
    public void findAllTest(){
        int i = 1;
        List<Employee> all = this.employeeRepostory.findAll();
        for (Employee emp : all){
            System.out.println("第"+i+"条员工信息："+emp);
            i++;
        }
    }

    @Test
    public void findByUsernameTest(){
        Employee jarry = this.employeeRepostory.findByUsername("Jarry");
        if(jarry != null){
            System.out.println(jarry);
        }else{
            System.out.println("没有查到");
        }

    }

    @Test
    public void exportExcelToLocalTest(){
        this.exportExcelServiceImpl.exportExcelToLocal();
    }

    @Test
    public void createQRCodePath(){
        // String content = "生成二维码成功";
        String path = "F:\\poi\\";

        Employee employee = this.employeeRepostory.findByUsername("Tom");

        try {
            QRCodeUtil.createQRCode(employee,path,300);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
