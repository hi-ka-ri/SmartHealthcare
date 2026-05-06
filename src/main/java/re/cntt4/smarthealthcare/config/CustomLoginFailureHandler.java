package re.cntt4.smarthealthcare.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class CustomLoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception)
            throws IOException {

        String errorMessage = "Email hoặc mật khẩu không chính xác";

        String encodedMessage = URLEncoder.encode(errorMessage, StandardCharsets.UTF_8);

        response.sendRedirect("/login?error=true&message=" + encodedMessage +
                "&username=" + request.getParameter("username"));
    }
}