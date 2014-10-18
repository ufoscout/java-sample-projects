package ufo.jetty9.reverseproxy;

import java.net.URI;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.proxy.AsyncProxyServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

public class UfoProxyServlet extends AsyncProxyServlet {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
	    super.init(config);
	    SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}

	@Override
	protected URI rewriteURI(HttpServletRequest request) {
	    String proxyTo = getProxyTo(request);
	    if (proxyTo == null) {
			return null;
		}
	    String path = getRemotePath(request.getRequestURI());
	    String query = request.getQueryString();
	    if (query != null) {
			path += "?" + query;
		}
	    logger.debug("Proxy call for [{}] to [{}]", path, proxyTo);
	    return URI.create(proxyTo + "/" + path).normalize();
	}

	private String getProxyTo(HttpServletRequest request) {
	 /*
	 *  Implement this method: All the magic happens here. Use this method to figure out your destination machine address. You can maintain
	 *  a static list of addresses, and depending on the URI or request content you can route your request transparently.
	 */
		return StaticUglyProxyConfig.remoteUrl;
	}

	private String getRemotePath(String localPath) {
		return localPath.replaceAll("proxy", "rest");
	}

}
