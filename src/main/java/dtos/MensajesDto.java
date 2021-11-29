package dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MensajesDto {

	private String ASUNTO;
	private String MENSAJE;
	private int REMITENTE;
	private int DESTINATARIO;
	private String ADJUNTO;
	private String HORA_DE_ENVIO;
}
