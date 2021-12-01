package hello.servlet.web.frontController.v5re;

import hello.servlet.web.frontController.ModelView;
import hello.servlet.web.frontController.MyView;
import hello.servlet.web.frontController.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontController.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontController.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontController.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontController.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontController.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontController.v5re.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontController.v5re.adapter.ControllerV4HandlerAdapter;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet(name = "frontControllerServletV5re" , urlPatterns = "/front-controller/v5/re/*")
public class FrontControllerServletV5 extends HttpServlet {

    private final Map<String,Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }


    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/re/v3/members/new-form",new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/re/v3/members/save",new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/re/v3/members",new MemberListControllerV3());

        handlerMappingMap.put("/front-controller/v5/re/v4/members/new-form",new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/re/v4/members/save",new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/re/v4/members",new MemberListControllerV4());
    }
    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object handler = getHandler(req);

        if(handler == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdapter handlerAdapter = getHandlerAdapter(handler);
        ModelView modelView = handlerAdapter.handle(req, resp, handler);

        MyView myView = viewResolver(modelView.getViewName());

        myView.render(modelView.getModel(),req, resp);

    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter handlerAdapter : handlerAdapters) {
            if (handlerAdapter.supports(handler)) {
                return handlerAdapter;
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다.");
    }


    private Object getHandler(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        Object handler = handlerMappingMap.get(requestURI);
        return handler;
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
