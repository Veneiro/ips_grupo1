package records;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import dtos.JornadaLaboralDto;

public class RecordAssembler {

    public static JornadaLaboralDto toDto(JornadaLaboralRecord r) {
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
	DateFormat hourFormat = new SimpleDateFormat("HH:mm", Locale.US);

	JornadaLaboralDto j = new JornadaLaboralDto();
	j.setId_trabajador(r.getId_trabajador());
	try {
	    j.setDia_comienzo(dateFormat.parse(r.getDia_comienzo()));
	    j.setDia_fin(dateFormat.parse(r.getDia_fin()));
	    j.setHora_entrada(hourFormat.parse(r.getHora_entrada()));
	    j.setHora_salida(hourFormat.parse(r.getHora_salida()));
	} catch (ParseException e) {
	    e.printStackTrace();
	}
	j.setLunes(r.isLunes());
	j.setMartes(r.isMartes());
	j.setMiercoles(r.isMiercoles());
	j.setJueves(r.isJueves());
	j.setViernes(r.isViernes());
	j.setSabado(r.isSabado());
	j.setDomingo(r.isDomingo());
	return j;
    }

}
