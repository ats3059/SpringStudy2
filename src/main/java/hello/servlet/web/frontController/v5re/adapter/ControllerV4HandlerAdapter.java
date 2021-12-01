package hello.servlet.web.frontController.v5re.adapter;

import hello.servlet.web.frontController.ModelView;
import hello.servlet.web.frontController.v4.ControllerV4;
import hello.servlet.web.frontController.v5re.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {


    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handle) throws ServletException, IOException {
        ControllerV4 controller = (ControllerV4) handle;

        Map<String,String> paramMap = createParamValues(request);
        Map<String,Object> model = new HashMap<>();

        String viewName = controller.process(paramMap,model);

        ModelView mv = new ModelView(viewName);
        mv.setModel(model);

        return mv;
    }

    private Map<String,String> createParamValues(HttpServletRequest request) {
        Map<String,String> map = new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(value -> map.put(value, request.getParameter(value)));
        return map;
    }


}
