package dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosticoDto {

	private int id;
	private int id_paciente;
	private String diagnostico;
	private String fecha;
	private int id_medico;
}
