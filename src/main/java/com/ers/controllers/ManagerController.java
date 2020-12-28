package com.ers.controllers;

import com.ers.models.Manager;
import com.ers.services.ManagerService;
import io.javalin.http.Handler;

public class ManagerController {


    /**
     ********** Manager Auth Handlers *************
     */

    /**
     * A simple login method
     * Will return a 403 Forbidden in the case of bad credentials
     */
    public static Handler login = ctx -> {
        Manager manager = null;

        System.out.println("we are here in manager controller");
        try {
            if (ctx.formParamMap().containsKey("email") && ctx.formParamMap().containsKey("password")) {
                manager = ManagerService.getInstance().authenticate(ctx.formParam("email"), ctx.formParam("password"));

                // 403 forbidden - incorrect credentials
                if(manager == null) {
                    ctx.status(403);
                    ctx.redirect("/managerLogin");
                }
                else {
                    // now we have to build the correct response and set Session info
                    ctx.status(200);
                    ctx.sessionAttribute("loggedInManager", manager);
                    ctx.redirect("/managerHome");
                }
            }
        } catch (Exception exception) {
            System.out.println("Error");
            exception.printStackTrace();
        }
    };

    /**
     * Will log the user out and invalidate the sessions
     * as well as removing the user from the sessionattribute
     */
    public static Handler logout = ctx -> {
        ctx.req.getSession().invalidate();
        ctx.sessionAttribute("loggedInManager", null);
        ctx.redirect("/");
    };

}
