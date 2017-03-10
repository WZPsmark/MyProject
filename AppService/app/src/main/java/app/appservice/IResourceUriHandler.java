package app.appservice;

import java.io.IOException;

/**
 * Created by smark on 2017/3/10.
 */

public interface IResourceUriHandler {
    boolean accept(String Uri);
    void handle(String uri,HttpContext httpContext) throws IOException;
}
