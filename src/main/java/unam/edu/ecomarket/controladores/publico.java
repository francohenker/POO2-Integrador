package unam.edu.ecomarket.controladores;


import jakarta.servlet.http.HttpSession;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@NoArgsConstructor
public class publico {

    @GetMapping("/error")
    public String error() {
        return "error";
    }

    @GetMapping("/comprar")
    public String compra(){
        return "fragments/comprar";
    }
    @GetMapping("/ca")
    public String comprar(){
        return "fragments/carousel";
    }
}

