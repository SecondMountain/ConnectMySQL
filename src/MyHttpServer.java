import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;

import java.io.*;
import java.net.InetSocketAddress;

import static Print.Print.println;

/**
 * Created by zyf on 2016/5/18.
 */
public class MyHttpServer {
    public void httpserverService(){
        try {
            HttpServerProvider provider = HttpServerProvider.provider();
            HttpServer server = provider.createHttpServer(new InetSocketAddress(8081),100);
            server.createContext("/test",new myHandle());
            server.setExecutor(null);
            server.start();
            System.out.println("server started");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }
    static class myHandle implements HttpHandler{
        InputStream in;
        BufferedReader reader;
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            String message = "get it";
            in = httpExchange.getRequestBody();
            println(httpExchange.getRequestURI());
            println(httpExchange.getRequestMethod());
            println(httpExchange.getProtocol());
            reader = new BufferedReader(new InputStreamReader(in));
            String temp = null;
            while ((temp = reader.readLine()) != null){
                System.out.println("cleint request:"+temp);
            }
//            HttpURLConnection.HTTP_OK
            httpExchange.setAttribute("text",null);
            httpExchange.sendResponseHeaders(200,message.getBytes().length);
            OutputStream out = httpExchange.getResponseBody();
            out .write(message.getBytes());
            out.flush();
            httpExchange.close();
            println("一次通信结束。");
        }
    }
    public static void main(String[] args){
        new MyHttpServer().httpserverService();
    }
}
