package web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ContadorVisitasServlet")
public class ContadorVisitasServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        //Inicializar contador
        int contador = 0;
        
        //Leemos las cookies del navegador de nuestro cliente
        Cookie[] cookies = request.getCookies();
        
        
        /*Comprobamos si ya existe una cookie creada con anterioridad llamada
         contadorVisitas*/
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("contadorVisitas")){
                    
                    contador = Integer.parseInt(c.getValue());
                    
                    break;

                }
            }

        }
        //Ahora incrementamos nuestra cookie contadorVisitas en 1
        contador++;
        
        //Creamos la cookie con el valor del contador actualizado
        Cookie c = new Cookie("contadorVisitas",Integer.toString(contador));
        
        //Establecemos un tiempo maximo de 1 hora para nuestra cookie
        c.setMaxAge(3600);//Esta en segundos, 1 hora
        response.addCookie(c);
        
        //Establecemos el tipo de contenido de respuesta
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.print("Contador de visitas de cada cliente: "+contador);
        out.close();
    }
}
