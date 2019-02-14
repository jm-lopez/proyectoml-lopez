package org.example;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
    name = "HelloAppEngine",
    urlPatterns = {"/clima"}
)
public class HelloAppEngine extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {

    //response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    
    //HttpServletResponse resp;
    response.setContentType("application/json");
    response.setHeader("Cache-Control", "no-cache");
    
    String option = request.getParameter("dia");
    
    //response.getWriter().print("Option es "+option);
    
    if (isInteger(option))
    {
    	int result = Integer.parseInt(option);
		universo nuevo_universo = new universo();
		nuevo_universo.getFerengi().setDias_actuales(result-1);
		nuevo_universo.getBetasoide().setDias_actuales(result-1);
		nuevo_universo.getVulcano().setDias_actuales(result-1);
		response.getWriter().print("{\"dia\":\""+result+"\",\"clima\":\""+nuevo_universo.calcular_clima()+"\"}");
    }
    else
    {
    	response.getWriter().print("{\"error\":\"El dia ingresado no es valido.\"}");
    }
    
    /*if (option.equals("prueba"))
    {
    	response.getWriter().print("Hello App Engine!\r\n A proposito, esto es una prueba");
    }
    else
    {
    	response.getWriter().print("Hello App Enginea123123!\r\n");
    }*/
  }
  
  public boolean isInteger(String s) {
	    return isInteger(s,10);
	}

  public boolean isInteger(String s, int radix) {
	  if(s.isEmpty()) return false;
	    for(int i = 0; i < s.length(); i++) {
	        if(i == 0 && s.charAt(i) == '-') {
	            if(s.length() == 1) return false;
	            else continue;
	        }
	        if(Character.digit(s.charAt(i),radix) < 0) return false;
	    }
	    return true;
	}
}