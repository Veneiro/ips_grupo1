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

    private int id, paciente_id;
    private boolean medicamento;
    private String nombre, indicaciones, cantidad, intervalo, duracion;
    private Date fecha;
}
