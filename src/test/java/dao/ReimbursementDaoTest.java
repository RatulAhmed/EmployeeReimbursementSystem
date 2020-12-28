package dao;

import com.ers.dao.ReimbursementDAO;
import com.ers.models.Reimbursement;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

public class ReimbursementDaoTest {

    @Test
    public void GetAllReimbursementsReturnsCollection() {
        Collection<Reimbursement> result = ReimbursementDAO.getInstance().getAll();
        Assert.assertTrue(result instanceof Collection);
    }

    @Test
    public void GetAllReimbursementsForEmployeeByStatusReturnsList() {
        int knownId = 1;
        String status = "PENDING";

        List<Reimbursement> actual = ReimbursementDAO.getInstance().getAllForEmployeeByStatus(knownId, status);
        Assert.assertTrue(actual instanceof List);
    }

    @Test
    public void GetAllForEmployeeReturnsList() {
        int knownId = 1;
        List<Reimbursement> actual = ReimbursementDAO.getInstance().getAllForEmployee(knownId);
        Assert.assertNotNull(actual);
        Assert.assertTrue(actual instanceof List);
    }

    @Test
    public void PersistNewReimbursement() {
        Reimbursement validNewReimbursement = new Reimbursement();
        validNewReimbursement.setAmount(10);
        validNewReimbursement.setDescription("DAO-TEST");
        validNewReimbursement.setStatus("PENDING");
        validNewReimbursement.setEmployee_id(2);
        ReimbursementDAO.getInstance().save(validNewReimbursement);
        List<Reimbursement> actual = ReimbursementDAO.getInstance().getAllForEmployee(2);
        Assert.assertTrue(actual.contains(validNewReimbursement));
    }

    @Test
    public void GetAllByStatusPendingReturnsListOfPending() {
        String correctStatus = "PENDING";
        List<Reimbursement> actual = ReimbursementDAO.getInstance().getAllByStatus(correctStatus);
        Assert.assertTrue(actual instanceof List);
        Assert.assertNotNull(actual);
        Assert.assertTrue(actual.get(0).getStatus().equals(correctStatus));
    }

    @Test
    public void GetAllByStatusResolvedReturnsListOfPending() {
        String correctStatus = "RESOLVED";
        List<Reimbursement> actual = ReimbursementDAO.getInstance().getAllByStatus(correctStatus);
        Assert.assertTrue(actual instanceof List);
        Assert.assertNotNull(actual);
        Assert.assertTrue(actual.get(0).getStatus().equals(correctStatus));
    }

}
