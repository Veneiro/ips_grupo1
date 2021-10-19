package dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistorialDto {
	
	private int idPaciente;
	private String vacunas;
	private String antecedentes;
	private String informacionAdicional;

}
