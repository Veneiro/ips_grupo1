package dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VacunaDto {

	private int id;
	private int id_paciente;
	private String vacuna;
	private String fecha;
	private String hora;
}
