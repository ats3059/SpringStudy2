package hello.servlet.web.servletmvc;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

@WebServlet(name = "mvcMemberListServlet" ,urlPatterns = "/servlet-mvc/members")
public class MvcMemberListServlet extends HttpServlet{
	private MemberRepository memberRepository = MemberRepository.getInstance();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Member> member = memberRepository.findAll();

		req.setAttribute("members", member);
		String viewPath = "/WEB-INF/views/members.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
		dispatcher.forward(req, resp);


	}
}
