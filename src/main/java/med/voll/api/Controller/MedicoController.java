package med.voll.api.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import med.voll.api.medico.DatosRegistroMedico;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    
    @PostMapping
    public void registrarMedico(@RequestBody DatosRegistroMedico parametro){
        //System.out.println("El request llega correctamete");
        System.out.println(parametro);
    }

}
