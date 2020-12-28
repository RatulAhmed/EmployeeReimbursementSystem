package controllers;


import com.ers.controllers.EmployeeController;
import com.ers.dao.EmployeeDAO;
import com.ers.models.Employee;
import io.javalin.http.Context;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.List;

import static io.javalin.apibuilder.ApiBuilder.*;

import static org.mockito.Mockito.*;

//@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

    private EmployeeController employeeController;

    @Test
    public void LoginWithCorrectInfo() throws Exception {
        Context ctx = mock(Context.class);

        String email="ratul@test.com";
        String password="test";

        when(ctx.formParam("email")).thenReturn(email);
        when(ctx.formParam("password")).thenReturn(password);

        EmployeeController.login.handle(ctx);
        verify(ctx).formParamMap();
    }

    @Test
    public void SubmitValidReimbursement201Status() throws Exception {
        Context ctx = mock(Context.class);

        when(ctx.pathParam("id")).thenReturn("1");
        when(ctx.formParam("amount")).thenReturn("100");
        when(ctx.formParam("description")).thenReturn("Test description");
        EmployeeController.submitReimbursement.handle(ctx);
        verify(ctx).status(201);
    }

    @Test
    public void CheckIfAuthRedirectWhenNot() throws Exception {
        Context ctx = mock(Context.class);

        when(ctx.sessionAttribute("loggedInEmployee")).thenReturn(null);
        EmployeeController.checkIfAuthenticated.handle(ctx);
        verify(ctx).redirect("/employeeLogin");

    }

    @Test
    public void CheckIfAuthIsAuthenticated() throws Exception {
        Context ctx = mock(Context.class);
        Employee employee = new Employee();
//        when(ctx.sessionAttribute("loggedInEmployee")).then();
        EmployeeController.checkIfAuthenticated.handle(ctx);
        ctx.redirect("/employeeLogin");
    }




}
