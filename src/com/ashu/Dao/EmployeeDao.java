package com.ashu.Dao;

import com.ashu.Employee;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;

import java.sql.PreparedStatement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    /*public Object getAllEmployees(){
        String query = "select * from employee";
        return jdbcTemplate.query(query, new ResultSetExtractor() {
           @Override
           public List<Employee> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
               List<Employee> list = new ArrayList<>();
               while (resultSet.next()){
                   Employee e = new Employee();
                   e.setId(resultSet.getInt(1));
                   e.setName(resultSet.getString(2));
                   e.setSalary(resultSet.getInt(3));
                   list.add(e);
               }
               return list;
           }
       });
    }*/

    public List<Employee> getAllEmployees(){
        String query = "select * from employee";
        return jdbcTemplate.query(query, new RowMapper() {
            @Override
            public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
                Employee e = new Employee();
                e.setId(resultSet.getInt(1));
                e.setName(resultSet.getString(2));
                e.setSalary(resultSet.getInt(3));
                return e;
            }
        });
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
