package p12.ocr.zuul.filters;

import ch.qos.logback.classic.Logger;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;


import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Component
public class LogFilter extends ZuulFilter {

    Logger logger = (Logger) LoggerFactory.getLogger(LogFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        HttpServletRequest req = RequestContext.getCurrentContext().getRequest();

        logger.info("**** Requête interceptée ! L'URL est : {} " , req.getRequestURL());

        return null;
    }
}
