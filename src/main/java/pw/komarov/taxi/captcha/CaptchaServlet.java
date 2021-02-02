package pw.komarov.taxi.captcha;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.cage.Cage;
import com.github.cage.GCage;

import lombok.AllArgsConstructor;

public class CaptchaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String CAPTCHA_RESULT_SESSION_ATTRIBUTE_NAME = "captchaResult";
	
	private static final long captchaTimeout = 3 * (60 * 1000); //3 minutes
	
	@AllArgsConstructor
	private class CaptchaDetails {
		long expiredAt;
		String value;
	}

	private static final Map<HttpSession,CaptchaDetails> captchas = new ConcurrentHashMap<>();
	
	public static void invalidateSession(HttpSession session) {
		captchas.remove(session);
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CaptchaServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		session.removeAttribute(CAPTCHA_RESULT_SESSION_ATTRIBUTE_NAME);
		
		Cage cage = new GCage();
		String value = cage.getTokenGenerator().next();

        try(OutputStream stream = response.getOutputStream()) {
            cage.draw(value, stream);
        }
		
        captchas.put(session, new CaptchaDetails(System.currentTimeMillis() + captchaTimeout, value));
		
        response.setContentType("image/jpeg");
	}
	
	public enum CaptchaResult {BAD_REQUEST, EXPIRED, VALUE, VALID};

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CaptchaResult result = CaptchaResult.BAD_REQUEST;
		HttpSession session = request.getSession();
		String captcha = request.getParameter("captcha");
		if(captcha != null) {
			CaptchaDetails captchaDetails = captchas.get(session);
			if(captchaDetails != null) {
				result = CaptchaResult.EXPIRED;
				if(captchaDetails.expiredAt > System.currentTimeMillis()) { //not expired
					result = CaptchaResult.VALUE;
					if(captcha.equals(captchaDetails.value))
						result = CaptchaResult.VALID;
				}
			}
		}
		
		session.setAttribute(CAPTCHA_RESULT_SESSION_ATTRIBUTE_NAME, result);
		
		if(result != CaptchaResult.VALID)
			response.sendRedirect("captcha");
		else {
			invalidateSession(session);
			String uri = (String)session.getAttribute(CaptchaFilter.CAPTCHA_REDIRECT_URI_SESSION_ATTRIBUTE_NAME);
			if(uri != null) {
				session.removeAttribute(CaptchaFilter.CAPTCHA_REDIRECT_URI_SESSION_ATTRIBUTE_NAME);
				response.sendRedirect(uri);
			} else //index page
				response.sendRedirect(request.getContextPath());
		}
	}

}
