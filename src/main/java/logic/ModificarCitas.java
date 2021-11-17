package logic;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dtos.CitaDto;
import dtos.JornadaLaboralDto;
import dtos.MedicoDto;
import modelo.CitaModelo;
import modelo.JornadaModelo;
import modelo.LectorDeDatos;
import modelo.MedicoAsignadoACitaModelo;
import records.JornadaLaboralRecord;
import records.RecordAssembler;
import util.ApplicationException;
import util.Util;

public class ModificarCitas {

    private CitaDto cita;
    private List<MedicoDto> medicosExistentes;
    private List<MedicoDto> medicosA�adibles;
    private List<MedicoDto> medicosElegidos;

    private MedicoAsignadoACitaModelo modeloMedicoCita = new MedicoAsignadoACitaModelo();

    public ModificarCitas(CitaDto cita) {
	medicosElegidos = new ArrayList<MedicoDto>();
	setCita(cita);
	setMedicosExistentes(modeloMedicoCita.getMedicosDeCita(cita.getId()));
	setMedicosA�adibles(modeloMedicoCita.getAllMedicosExceptoDeCita(cita.getId()));
    }

    public void seleccionarMedicos(List<MedicoDto> listaMedicos) {
	for (MedicoDto newMedico : listaMedicos) {
	    if (!medicosElegidos.contains(newMedico))
		medicosElegidos.add(newMedico);
	}
    }

    public void deseleccionarMedicos(List<MedicoDto> listaMedicos) {
	for (MedicoDto medicoABorrar : listaMedicos) {
	    medicosElegidos.remove(medicoABorrar);
	}
    }

    public String getStringMedicosElegidos() {
	String txt = "";
	for (MedicoDto ms : medicosElegidos) {
	    txt += ms.toString() + "\n";
	}
	return txt;
    }

    public boolean hayMedicosElegidos() {
	return !medicosElegidos.isEmpty();
    }

    public CitaDto getCita() {
	return cita;
    }

    public List<MedicoDto> getMedicosExistentes() {
	return new ArrayList<MedicoDto>(medicosExistentes);
    }

    public List<MedicoDto> getMedicosA�adibles() {
	return new ArrayList<MedicoDto>(medicosA�adibles);
    }

    /**
     * Devuelve una lista que contiene s�lo los m�dicos cuya representaci�n en
     * String contenga el filtro pasado.
     * 
     * @param filtro
     * @return lista filtrada de los m�dicos
     */
    public List<MedicoDto> filtrarMedicos(String filtro) {
	List<MedicoDto> listaFiltrada = new ArrayList<MedicoDto>();
	for (MedicoDto m : medicosA�adibles) {
	    if (m.toString().trim().toLowerCase().contains(filtro.toLowerCase())) {
		listaFiltrada.add(m);
	    }
	}
	return listaFiltrada;
    }

    public List<MedicoDto> filtrarMedicosPorEspecialidad(String especialidad) {
	List<MedicoDto> listaFiltrada = new ArrayList<MedicoDto>();
	for (MedicoDto m : medicosA�adibles) {
	    if (m.getEspecialidad() != null)
		if (m.getEspecialidad().equals(especialidad)) {
		    listaFiltrada.add(m);
		}
	}
	return listaFiltrada;
    }

    public void actualizarCita(String infoContacto, String ubicacion, Date horaInicio, Date horaFin, Date fecha) {
	MedicoAsignadoACitaModelo medicoCitaModelo = new MedicoAsignadoACitaModelo();

	for (MedicoDto medicoDto : medicosElegidos) {
	    medicoCitaModelo.add(cita.getId(), medicoDto.getId());
	}

	if (horaInicio == null || horaFin == null) {
	    cita.setHorario_inicio(null);
	    cita.setHorario_fin(null);
	} else {
	    cita.setHorario_inicio(Util.dateToIsoHour(horaInicio));
	    cita.setHorario_fin(Util.dateToIsoHour(horaFin));
	}
	cita.setUbicacion(ubicacion);
	cita.setContacto(infoContacto);
	if (fecha != null)
	    cita.setFecha(new SimpleDateFormat("dd-MM-yyyy").format(fecha));
	else
	    cita.setFecha(null);

	new CitaModelo().updateCita(cita);
    }

    /**
     * Dado un inico y fin de un horario, comprueba si alg�n m�dico de los elegidos
     * tiene alguna cita con un horario igual.
     * 
     * @param horaEntrada
     * @param horaSalida
     * @return true si hay colisi�n con alguna cita de los m�dicos, false en caso
     *         contrario.
     */
    public boolean hayColisionMismoHorario(Date horaEntrada, Date horaSalida, Date fecha) {
	List<CitaDto> citasDto = new LectorDeDatos().getListaCitasDeMedicos(medicosElegidos);
	Format formatterHora = new SimpleDateFormat("HH:mm");
	Format formatterDia = new SimpleDateFormat("dd-MM-yyyy");
	try {
	    fecha = (Date) formatterDia.parseObject(formatterDia.format(fecha));
	} catch (ParseException e1) {
	    throw new ApplicationException(e1);
	}
	for (CitaDto citaDto : citasDto) {
	    try {
		if (citaDto.getFecha() != null) {
		    Date fechaAjena = (Date) formatterDia.parseObject(citaDto.getFecha());
		    if (fecha.compareTo(fechaAjena) == 0) {
			Date horaEntradaAjena = (Date) formatterHora.parseObject(citaDto.getHorario_inicio());
			Date horaSalidaAjena = (Date) formatterHora.parseObject(citaDto.getHorario_fin());
			if (colisionHorarios(horaEntrada, horaSalida, horaEntradaAjena, horaSalidaAjena)) {
			    return true;
			}
		    }
		}
	    } catch (ParseException e) {
		// Se ignora la cita
	    }
	}
	return false;
    }

    public boolean fueraDeJornadaLaboral(Date horaEntrada, Date horaSalida, Date fecha) {
	Format formatterDia = new SimpleDateFormat("yyyy-MM-dd");
	try {
	    fecha = (Date) formatterDia.parseObject(formatterDia.format(fecha));
	} catch (ParseException e1) {
	    throw new ApplicationException(e1);
	}
	JornadaModelo modelo = new JornadaModelo();
	for (MedicoDto medicoDto : medicosElegidos) {
		List<JornadaLaboralRecord> jornadas = modelo.findByName(medicoDto.getNombre());
	    for (JornadaLaboralRecord jornada : jornadas) {
	    	JornadaLaboralDto j = RecordAssembler.toDto(jornada);
		if (j.getDia_comienzo() != null && j.getDia_fin() != null) {
		    if (fecha.before(j.getDia_comienzo()) || fecha.after(j.getDia_fin())) {
			return true;
		    }
		    try {
			if (colisionHorarios(horaEntrada, horaSalida, j.getHora_entrada(),
				j.getHora_salida())) {
			    return true;
			}
		    } catch (ParseException e) {
			// Se ignora la jornada
		    }
		}
	    }
	}
	return false;
    }

    private void setCita(CitaDto cita) {
	if (cita == null)
	    throw new ApplicationException("La cita no puede ser null");
	this.cita = cita;
    }

    private void setMedicosExistentes(List<MedicoDto> medicosExistentes) {
	this.medicosExistentes = medicosExistentes;
    }

    private void setMedicosA�adibles(List<MedicoDto> medicosA�adibles) {
	this.medicosA�adibles = medicosA�adibles;
    }

    /**
     * Comprueba si hay intersecci�n entre 2 horarios.
     * 
     * @param i1 hora de inicio del horario 1
     * @param f1 hora de fin del horario 1
     * @param i2 hora de inicio del horario 2
     * @param f2 hora de fin del horario 2
     * @return true si tienen tiempo en com�n, false en caso contrario
     * @throws ParseException
     */
    private boolean colisionHorarios(Date i1, Date f1, Date i2, Date f2) throws ParseException {
	Format formatter = new SimpleDateFormat("HH:mm");
	i1 = (Date) formatter.parseObject(formatter.format(i1));
	f1 = (Date) formatter.parseObject(formatter.format(f1));
	i2 = (Date) formatter.parseObject(formatter.format(i2));
	f2 = (Date) formatter.parseObject(formatter.format(f2));
	if (i1.compareTo(i2) == 0 || f1.compareTo(f2) == 0)
	    return true;
	if (i1.after(i2) && i1.before(f2))
	    return true;
	if (f1.after(i2) && f1.before(f2))
	    return true;
	if (i2.after(i1) && i2.before(f1))
	    return true;
	if (f2.after(i1) && f2.before(f1))
	    return true;
	return false;
    }
    
    public List<MedicoDto> filtrarMedicosSinCitasColisionantes(Date horaEntrada, Date horaSalida, Date fecha) {
		List<MedicoDto> res = new ArrayList<MedicoDto>();
		Format formatterHora = new SimpleDateFormat("HH:mm");
		Format formatterDia = new SimpleDateFormat("dd-MM-yyyy");
		try {
			fecha = (Date) formatterDia.parseObject(formatterDia.format(fecha));
		} catch (ParseException e1) {
			throw new ApplicationException(e1);
		}
		for (MedicoDto medico : medicosA�adibles) {
			List<CitaDto> citasDto = new LectorDeDatos().getListaCitasDeMedico(medico);
			if (!algunaCitaColisionante(horaEntrada, horaSalida, fecha, formatterHora, formatterDia, citasDto)) {
				res.add(medico);
			}
		}
		return res;
	}

	private boolean algunaCitaColisionante(Date horaEntrada, Date horaSalida, Date fecha, Format formatterHora, Format formatterDia,
			List<CitaDto> citasDto) {
		for (CitaDto citaDto : citasDto) {
			try {
				if (citaDto.getFecha() != null) {
					Date fechaAjena = (Date) formatterDia.parseObject(citaDto.getFecha());
					if (fecha.compareTo(fechaAjena) == 0) {
						Date horaEntradaAjena = (Date) formatterHora.parseObject(citaDto.getHorario_inicio());
						Date horaSalidaAjena = (Date) formatterHora.parseObject(citaDto.getHorario_fin());
						if (colisionHorarios(horaEntrada, horaSalida, horaEntradaAjena, horaSalidaAjena)) {
							return true;
						}
					}
				}
			} catch (ParseException e) {
				// Se ignora la cita
			}
		}
		return false;
	}
}
