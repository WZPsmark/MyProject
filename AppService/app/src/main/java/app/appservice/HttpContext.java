package app.appservice;

import java.net.Socket;
import java.util.HashMap;

/**
 * Created by smark on 2017/3/10.
 */

public class HttpContext {

    private Socket underlySocket;
    private HashMap<String,String> requestHeaders;



    public void setUnderlySocket(Socket socket) {
        this.underlySocket = socket;
        requestHeaders = new HashMap<>();
    }


    public Socket getUnderlySocket() {
        return underlySocket;
    }

    public void addRequestHeader(String headerName, String headerValue) {
        requestHeaders.put(headerName,headerValue);
    }


    public String getRequestHeaderValue(String headerName){
        return requestHeaders.get(headerName);
    }

}
