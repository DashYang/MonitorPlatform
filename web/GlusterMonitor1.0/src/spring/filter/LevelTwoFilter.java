package spring.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import monitor.validator.LevelTwoValidator;
import monitor.validator.ValidatorInterface;

import org.springframework.web.filter.OncePerRequestFilter;

public class LevelTwoFilter extends OncePerRequestFilter{
	
	/**
     * @see OncePerRequestFilter#OncePerRequestFilter()
     */
    public LevelTwoFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		logger.info("filter begin to work");
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("USER");
		logger.info(username + " try to visit site " + request.getRequestURL());
		logger.info("begin to validate user's authority");
		if(username == null || username.equals("")){
			logger.info("not such user or not log in!");
			response.sendRedirect("../zero/warning.jsp");
			logger.info("back to index.jsp");
			chain.doFilter(request, response);
			return;
		}
		ValidatorInterface vInterface = new LevelTwoValidator();
		if(vInterface.run(username)){
			logger.info("validate success");
			chain.doFilter(request, response);
			return;
		}else {
			logger.info("validate error!");
			response.sendRedirect("../zero/warning.jsp");
			logger.info("back to index.jsp");
			chain.doFilter(request, response);
			return;
		}
	}
	
}
