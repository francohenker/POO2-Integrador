package unam.edu.ecomarket.controladores;


import jakarta.servlet.http.HttpSession;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@NoArgsConstructor
public class publico {

//    @GetMapping("/home")
//    public String homee(HttpSession session) {
//        String user = session.getAttribute("usuario").toString();
//        System.out.println("[!] USUARIO: " + user);
//        return "prueba";
//
//    }


    @GetMapping("/error")
    public String error() {
        return "error";
    }


}

