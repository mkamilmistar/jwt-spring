package com.jwt.userservice.common.util;

import com.jwt.userservice.common.context.ApiContext;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Log4j2
@Component
public class HttpHeadersInterceptor implements ClientHttpRequestInterceptor {

    private ApiContext apiContext;

    public HttpHeadersInterceptor(ApiContext apiContext) {
        this.apiContext = apiContext;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
            ClientHttpRequestExecution execution) throws IOException {
        apiContext.getHttpHeaders().entrySet().stream()
                .filter(x -> x.getKey() != null)
                .forEach(x -> request.getHeaders().add(x.getKey(), x.getValue().get(0)));

        request.getHeaders().set("Content-Type", "application/json");
        request.getHeaders().set("Accept", "application/json");
        return execution.execute(request, body);
    }

}
