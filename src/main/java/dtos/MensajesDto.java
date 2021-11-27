package dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MensajesDto {

	private String ASUNTO;
	private String MENSAJE;
	private String REMITENTE;
	private String DESTINATARIO;
	private String ADJUNTO;
}
