package dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Santiago
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PacienteDto {

	private int id;
	private String nombre;
	private String apellido;
	private String info_contacto;
	
	@Override
	public String toString() {
		return getNombre();
	}
}
