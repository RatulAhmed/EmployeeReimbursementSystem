package services;

import com.ers.models.Employee;
import com.ers.services.EmployeeService;
import org.junit.Assert;
import org.junit.Test;

public class EmployeeServiceTests {

    @Test
    public void ShouldReturnNullUsernameOrPasswordNull() {
        String email = null;
        String password = null;
        Employee actual =EmployeeService.getInstance().authenticate(email, password);
        Assert.assertTrue(actual == null);
    }

}
