package com.jwt.userservice.common.util;

import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import lombok.extern.log4j.Log4j2;
import net.minidev.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Log4j2
public class TokenUtils {

    private TokenUtils() {

    }

    public static Map<String, String> decodeToken(String jwtToken) {
        Map<String, String> body = new HashMap<>();

        try {
            if (jwtToken != null) {
                jwtToken = jwtToken.replace("Bearer", "");
                jwtToken = jwtToken.trim();
            }
            if (StringUtils.isBlank(jwtToken)) {
                return body;
            }
            JWSObject token = JWSObject.parse(jwtToken);

            Payload tokenPayload = token.getPayload();
            JSONObject tokenBody = tokenPayload.toJSONObject();

            tokenBody.forEach((key, value) -> {
                if (Objects.isNull(value)) {
                    value = "";
                }

                body.put(key, value.toString());
            });
        } catch (Exception e) {
            log.error("Failed to parse JWT Token. Error: {}", e.getMessage());
        }

        return body;
    }

    public static Object getValueByParam(String param, String token) {
        return decodeToken(token).get(param);
    }
}
