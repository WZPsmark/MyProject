package app.appservice;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * Created by smark on 2017/3/10.
 */

public class ResourceInAssetsHandler implements IResourceUriHandler{


    private String acceptPrefix = "/static/";
    private Context context;


    public ResourceInAssetsHandler(Context context) {
        this.context = context;
    }

    @Override
    public boolean accept(String Uri) {

        return Uri.startsWith(acceptPrefix);
    }

    @Override
    public void handle(String uri, HttpContext httpContext)throws IOException {
//        OutputStream mos = httpContext.getUnderlySocket().getOutputStream();
//        PrintWriter printWriter = new PrintWriter(mos);
//        printWriter.write("HTTP/1.1 200 OK");
//        printWriter.println();
//        printWriter.println("from resource in assets handler");
//        printWriter.flush();

        int startIndex = acceptPrefix.length();
        String assetsPath = uri.substring(startIndex);
         InputStream fis=context.getAssets().open(assetsPath);
        byte[]  raw = StreamToolkit.readRawFromStream(fis);

        fis.close();
        OutputStream nos = httpContext.getUnderlySocket().getOutputStream();
        PrintStream printStream = new PrintStream(nos);
        printStream.println("HTTP/1.1 200 Ok");
        printStream.println("Content-Length:"+raw.length);
        if(assetsPath.endsWith(".html")){
            printStream.println("Content-Type:text/html");
        }else if(assetsPath.endsWith(".js")){
            printStream.println("Content-Type:text/js");
        }else if(assetsPath.endsWith(".css")){
            printStream.println("Content-Type:text/css");
        }else if(assetsPath.endsWith(".jpg")){
            printStream.println("Content-Type:text/jpg");
        }
        printStream.println();
        printStream.write(raw);




    }
}
