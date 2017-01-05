package pers.liujunyi.bookkeeping.securityFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.google.gson.Gson;

/**
 * 失败
 * @author ljy
 *
 */
public class AjaxAuthenticationFailureHandler implements
		AuthenticationFailureHandler {

	private AuthenticationFailureHandler defaultHandler;
	
	public AjaxAuthenticationFailureHandler(){
		
	}
	
	public AjaxAuthenticationFailureHandler(AuthenticationFailureHandler defaultHandler) {
        this.defaultHandler = defaultHandler;
    }
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
         
		 System.out.println(" ************ 进入登录失败  **************** ");
		 
		    response.setContentType("application/json;charset=UTF-8");
	        response.setHeader("Cache-Control","no-store, max-age=0, no-cache, must-revalidate");
	        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
	        response.setHeader("Pragma", "no-cache");
	        ConcurrentMap<String,Object> map = new ConcurrentHashMap<String, Object>();
			try {
				    map.put("success", false);
				    map.put("message", "登录失败.");
				    Gson gson = new Gson();
		            PrintWriter out = response.getWriter();
		            out.write(gson.toJson(map));
		            out.flush();
		            out.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		 
		/* if ("false".equals(request.getHeader("X-Ajax-call"))) {
		        response.getWriter().print("no");
		        response.getWriter().flush();
		    } else {
		        defaultHandler.onAuthenticationFailure(request, response, exception);
		    }*/

	}

}
