package pw.komarov.taxi.captcha;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pw.komarov.taxi.captcha.CaptchaServlet.CaptchaResult;

public class CaptchaFilter implements Filter {
	public static final String CAPTCHA_REDIRECT_URI_SESSION_ATTRIBUTE_NAME = "captchaRedirectUri";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}
	
	private boolean captchaRedirectRequired(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session != null) {
			CaptchaResult captchaResult = (CaptchaResult)session.getAttribute(CaptchaServlet.CAPTCHA_RESULT_SESSION_ATTRIBUTE_NAME);
			if(captchaResult != null && captchaResult == CaptchaResult.VALID)
				return false;
		}
		
		return true;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		if(request instanceof HttpServletRequest) {
			HttpServletRequest req = (HttpServletRequest)request;
			String uri = (req.getQueryString() != null)
						? String.join("", req.getRequestURI(),"?" ,req.getQueryString())
						: req.getRequestURI();
			
			System.out.println(uri);
			String path = uri.substring(req.getContextPath().length());
			
			if((!path.startsWith("/rest/")) && (!path.equals("/captcha") && (!path.equals("/captcha.image")))) //don't use it for rest and captcha
				if(captchaRedirectRequired((HttpServletRequest)request)) {
					req.getSession(true).setAttribute(CAPTCHA_REDIRECT_URI_SESSION_ATTRIBUTE_NAME, uri);
					((HttpServletResponse)response).sendRedirect(req.getContextPath() + "/captcha");
					return;
				}
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {}
}