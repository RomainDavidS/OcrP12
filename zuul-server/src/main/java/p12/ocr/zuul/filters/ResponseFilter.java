package p12.ocr.zuul.filters;

import ch.qos.logback.classic.Logger;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;


import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

@Component
public class ResponseFilter extends ZuulFilter {

    Logger logger = (Logger) LoggerFactory.getLogger(ResponseFilter.class);

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() throws ZuulException {

        HttpServletResponse response = RequestContext.getCurrentContext().getResponse();

        response.setStatus(400);

        logger.info(" CODE HTTP {} ", response.getStatus());


        return null;
    }
}
