package dao;

import com.ers.dao.ManagerDAO;
import com.ers.models.Manager;
import org.junit.Assert;
import org.junit.Test;

public class ManagerDaoTest {

    @Test
    public void GetByEmailAndPasswordOfKnownManager() {
        String knownEmail = "ratul@manager.com";
        String knownPass = "test";
        Manager manager = ManagerDAO.getInstance().getByEmailAndPassword(knownEmail, knownPass);
        Assert.assertNotNull(manager);
        Assert.assertTrue(manager instanceof Manager);
    }
}
