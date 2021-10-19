package dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JornadaLaboralDto {

	private String nombreEmpleado;
	private Date diaComienzo, diaFin, horaEntrada, horaSalida;
	private boolean lunes, martes, miercoles, jueves, viernes, sabado, domingo;
}
