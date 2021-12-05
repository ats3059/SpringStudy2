package hello.servlet.web.frontController.makeV5;

import hello.servlet.web.frontController.ModelView;
import hello.servlet.web.frontController.MyView;
import hello.servlet.web.frontController.makeV5.adaptor.AdaptorHandlerV3;
import hello.servlet.web.frontController.makeV5.adaptor.AdaptorHandlerV4;
import hello.servlet.web.frontController.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontController.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontController.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontController.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontController.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontController.v4.controller.MemberSaveControllerV4;

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

@WebServlet(name = "frontControllerMakeV5", urlPatterns = "/front-controller/v5/make/*")
public class FrontControllerServletMakeV5 extends HttpServlet {


    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyAdaptorHandler> handlerAdapters = new ArrayList<>();

    public FrontControllerServletMakeV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }


    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/make/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/make/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/make/v3/members", new MemberListControllerV3());

        handlerMappingMap.put("/front-controller/v5/make/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/make/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/make/v4/members", new MemberListControllerV4());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new AdaptorHandlerV3());
        handlerAdapters.add(new AdaptorHandlerV4());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object handler = getHandler(req);

        if (handler == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyAdaptorHandler adaptorHandler = getAdaptorHandler(handler);

        ModelView modelView = adaptorHandler.handle(req, resp, handler);
        String viewName = modelView.getViewName();
        MyView realView = viewResolver(viewName);
        realView.render(modelView.getModel(),req,resp);
    }

    private MyAdaptorHandler getAdaptorHandler(Object handler) {
        for (MyAdaptorHandler adaptorHandler : handlerAdapters) {
            if (adaptorHandler.support(handler)) {
                return adaptorHandler;
            }
        }
        throw new IllegalArgumentException("어뎁터가 존재하지 않습니다");
    }

    private Object getHandler(HttpServletRequest req) {
        for (String name : handlerMappingMap.keySet()) {
            if (req.getRequestURI().equals(name)) {
                return handlerMappingMap.get(name);
            }
        }
        return null;
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
