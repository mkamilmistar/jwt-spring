package com.jwt.userservice.common.context;

import com.jwt.userservice.common.util.Platform;
import com.jwt.userservice.common.util.TokenScope;
import com.jwt.userservice.common.util.TokenUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
public class ApiContextFactory {

    private ApiContextFactory() {
    }

    private static final String DEFAULT_LANGUAGE = "id-id";
    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String HEADER_ACCEPT_LANGUAGE = HttpHeaders.ACCEPT_LANGUAGE;
    private static final String HEADER_REQUEST_ID = "X-Request-ID";
    private static final String HEADER_CORRELATION_ID = "X-Correlation-ID";
    private static final String HEADER_FORWARDED_FOR = "X-Forwarded-For";
    private static final String HEADER_USER_AGENT = "User-Agent";
    private static final String HEADER_PLATFORM = "X-Platform";
    private static final String HEADER_CLIENT_VERSION = "X-Client-Version";
    private static final String HEADER_DEVICE_ID = "X-Device-ID";
    private static final String HEADER_CHANNEL_ID = "X-Channel-ID";
    private static final String HEADER_API_KEY = "X-API-Key";
    private static final String HEADER_SLEUTH_ID = "X-Sleuth-ID";
    private static final String HEADER_USER_NAME = "X-User-Name";
    private static final String HEADER_USER_ID = "X-User-ID";
    private static final String HEADER_SM_UNIVERSAL_ID = "sm_universalId";
    private static final String HEADER_CUSTOMER_ID = "X-Customer-ID";
    private static final String HEADER_CLIENT_ID = "X-Client-ID";
    private static final String HEADER_CLIENT_SECRET = "X-Client-Secret";
    private static final String HEADER_SECRET_KEY = "X-Secret-Key";
    private static final String HEADER_TIMESTAMP = "X-TIMESTAMP";
    private static final String HEADER_MANDIRI_KEY = "X-MANDIRI-KEY";
    private static final String HEADER_SIGNATURE = "X-SIGNATURE";

    private static final String JWT_USER_ID = "user_id";
    private static final String JWT_CUSTOMER_ID = "customerId";
    private static final String JWT_LOGIN_SCOPE = "scope";
    private static final String JWT_ROLE = "role";
    private static final String JWT_BRANCH_NUMBER = "branchNumber";
    private static final String JWT_TELLER_ID = "tellerId";
    private static final String JWT_TELLER_USER_ID = "tellerUserId";
    private static final String JWT_CONTROL_UNIT = "controlUnit";
    private static final String JWT_PARENT_CONTROL_UNIT = "parentControlUnit";
    private static final String JWT_FULLNAME = "fullname";
    private static final String JWT_CUSTOMER_TYPE = "customerType";
    private static final String JWT_IS_CUSTOMER_REGISTERED = "isCustomerRegistered";

    private static final List<String> ALLOW_HEADER_LIST = List.of(
	    		HEADER_AUTHORIZATION,
	    		HEADER_ACCEPT_LANGUAGE,
	    		HEADER_REQUEST_ID,
	            HEADER_CORRELATION_ID,
	            HEADER_FORWARDED_FOR,
	            HEADER_USER_AGENT,
	            HEADER_PLATFORM,
	            HEADER_CLIENT_VERSION,
	            HEADER_CHANNEL_ID,
	            HEADER_API_KEY,
	            HEADER_DEVICE_ID,
	            HEADER_SLEUTH_ID,
	            HEADER_USER_NAME,
	            HEADER_USER_ID,
	            HEADER_SM_UNIVERSAL_ID,
	            HEADER_CUSTOMER_ID,
	            HEADER_CLIENT_ID,
	            HEADER_CLIENT_SECRET,
	            HEADER_SECRET_KEY,
	            HEADER_TIMESTAMP,
	            HEADER_MANDIRI_KEY,
	            HEADER_SIGNATURE
            ).stream().map(String::toLowerCase).collect(Collectors.toList());

    public static ApiContext generateApiContext() throws IOException {
        ApiContext apiContext = new ApiContext();

        HttpHeaders httpHeaders = constructHttpHeaders();
        String platform = httpHeaders.getFirst(HEADER_PLATFORM);
        String authentication = httpHeaders.getFirst(HEADER_AUTHORIZATION);

        if (authentication != null) {
            authentication = authentication.replace("Bearer", "");
            authentication = authentication.trim();
        }

        if (platform != null && Platform.BACKOFFICE.getCode().equals(platform)) {
            apiContext.setSmUniversalId(httpHeaders.getFirst(HEADER_SM_UNIVERSAL_ID));
            apiContext.setUserId(httpHeaders.getFirst(HEADER_USER_ID));
        } else {
            if (authentication != null) {
                Map<String, String> jwtBodyMap = TokenUtils.decodeToken(authentication);
                apiContext.setUserId(jwtBodyMap.getOrDefault(JWT_USER_ID, ""));
                apiContext.setTokenScope(jwtBodyMap.getOrDefault(JWT_LOGIN_SCOPE, ""));
                apiContext.setRole(jwtBodyMap.getOrDefault(JWT_ROLE, ""));
                apiContext.setBranchNumber(jwtBodyMap.getOrDefault(JWT_BRANCH_NUMBER, ""));
                apiContext.setTellerId(jwtBodyMap.getOrDefault(JWT_TELLER_ID, ""));
                apiContext.setTellerUserId(jwtBodyMap.getOrDefault(JWT_TELLER_USER_ID, ""));
                apiContext.setControlUnit(jwtBodyMap.getOrDefault(JWT_CONTROL_UNIT, ""));
                apiContext.setParentControlUnit(jwtBodyMap.getOrDefault(JWT_PARENT_CONTROL_UNIT, ""));
                apiContext.setUserName(jwtBodyMap.getOrDefault(JWT_FULLNAME, ""));
                apiContext.setCustomerId(jwtBodyMap.getOrDefault(JWT_CUSTOMER_ID, ""));
                apiContext.setCustomerType(jwtBodyMap.getOrDefault(JWT_CUSTOMER_TYPE, ""));
                apiContext.setIsCustomerRegistered(("1").equals(jwtBodyMap.getOrDefault(JWT_IS_CUSTOMER_REGISTERED, "")) ? Boolean.TRUE : Boolean.FALSE);

                if ("login".equals(jwtBodyMap.get(JWT_LOGIN_SCOPE))) {
                    apiContext.setTokenScope(TokenScope.LOGIN.getValue());
                }
            }
        }

        apiContext.setHttpHeaders(httpHeaders);
        apiContext.setAuthorization(authentication);
        apiContext.setLanguage(httpHeaders.getFirst(HEADER_ACCEPT_LANGUAGE));
        apiContext.setRequestId(httpHeaders.getFirst(HEADER_REQUEST_ID));
        apiContext.setCorrelationId(httpHeaders.getFirst(HEADER_CORRELATION_ID));
        apiContext.setForwardedFor(httpHeaders.getFirst(HEADER_FORWARDED_FOR));
        apiContext.setUserAgent(httpHeaders.getFirst(HEADER_USER_AGENT));
        apiContext.setPlatform(httpHeaders.getFirst(HEADER_PLATFORM));
        apiContext.setClientVersion(httpHeaders.getFirst(HEADER_CLIENT_VERSION));
        apiContext.setChannelId(httpHeaders.getFirst(HEADER_CHANNEL_ID));
        apiContext.setDeviceId(httpHeaders.getFirst(HEADER_DEVICE_ID));
        apiContext.setSleuthId(httpHeaders.getFirst(HEADER_SLEUTH_ID));
        apiContext.setApiKey(httpHeaders.getFirst(HEADER_API_KEY));
        apiContext.setClientId(httpHeaders.getFirst(HEADER_CLIENT_ID));
        apiContext.setClientSecret(httpHeaders.getFirst(HEADER_CLIENT_SECRET));
        apiContext.setSecretKey(httpHeaders.getFirst(HEADER_SECRET_KEY));
        apiContext.setTimestamp(httpHeaders.getFirst(HEADER_TIMESTAMP));
        apiContext.setMandiriKey(httpHeaders.getFirst(HEADER_MANDIRI_KEY));
        apiContext.setSignature(httpHeaders.getFirst(HEADER_SIGNATURE));

        return apiContext;
    }

    public static HttpHeaders constructHttpHeaders() throws IOException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpServletRequest curRequest = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes()).getRequest();
        Enumeration<String> headerNames = curRequest.getHeaderNames();

        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String header = headerNames.nextElement();
                String value = curRequest.getHeader(header);
                if (ALLOW_HEADER_LIST.contains(header)) {
                    log.debug("Adding header {} with value {}", header, value);
                    httpHeaders.add(header, value);
                } else {
                    log.debug("Header {} with value {} is not required to be copied", header,
                            value);
                }
            }
        }

        if (!httpHeaders.containsKey(HEADER_ACCEPT_LANGUAGE) || StringUtils
                .isEmpty(httpHeaders.getFirst(
                        HEADER_ACCEPT_LANGUAGE))) {
            httpHeaders.set(HEADER_ACCEPT_LANGUAGE, DEFAULT_LANGUAGE);
        }

        return httpHeaders;
    }
}
