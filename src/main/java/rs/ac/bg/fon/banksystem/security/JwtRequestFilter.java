package rs.ac.bg.fon.banksystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;
import rs.ac.bg.fon.banksystem.utils.JwtTokenUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    JwtTokenUtil tokenUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
final String headerAuth=request.getHeader("Authorisation");
if(headerAuth!=null && headerAuth.startsWith("Bearer ")){
    String jwt=headerAuth.substring(7);
    String username=tokenUtil.getUsernameFromToken(jwt);
}
    }
}
