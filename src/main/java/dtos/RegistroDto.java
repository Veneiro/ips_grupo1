package dtos;

import java.time.Instant;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistroDto {

    private String quien, que;
    private Date fecha;

    public RegistroDto(String quien, String que) {
	super();
	this.quien = quien;
	this.que = que;
	this.fecha = Date.from(Instant.now());
    }

}
