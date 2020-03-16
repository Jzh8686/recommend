package com.gyj.gx.base.filter;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

@Component
@WebFilter(urlPatterns = { "/" }, filterName = "jsonLogFilter")
public class JsonLogFilter implements Filter {

    public static final String JSON_STRING = "JSONSTRING";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        ResettableStreamHttpServletRequest wrappedRequest = new ResettableStreamHttpServletRequest(
                (HttpServletRequest) request);
        if(isJson((HttpServletRequest)request)){
            String body = IOUtils.toString(wrappedRequest.getInputStream()).replace("\n", "");
            request.setAttribute(JSON_STRING, body);
            wrappedRequest.resetInputStream();
        }

        chain.doFilter(wrappedRequest, response);

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // Nothing to do
    }

    @Override
    public void destroy() {
        // Nothing to do
    }

    private static class ResettableStreamHttpServletRequest extends HttpServletRequestWrapper {

        private byte[] rawData;
        private HttpServletRequest request;
        private ResettableServletInputStream servletStream;

        public ResettableStreamHttpServletRequest(HttpServletRequest request) {
            super(request);
            this.request = request;
            this.servletStream = new ResettableServletInputStream();
        }


        public void resetInputStream() {
            servletStream.stream = new ByteArrayInputStream(rawData);
        }

        @Override
        public ServletInputStream getInputStream() throws IOException {
            if (rawData == null) {
                rawData = IOUtils.toByteArray(this.request.getInputStream());
                servletStream.stream = new ByteArrayInputStream(rawData);
            }
            return servletStream;
        }

        @Override
        public BufferedReader getReader() throws IOException {
            if (rawData == null) {
                rawData = IOUtils.toByteArray(this.request.getReader(),"utf-8");
                servletStream.stream = new ByteArrayInputStream(rawData);
            }
            return new BufferedReader(new InputStreamReader(servletStream));
        }


        private class ResettableServletInputStream extends ServletInputStream {

            private InputStream stream;

            @Override
            public int read() throws IOException {
                return stream.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }
        }
    }

    private boolean isJson(final HttpServletRequest request) {
        return request.getContentType()!=null && request.getContentType().startsWith("application/json");
    }

}
