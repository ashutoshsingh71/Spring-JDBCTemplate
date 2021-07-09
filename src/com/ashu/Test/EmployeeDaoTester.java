package com.ashu.Test;

import com.ashu.Dao.EmployeeDao;
import com.ashu.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.List;

public class EmployeeDaoTester {
    public static void main(String [] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        EmployeeDao employeeDao = (EmployeeDao)context.getBean("employeedao");
        /*int status = employeeDao.saveEmployee(new Employee(1,"Amit",10000));
        System.out.println(status);*/
        /*final Employee e = new Employee(3,"Ashish",12000);
        Object status = employeeDao.saveEmployeeWithPreparedStatement(e);
        if(status == null)
            System.out.println("Record inserted successfully");*/
       /*List<Employee> employeeList =  (List<Employee>)employeeDao.getAllEmployees();*/
        List<Employee> employeeList =  employeeDao.getAllEmployees();
        Iterator itr = employeeList.iterator();
        while (itr.hasNext()){
            System.out.println(itr.next());
        }
    }
}
