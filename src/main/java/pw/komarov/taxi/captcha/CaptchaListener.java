package pw.komarov.taxi.captcha;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class CaptchaListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		CaptchaServlet.invalidateSession(se.getSession());
	}
}