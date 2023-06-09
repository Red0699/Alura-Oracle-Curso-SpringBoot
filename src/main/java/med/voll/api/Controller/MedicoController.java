package med.voll.api.Controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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
        //return medicoRepository.findAll(paginacion).map(DatosListadoMedicos::new);
        return medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedicos::new);
    }
    @PutMapping
    @Transactional
    public void actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico){
        Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id());
        medico.actualizarDatos(datosActualizarMedico);
    }
    @DeleteMapping("/{id}")
    @Transactional

    //Borrar registro de la base de datos
    /*
    public void eliminarMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        medicoRepository.delete(medico);
    }
    */
    //Deshabilitar registro actualizando el estado a false (0)
    public void eliminarMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
    }

}
