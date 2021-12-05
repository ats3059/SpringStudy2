package hello.servlet.web.frontController.makeV5;

import hello.servlet.web.frontController.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MyAdaptorHandler {

    boolean support(Object handler);

    ModelView handle(HttpServletRequest request, HttpServletResponse response , Object handle) throws ServletException, IOException;


}
