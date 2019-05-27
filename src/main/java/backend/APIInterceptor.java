package backend;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class APIInterceptor extends HandlerInterceptorAdapter {

  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

  @Override
  public boolean preHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler) {
    printLog(request);
    return true;
  }

  private void printLog(HttpServletRequest request) {
    System.out.println("=========================");
    System.out.println("Received request at " + formatter.format(new Date()));
    System.out.println("METHOD: " + request.getMethod());
    System.out.println("QUERY: " + request.getQueryString());
    System.out.println("FROM: " + request.getRemoteAddr());
    System.out.println("=========================\n");
  }

  @Override
  public void afterCompletion(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      Exception ex) {
    //
  }
}