package hello.servlet.basic.response;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import hello.servlet.basic.HelloData;

@WebServlet(name = "responseJsonServlet" , urlPatterns = "/response-json")
public class ResponseJsonServlet  extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("applcation/json");
		resp.setCharacterEncoding("utf-8");

		HelloData helloData = new HelloData();
		helloData.setAge(20);
		helloData.setUsername("kim");

		ObjectMapper objectMapper = new ObjectMapper();
		String result = objectMapper.writeValueAsString(helloData);

		PrintWriter pw = resp.getWriter();
		pw.print(result);


	}

}
