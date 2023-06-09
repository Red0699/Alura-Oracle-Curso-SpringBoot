package med.voll.api.Controller;

import jakarta.validation.Valid;
import med.voll.api.medico.DatosListadoMedicos;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import med.voll.api.medico.DatosRegistroMedico;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;
    @PostMapping
    public void registrarMedico(@RequestBody @Valid DatosRegistroMedico parametro){
        //System.out.println("El request llega correctamete");
        medicoRepository.save(new Medico(parametro));
    }
    @GetMapping
    public Page<DatosListadoMedicos> listadoMedicos(@PageableDefault(size = 1, sort = "nombre") Pageable paginacion){
        return medicoRepository.findAll(paginacion).map(DatosListadoMedicos::new);
    }

}
