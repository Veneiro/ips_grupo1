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

	private String nombre_trabajador;
	private Date dia_comienzo, dia_fin, hora_entrada, hora_salida;
	private boolean lunes, martes, miercoles, jueves, viernes, sabado, domingo;
}
