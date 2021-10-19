package dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnfermeroDto {

	private int id;
	private String nombre;
	
	@Override
	public String toString() {
		return nombre;
	}
}
