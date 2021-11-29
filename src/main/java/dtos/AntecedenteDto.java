package dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AntecedenteDto {
	
	private int id;
	private int paciente_id;
	private String antecedente;
	private String fecha_comienzo;
	private String informacion;

}
