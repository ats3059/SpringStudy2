package hello.servlet.web.frontController.makeV5.adaptor;

import hello.servlet.web.frontController.ModelView;
import hello.servlet.web.frontController.makeV5.MyAdaptorHandler;
import hello.servlet.web.frontController.v4.ControllerV4;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AdaptorHandlerV4 implements MyAdaptorHandler {

    @Override
    public boolean support(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handle) throws ServletException, IOException {

        ControllerV4 controllerV4 = (ControllerV4)handle;
        Map<String,Object> model = new HashMap<>();
        String viewName = controllerV4.process(createParamMap(request), model);
        ModelView modelView = new ModelView(viewName);
        modelView.setModel(model);

        return modelView;
    }
    private Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String,String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));
        return paramMap;
    }

}
