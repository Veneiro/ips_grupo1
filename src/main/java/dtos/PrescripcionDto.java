package dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrescripcionDto {

	private String indicaciones, medicamento, cantidad, intervalo, duracion;
	private Date fecha;
}
