package de.holisticon.dw.calendar.view;

import com.google.common.base.Supplier;
import io.dropwizard.views.View;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by jangalinski on 08.10.14.
 */
@Path("/hello")
public class SimpleView extends View  implements Supplier<SimpleView> {


    public SimpleView() {
        super("/site.mustache");
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Override
    public SimpleView get() {
        return this;
    }
}
