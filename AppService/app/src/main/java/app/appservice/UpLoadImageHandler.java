package app.appservice;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Created by smark on 2017/3/10.
 */



public class UpLoadImageHandler implements IResourceUriHandler{


    private String acceptPrefix = "/upload_image/";

    @Override
    public boolean accept(String Uri) {

        return Uri.startsWith(acceptPrefix);
    }

    @Override
    public void handle(String uri, HttpContext httpContext)throws IOException {
        String tmpPath = "/mnt/sdcard/test_upload.jpg";
       long  totalLenght = Long.parseLong(httpContext.getRequestHeaderValue("Content-Length"));
        FileOutputStream fos = new FileOutputStream(tmpPath);
        InputStream nis = httpContext.getUnderlySocket().getInputStream();
        byte[] buffer = new byte[10240];
        int nReaded =0 ;
        long nLeftLenght = totalLenght;

        while ((nReaded=nis.read(buffer))>0 && nLeftLenght>0){
            fos.write(buffer,0,nReaded);
            nLeftLenght -=nReaded;

        }

        fos.close();

        OutputStream nos = httpContext.getUnderlySocket().getOutputStream();
        PrintStream printStream = new PrintStream(nos);
        printStream.println("HTTP/1.1 200 OK");
        printStream.println();

        onImageLoaded(tmpPath);
    }



    protected void onImageLoaded(String filePath){

    }
}
