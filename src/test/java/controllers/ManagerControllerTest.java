package controllers;

import com.ers.controllers.ManagerController;
import com.ers.models.Manager;
import io.javalin.http.Context;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ManagerControllerTest {

    @Test
    public void ManagerLoginCorrectCredentials() throws Exception {
        Context context = mock(Context.class);
        String email="ratul@manager.com";
        String password="test";

        when(context.formParam("email")).thenReturn(email);
        when(context.formParam("password")).thenReturn(password);
        when(context.sessionAttribute("loggedInManager")).thenReturn(null);
        ManagerController.login.handle(context);

    }


}
