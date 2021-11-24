package dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CauseDto {
	private String OBSERVATIONS;
	private String DATE;
	private String HOUR;
	private String NAME;
	private int ID;
	
	public String toString() {
		String aux = "";
		aux += NAME;
		aux += " | " + DATE;
		aux += " | " + HOUR;
		return aux;
	}
}
