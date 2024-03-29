package hello.servlet.basic.request;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		printStartLine(request);
		printHeaders(request);
		printHeaderUtils(request);
	}

	// start line ����
	private void printStartLine(HttpServletRequest request) {
		System.out.println("--- REQUEST-LINE - start ---");
		System.out.println("request.getMethod() = " + request.getMethod()); // GET
		System.out.println("request.getProtocal() = " + request.getProtocol()); // HTTP/1.1
		System.out.println("request.getScheme() = " + request.getScheme()); // http
		// http://localhost:8080/request-header
		System.out.println("request.getRequestURL() = " + request.getRequestURL());
		// /request-test
		System.out.println("request.getRequestURI() = " + request.getRequestURI());
		// username=hi
		System.out.println("request.getQueryString() = " + request.getQueryString());
		System.out.println("request.isSecure() = " + request.isSecure()); // https ��� ����
		System.out.println("--- REQUEST-LINE - end ---");
		System.out.println();
	}

	// Header ��� ����
	private void printHeaders(HttpServletRequest request) {
		System.out.println("--- Headers - start ---");
		/*
		 * Enumeration<String> headerNames = request.getHeaderNames(); while
		 * (headerNames.hasMoreElements()) { String headerName =
		 * headerNames.nextElement(); System.out.println(headerName + ": " +
		 * request.getHeader(headerName)); }
		 */
//		request.getHeaderNames().asIterator()
//				.forEachRemaining(headerName -> System.out.println(headerName + ":" + request.getHeader(headerName)));
		System.out.println("--- Headers - end ---");
		System.out.println();
	}

	// Header ���� ��ȸ
	private void printHeaderUtils(HttpServletRequest request) {
		System.out.println("--- Header ���� ��ȸ start ---");
		System.out.println("[Host ���� ��ȸ]");
		System.out.println("request.getServerName() = " + request.getServerName()); // Host ���
		System.out.println("request.getServerPort() = " + request.getServerPort()); // Host ���
		System.out.println();
		System.out.println("[Accept-Language ���� ��ȸ]");
//		request.getLocales().asIterator().forEachRemaining(locale -> System.out.println("locale = " + locale));
		System.out.println("request.getLocale() = " + request.getLocale());
		System.out.println();
		System.out.println("[cookie ���� ��ȸ]");
		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				System.out.println(cookie.getName() + ": " + cookie.getValue());
			}
		}
		System.out.println();
		System.out.println("[Content ���� ��ȸ]");
		System.out.println("request.getContentType() = " + request.getContentType());
		System.out.println("request.getContentLength() = " + request.getContentLength());
		System.out.println("request.getCharacterEncoding() = " + request.getCharacterEncoding());
		System.out.println("--- Header ���� ��ȸ end ---");
		System.out.println();
	}

}
