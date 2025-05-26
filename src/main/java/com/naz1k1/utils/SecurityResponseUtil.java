package com.naz1k1.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naz1k1.model.Result;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class SecurityResponseUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void writeResponse(HttpServletResponse response, Result<?> result) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
