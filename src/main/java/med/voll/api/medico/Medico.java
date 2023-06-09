package med.voll.api.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.direccion.Direccion;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;

    private Boolean activo;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;

    public Medico(DatosRegistroMedico parametros) {
        this.activo = true;
        this.nombre = parametros.nombre();
        this.email = parametros.email();
        this.telefono = parametros.telefono();
        this.documento = parametros.documento();
        this.especialidad = parametros.especialidad();
        this.direccion = new Direccion(parametros.direccion());
    }

    public void actualizarDatos(DatosActualizarMedico datosActualizarMedico) {
        if(datosActualizarMedico.nombre() != null){
            this.nombre = datosActualizarMedico.nombre();
        }
        if(datosActualizarMedico.documento() != null){
            this.documento = datosActualizarMedico.documento();
        }
        if(datosActualizarMedico.direccion() != null){
            this.direccion =direccion.actualizarDireccion(datosActualizarMedico.direccion());
        }

    }

    public void desactivarMedico() {
        this.activo = false;
    }
}
