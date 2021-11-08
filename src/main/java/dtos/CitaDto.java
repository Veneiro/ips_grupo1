package dtos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Cita con el formato de la base de datos.
 * 
 * @author Santiago
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CitaDto {

	private int id;
	private String horario_inicio;
	private String horario_fin;
	private String ubicacion;
	private String contacto;
	private String fecha;
	private String informacion;
	private String acudio;
	private int id_paciente;
	private int id_medico;
	private String especialidad;
	private String nombre_paciente;
	private String hora_entrada;
	private String hora_salida;
}
