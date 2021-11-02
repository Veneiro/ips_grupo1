package records;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JornadaLaboralRecord {

    private String nombre_trabajador;
    private String dia_comienzo, dia_fin, hora_entrada, hora_salida;
    private boolean lunes, martes, miercoles, jueves, viernes, sabado, domingo;
}
