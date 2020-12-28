package dao;

import com.ers.dao.EmployeeDAO;
import com.ers.models.Employee;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

public class EmployeeDaoTest {

    @Test
    public void GetKnownEmployeeWithEmployeeId() {
        int id = 1;
        Employee result = EmployeeDAO.getInstance().getById(1);
        Assert.assertTrue(result instanceof Employee);
    }

    @Test
    public void GetEmployeeByEmailAndPassReturnsEmployee() {
        String knownEmail = "ratul@test.com";
        String knownPass = "test";
        Employee result = EmployeeDAO.getInstance().getByEmailAndPassword(knownEmail, knownPass);
        Assert.assertTrue(result instanceof Employee);
    }

    @Test
    public void GetKnownEmployeeByEmailReturnsEmployee() {
        String knownEmail = "ratul@test.com";
        Employee result = EmployeeDAO.getInstance().getByEmail(knownEmail);
        Assert.assertTrue(result instanceof Employee);
    }

    @Test
    public void GetAllEmployeesReturnsCollection() {
        Collection<Employee> result = EmployeeDAO.getInstance().getAll();
        Assert.assertTrue(result instanceof Collection);
    }

    @Test
    public void SavePersistsNewEmployee() {
        Employee validNewUser = new Employee();
        validNewUser.setEmail("EmployeeDaoTest@mail.com");
        validNewUser.setPassword("test");
        EmployeeDAO.getInstance().save(validNewUser);
        Employee result = EmployeeDAO.getInstance().getByEmail("EmployeeDaoTest@mail.com");
        Assert.assertTrue(validNewUser.getEmail().equals(result.getEmail()));
        Assert.assertTrue(validNewUser.getPassword().equals(result.getPassword()));
    }

}
