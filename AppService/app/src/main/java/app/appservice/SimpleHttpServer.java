package app.appservice;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by smark on 2017/3/10.
 */

public class SimpleHttpServer {
    private boolean  isEnable;
    private WebConfiguration configuration;
    private ServerSocket socket;
    private ExecutorService threadPool;
    private Set<IResourceUriHandler> resourceHandelrs;

    public SimpleHttpServer(WebConfiguration webConfiguration){
        this.configuration=webConfiguration;
        threadPool = Executors.newCachedThreadPool();
        resourceHandelrs = new HashSet<>();
    }


    /**
     * 启动server(异步)
     */
    public void startAsync(){
        isEnable =true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                doProSync();
            }

        }).start();


    }

    /**
     * 结束server
     */
    public void stopAsync(){
        if (!isEnable){
            return;
        }
        isEnable = false;
        try {
            socket.close();
            socket=null;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public void registerResourceHandler(IResourceUriHandler handler){
        resourceHandelrs.add(handler);
    }


    /**
     * 同步socket服务操作
     */
    private void doProSync() {
        try {
            InetSocketAddress socketarr = new InetSocketAddress(configuration.getPort());
            socket = new ServerSocket();
            socket.bind(socketarr);
            while (isEnable){
                final Socket remotePeer = socket.accept();
                threadPool.submit(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("suu","a remote peer accept.." +remotePeer.getRemoteSocketAddress().toString());
                        onAcceptRemotePeer(remotePeer);
                    }
                });
            }
        } catch (IOException e) {
            Log.e("spy", "doProSync: "+e.toString() );
        }
    }


    /**
     * 处理数据
     * @param remotePeer
     */
    private void onAcceptRemotePeer(Socket remotePeer) {

        try {
            HttpContext httpContext = new HttpContext();
            httpContext.setUnderlySocket(remotePeer);
            InputStream nis = remotePeer.getInputStream();
            String headerLine=null;
            String resourceUri=headerLine = StreamToolkit.readLine(nis).split(" ")[1];
            Log.d("uri", resourceUri);
            while ((headerLine=StreamToolkit.readLine(nis))!=null){
                if(headerLine.equals("\r\n")){
                    break;
                }
                String[] pair = headerLine.split(":");
                httpContext.addRequestHeader(pair[0],pair[1]);
                Log.d("nis", headerLine);
            }

            for (IResourceUriHandler handler: resourceHandelrs) {
                if(handler.accept(resourceUri)){
                    continue;
                }
                handler.handle(resourceUri,httpContext);

            }
        } catch (IOException e) {
            Log.e("IOException", "onAcceptRemotePeer: "+e.toString() );
        }

    }


}
