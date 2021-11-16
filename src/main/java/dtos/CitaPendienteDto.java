package dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CitaPendienteDto {

	private int ID;
	private String HORA_ENTRADA;
	private String HORA_SALIDA;
	private String NOMBRE_PACIENTE;
	private String NOMBRE_MEDICO;
	private String UBICACION;
	private String CONTACTO_MEDICO;
	private int IDPACIENTE;
	private int ID_MEDICO;
	private String FECHA;
	private String ESTADO;
}
