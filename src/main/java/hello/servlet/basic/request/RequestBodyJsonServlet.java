package hello.servlet.basic.request;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import hello.servlet.basic.HelloData;

@WebServlet(name = "requestBodyJsonServlet" , urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet{

	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletInputStream inputstream = request.getInputStream();
		String messageBody = StreamUtils.copyToString(inputstream, StandardCharsets.UTF_8);
		HelloData helloData = objectMapper.readValue(messageBody,HelloData.class);
		System.out.println(helloData.getAge());
		System.out.println(helloData.getUsername());
		response.getWriter().write("ok");


	}


}
