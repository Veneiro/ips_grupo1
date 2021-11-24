package dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcedimientoDto {

	private int id;
	private int cita_id;
	private String procedimiento;
	private String fecha;
	private String hora;
}
