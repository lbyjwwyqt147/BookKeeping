package pers.liujunyi.bookkeeping.securityFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.google.gson.Gson;

public class MySpringAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request,
			HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException,
			ServletException {
		
		boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
		//如果是ajax请求
		if (isAjax) {
			response.setContentType("application/json;charset=UTF-8");
	        response.setHeader("Cache-Control","no-store, max-age=0, no-cache, must-revalidate");
	        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
	        response.setHeader("Pragma", "no-cache");
	        ConcurrentMap<String,Object> map = new ConcurrentHashMap<String, Object>();
		    map.put("success", false);
		    map.put("message", "403.");
		    Gson gson = new Gson();
            PrintWriter out = response.getWriter();
            out.write(gson.toJson(map));
            out.flush();
            out.close();
		
		return;
		}
		else if (!response.isCommitted()) {
			if (clientErrorPage != null) {
			// Put exception into request scope (perhaps of use to a view)
				request.setAttribute(WebAttributes.ACCESS_DENIED_403, accessDeniedException);
			// Set the 403 status code. 
				response.setStatus(HttpServletResponse.SC_FORBIDDEN); 
			// forward to error page.
			RequestDispatcher dispatcher = request.getRequestDispatcher(clientErrorPage);
			dispatcher.forward(request, response); 
			} else {
				response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage()); }
			}
			
		

	}
	
	private String clientErrorPage;
	public void setClientErrorPage(String clientErrorPage) {
	if((clientErrorPage != null)&&!clientErrorPage.startsWith("/")){
	    throw new IllegalArgumentException("errorPage must begin with '/'");
	}
	this.clientErrorPage = clientErrorPage;
	}


}
