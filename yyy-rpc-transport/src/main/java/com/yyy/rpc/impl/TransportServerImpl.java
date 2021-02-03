package com.yyy.rpc.impl;

import com.sun.corba.se.spi.activation.ServerHolder;
import com.yyy.rpc.RequestHandler;
import com.yyy.rpc.TransportServer;
import lombok.extern.slf4j.Slf4j;
import model.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Slf4j
public class TransportServerImpl implements TransportServer {
    private RequestHandler requestHandler;
    private Server server;
    @Override
    public void init(int port, RequestHandler requestHandler) {
        this.requestHandler=requestHandler;
        this.server=new Server(port);

        ServletContextHandler servletContextHandler=new ServletContextHandler();
        server.setHandler(servletContextHandler);

        ServletHolder servletHolder=new ServletHolder(new RequestServlet());
        servletContextHandler.addServlet(servletHolder,"/*");

    }

    @Override
    public void start() {
        try {
            /*启动jetty*/
            server.start();
            server.join();
        } catch (Exception e) {
            log.error("server启动失败....."+e.getMessage()+e);
        }
    }

    @Override
    public void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            log.error("sever关闭失败....."+e.getMessage()+e);
        }
    }

    class RequestServlet extends HttpServlet{
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            log.info("client连接......");
            InputStream inputStream=req.getInputStream();
            OutputStream outputStream=resp.getOutputStream();
            if(requestHandler!=null){
                requestHandler.resHandler(inputStream,outputStream);
            }

            outputStream.flush();
        }
    }
}
