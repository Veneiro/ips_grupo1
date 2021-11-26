package records;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JornadaLaboralRecord {

    private int id;
    private int id_trabajador;
    private String dia_comienzo, dia_fin, hora_entrada, hora_salida;
    private boolean lunes, martes, miercoles, jueves, viernes, sabado, domingo;

    @Override
    public int hashCode() {
	return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	JornadaLaboralRecord other = (JornadaLaboralRecord) obj;
	return id == other.id;
    }

}
