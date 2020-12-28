package services;

import com.ers.models.Manager;
import com.ers.services.ManagerService;
import org.junit.Assert;
import org.junit.Test;

public class ManagerServiceTest {

    @Test
    public void ShouldReturnNullUsernameOrPasswordNull() {
        String email = null;
        String password = null;
        Manager actual = ManagerService.getInstance().authenticate(email, password);
        Assert.assertTrue(actual == null);
    }
}
