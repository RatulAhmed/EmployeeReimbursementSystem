package services;

import com.ers.models.Reimbursement;
import com.ers.services.EmployeeService;
import com.ers.services.ReimbursementService;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ReimbursementServiceTest {

    @Test
    public void checkPendingRequestsForEmployeeIsList() {
        int knownId = 1;
        List<Reimbursement> reimbursementList = ReimbursementService.getInstance().getAllPendingReimbursementsForEmployee(1);
        Assert.assertTrue(reimbursementList instanceof List);
        Assert.assertNotNull(reimbursementList);
    }


    @Test
    public void checkResolvedRequestsForEmployeeIsList() {
        int knownId = 1;
        List<Reimbursement> reimbursementList = ReimbursementService.getInstance().getAllResolvedReimubrsements();
        Assert.assertTrue(reimbursementList instanceof List);
        Assert.assertNotNull(reimbursementList);
    }
}
