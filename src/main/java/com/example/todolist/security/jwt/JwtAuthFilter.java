package com.example.todolist.security.jwt;

import com.example.todolist.common.ResponseMessage;
import com.example.todolist.common.exception.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtUtil.resolveToken(request);

        if(token == null){
            filterChain.doFilter(request,response);
            return;
        }

        if(!jwtUtil.validateToken(token)){
            jwtExceptionHandler(response, ErrorCode.TOKEN_ERROR);
            return;
        }

        Claims info = jwtUtil.getUserInfoFromToken(token);
        setAuthentication(response, info.getSubject());
        filterChain.doFilter(request, response);

    }

    public void setAuthentication(HttpServletResponse response, String email){
        try{
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            Authentication authentication = jwtUtil.createAuthentication(email);
            context.setAuthentication(authentication);

            SecurityContextHolder.setContext(context);
        }catch (UsernameNotFoundException e){

        }
    }

    public void jwtExceptionHandler(HttpServletResponse response, ErrorCode errorCode){
        response.setStatus(errorCode.getStatusCode());
        response.setContentType("application/json; charset=utf8");

        try{
            String json = new ObjectMapper().writeValueAsString(new ResponseMessage<>(errorCode, null));
            response.getWriter().write(json);
        }catch(Exception e){
            log.error(e.getMessage());
        }
    }
}
