package hello.servlet.web.frontController.v5re.adapter;

import hello.servlet.web.frontController.ModelView;
import hello.servlet.web.frontController.v3.ControllerV3;
import hello.servlet.web.frontController.v5re.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handle) throws ServletException, IOException {
        ControllerV3 controller = (ControllerV3) handle;
        Map<String,String> map = createParameter(request);
        ModelView mv = controller.process(map);

        return mv;
    }

    private Map<String,String> createParameter(HttpServletRequest request) {
        Map<String,String> map = new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(value -> map.put(value, request.getParameter(value)));
        return map;
    }
}
