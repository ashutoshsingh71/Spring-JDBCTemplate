package com.ashu.Dao;

import com.ashu.Employee;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import java.sql.PreparedStatement;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeDao  {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveEmployee(Employee e){
        String query = "Insert into employee(id,name,salary) values('" + e.getId()+ "','" + e.getName()+"','"+e.getSalary() +"')" ;
        return jdbcTemplate.update(query);
    }

    public int updateEmployee(Employee e){
        String query = "update employee set name ='" + e.getName() + "',salary='"+e.getSalary() +"' where id='" + e.getId()+"'" ;
        return jdbcTemplate.update(query);
    }
    public int deleteEmployee(Employee e){
        String query = "delete from employee where id= " + e.getId() ;
        return jdbcTemplate.update(query);
    }

    public Object saveEmployeeWithPreparedStatement(final Employee e){
        String query = "Insert into employee(id,name,salary) values(?,?,?)";
        return jdbcTemplate.execute(query, new PreparedStatementCallback() {
            @Override
            public Object doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                preparedStatement.setInt(1,e.getId());
                preparedStatement.setString(2,e.getName());
                preparedStatement.setInt(3,e.getSalary());
                preparedStatement.execute();
                return null;
            }
        });
    }
}
