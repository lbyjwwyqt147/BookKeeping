package pers.liujunyi.bookkeeping.securityFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.google.gson.Gson;

/**
 * ajax 成功后操作
 * @author ljy
 *
 */
public class AjaxAuthenticationSuccessHandler implements
		AuthenticationSuccessHandler {

	 private AuthenticationSuccessHandler defaultHandler;
	
	 public AjaxAuthenticationSuccessHandler() {

	    }
	    public AjaxAuthenticationSuccessHandler(AuthenticationSuccessHandler defaultHandler) {
	        this.defaultHandler = defaultHandler;
	    }

	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		 System.out.println(" ************ 进入登录成功  **************** ");
		 
			response.setContentType("application/json;charset=UTF-8");
	        response.setHeader("Cache-Control","no-store, max-age=0, no-cache, must-revalidate");
	        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
	        response.setHeader("Pragma", "no-cache");
	        ConcurrentMap<String,Object> map = new ConcurrentHashMap<String, Object>();
			try {
				    map.put("success", true);
				    map.put("message", "登录成功.");
				    Gson gson = new Gson();
		            PrintWriter out = response.getWriter();
		            out.write(gson.toJson(map));
		            out.flush();
		            out.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		 
		 /*if ("true".equals(request.getHeader("X-Ajax-call"))) {
		        response.getWriter().print("ok");
		        response.getWriter().flush();
		    } else {
		        defaultHandler.onAuthenticationSuccess(request, response, authentication);
		    }*/

	}

}
