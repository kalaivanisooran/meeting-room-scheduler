package cts.rabobank.glassdoorscheduler.config;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilter  implements Filter {

	private final Logger log = LoggerFactory.getLogger(CORSFilter.class);

	@Override
	public void init(FilterConfig filterConfig) {
		log.debug("Cors Filter Invoked..............");
	}

	@Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "PATCH,POST,GET,OPTIONS,DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers",
				"x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN");

		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			log.debug("Filter OPTIONS INVOKED..............");
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(req, res);
		}
    }
}
