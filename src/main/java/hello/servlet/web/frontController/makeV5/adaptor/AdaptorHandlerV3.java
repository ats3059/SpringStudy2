package hello.servlet.web.frontController.makeV5.adaptor;

import hello.servlet.web.frontController.ModelView;
import hello.servlet.web.frontController.makeV5.MyAdaptorHandler;
import hello.servlet.web.frontController.v3.ControllerV3;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AdaptorHandlerV3 implements MyAdaptorHandler {

    Map<String,String> map = new HashMap<>();

    @Override
    public boolean support(Object handler) {
        return (handler instanceof ControllerV3);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handle) throws ServletException, IOException {

        ControllerV3 controllerV3 = (ControllerV3)handle;
        createParamMap(request);
        ModelView modelView = controllerV3.process(map);

        return modelView;
    }

    private void createParamMap(HttpServletRequest request) {
        request.getParameterNames().asIterator().forEachRemaining((name) -> map.put(name, request.getParameter(name)));
    }
}
