package org.zpp.security.core.component;

import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.zpp.security.core.properties.SecurityConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Slf4j
@Component
@AllArgsConstructor
public class SecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private final ObjectMapper objectMapper;

	@Override
	@SneakyThrows
	public void commence(HttpServletRequest request, HttpServletResponse response,
						 AuthenticationException authException) {
		log.error("[异常] - {}", authException.getLocalizedMessage());
		response.setCharacterEncoding("utf-8");
		response.setContentType(SecurityConstants.CONTENT_TYPE_JSON);
		JSONObject obj = new JSONObject();
		if (authException != null) {
			obj.put("code", -1);
			obj.put("msg", authException.getMessage());
		}
		response.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
		PrintWriter printWriter = response.getWriter();
		printWriter.append(objectMapper.writeValueAsString(obj));
	}
}