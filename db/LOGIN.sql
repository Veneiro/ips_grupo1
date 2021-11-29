BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "TANTECEDENTES" (
	"id"	INTEGER,
	"paciente_id"	INTEGER,
	"antecedente"	TEXT,
	"fecha_comienzo"	TEXT,
	"informacion"	TEXT,
	FOREIGN KEY("paciente_id") REFERENCES "THISTORIALES"("idPaciente"),
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "TASIGNADOA" (
	"ID_CITA"	INTEGER,
	"ID_MEDICO"	INTEGER,
	FOREIGN KEY("ID_CITA") REFERENCES "TCITAS"("ID"),
	FOREIGN KEY("ID_MEDICO") REFERENCES "TMEDICOS"("ID"),
	PRIMARY KEY("ID_CITA","ID_MEDICO")
);
CREATE TABLE IF NOT EXISTS "TASIGNADOAPENDIENTE" (
	"ID_CITA"	INTEGER,
	"ID_MEDICO"	INTEGER,
	FOREIGN KEY("ID_CITA") REFERENCES "TCITASPENDIENTES"("ID"),
	FOREIGN KEY("ID_MEDICO") REFERENCES "TMEDICOS"("ID"),
	PRIMARY KEY("ID_CITA","ID_MEDICO")
);
CREATE TABLE IF NOT EXISTS "TCAUSA" (
	"OBSERVATIONS"	TEXT,
	"DATE"	TEXT NOT NULL,
	"HOUR"	TEXT NOT NULL,
	"NAME"	TEXT NOT NULL,
	"ID"	INTEGER NOT NULL,
	FOREIGN KEY("ID") REFERENCES "TCITAS"("ID")
);
CREATE TABLE IF NOT EXISTS "TCITAS" (
	"ID"	NUMERIC,
	"HORARIO_INICIO"	TEXT,
	"HORARIO_FIN"	TEXT,
	"UBICACION"	TEXT,
	"ID_PACIENTE"	INTEGER NOT NULL,
	"ID_MEDICO"	BLOB,
	"ACUDIO"	TEXT,
	"FECHA"	TEXT,
	"INFORMACION"	TEXT,
	"ESPECIALIDAD"	TEXT,
	"HORA_ENTRADA"	TEXT,
	"HORA_SALIDA"	TEXT,
	FOREIGN KEY("ID_PACIENTE") REFERENCES "TPACIENTES"("ID"),
	FOREIGN KEY("ID_MEDICO") REFERENCES "TMEDICOS"("ID"),
	PRIMARY KEY("ID")
);
CREATE TABLE IF NOT EXISTS "TCITASPENDIENTES" (
	"ID"	INTEGER,
	"HORA_ENTRADA"	TEXT,
	"HORA_SALIDA"	TEXT,
	"UBICACION"	TEXT,
	"ID_PACIENTE"	INTEGER,
	"ID_MEDICO"	INTEGER,
	"FECHA"	TEXT,
	FOREIGN KEY("ID_PACIENTE") REFERENCES "TPACIENTES"("ID"),
	FOREIGN KEY("ID_MEDICO") REFERENCES "TMEDICOS"("ID")
);
CREATE TABLE IF NOT EXISTS "TDIAGNOSTICOS" (
	"id"	INTEGER,
	"id_paciente"	INTEGER,
	"diagnostico"	TEXT,
	"fecha"	TEXT,
	"id_medico"	INTEGER,
	"hora_creacion"	TEXT,
	"actual"	INTEGER,
	FOREIGN KEY("id_medico") REFERENCES "TMEDICOS"("ID"),
	FOREIGN KEY("id_paciente") REFERENCES "THISTORIALES"("idPaciente"),
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "TENFERMEROS" (
	"ID"	INTEGER NOT NULL UNIQUE,
	"NOMBRE"	TEXT,
	PRIMARY KEY("ID" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "THISTORIALES" (
	"IDPACIENTE"	INTEGER,
	"INFORMACIONADICIONAL"	TEXT,
	FOREIGN KEY("IDPACIENTE") REFERENCES "TPACIENTES"("ID"),
	PRIMARY KEY("IDPACIENTE")
);
CREATE TABLE IF NOT EXISTS "TJORNADALABORAL" (
	"ID"	INTEGER NOT NULL UNIQUE,
	"ID_TRABAJADOR"	INTEGER NOT NULL,
	"DIA_COMIENZO"	TEXT NOT NULL,
	"HORA_ENTRADA"	TEXT NOT NULL,
	"DIA_FIN"	TEXT NOT NULL,
	"HORA_SALIDA"	TEXT NOT NULL,
	"LUNES"	INTEGER DEFAULT 0,
	"MARTES"	INTEGER DEFAULT 0,
	"MIERCOLES"	INTEGER DEFAULT 0,
	"JUEVES"	INTEGER DEFAULT 0,
	"VIERNES"	INTEGER DEFAULT 0,
	"SABADO"	INTEGER DEFAULT 0,
	"DOMINGO"	INTEGER DEFAULT 0,
	FOREIGN KEY("ID_TRABAJADOR") REFERENCES "TTRABAJADORES"("ID"),
	PRIMARY KEY("ID" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "TMEDICOS" (
	"ID"	INTEGER NOT NULL UNIQUE,
	"NOMBRE"	TEXT,
	"ESPECIALIDAD"	TEXT,
	"EMAIL"	TEXT,
	PRIMARY KEY("ID")
);
CREATE TABLE IF NOT EXISTS "TPACIENTES" (
	"ID"	INTEGER NOT NULL,
	"NOMBRE"	TEXT,
	"INFO_CONTACTO"	TEXT,
	"APELLIDO"	TEXT,
	PRIMARY KEY("ID")
);
CREATE TABLE IF NOT EXISTS "TPRESCRIPCIONES" (
	"ID"	INTEGER NOT NULL UNIQUE,
	"PACIENTE_ID"	INTEGER,
	"NOMBRE"	TEXT NOT NULL,
	"INDICACIONES"	TEXT NOT NULL,
	"MEDICAMENTO"	INTEGER,
	"CANTIDAD"	TEXT,
	"INTERVALO"	TEXT,
	"DURACION"	TEXT,
	"FECHA"	TEXT,
	"HORA"	TEXT,
	FOREIGN KEY("PACIENTE_ID") REFERENCES "TPACIENTES"("ID"),
	PRIMARY KEY("ID" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "TPROCEDIMIENTOS" (
	"id"	INTEGER,
	"cita_id"	INTEGER,
	"procedimiento"	TEXT,
	"fecha"	TEXT,
	"hora"	TEXT,
	FOREIGN KEY("cita_id") REFERENCES "TCITAS"("id"),
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "TREGISTRO" (
	"ID"	INTEGER NOT NULL UNIQUE,
	"QUIEN"	TEXT,
	"QUE"	TEXT,
	"FECHA"	TEXT,
	"HORA"	TEXT,
	PRIMARY KEY("ID" AUTOINCREMENT),
	FOREIGN KEY("QUIEN") REFERENCES "TUSERS"("USER")
);
CREATE TABLE IF NOT EXISTS "TVACUNAS" (
	"ID"	INTEGER,
	"PACIENTE_ID"	INTEGER,
	"VACUNA"	TEXT,
	"FECHA"	TEXT,
	"HORA"	TEXT,
	PRIMARY KEY("ID"),
	FOREIGN KEY("PACIENTE_ID") REFERENCES "TPACIENTES"("ID")
);
CREATE TABLE IF NOT EXISTS "TTRABAJADORES" (
	"ID"	INTEGER NOT NULL UNIQUE,
	"CATEGORIA"	TEXT NOT NULL,
	"ID_MEDICO"	INTEGER,
	"ID_ENFERMERO"	INTEGER,
	PRIMARY KEY("ID" AUTOINCREMENT),
	FOREIGN KEY("ID_ENFERMERO") REFERENCES "TENFERMEROS"("ID"),
	FOREIGN KEY("ID_MEDICO") REFERENCES "TMEDICOS"("ID")
);
CREATE TABLE IF NOT EXISTS "TUSERS" (
	"ID"	INTEGER NOT NULL UNIQUE,
	"USER"	TEXT NOT NULL UNIQUE,
	"PASSWORD"	TEXT,
	"CATEGORY"	TEXT NOT NULL,
	"ID_ASOCIADO"	INTEGER NOT NULL,
	"EMAIL"	TEXT NOT NULL UNIQUE,
	"EXPIRATION"	BLOB,
	PRIMARY KEY("ID" AUTOINCREMENT)
);

CREATE TABLE IF NOT EXISTS "TMENSAJES" (
	"ASUNTO"	TEXT,
	"MENSAJE"	TEXT,
	"REMITENTE"	INTEGER,
	"DESTINATARIO"	INTEGER,
	"ADJUNTO"	TEXT, 
	"HORA_DE_ENVIO"	TEXT
	);
INSERT INTO "TENFERMEROS" ("ID","NOMBRE") VALUES (1,'Pepito Pérez Mayor');
INSERT INTO "TENFERMEROS" ("ID","NOMBRE") VALUES (2,'Jesé Gracía Fernández');
INSERT INTO "TENFERMEROS" ("ID","NOMBRE") VALUES (3,'Eric García Busquets');
INSERT INTO "TENFERMEROS" ("ID","NOMBRE") VALUES (4,'Nacho Fernández Monreal');

INSERT INTO "TMEDICOS" ("ID","NOMBRE","ESPECIALIDAD","EMAIL") VALUES (1,'María Fernández Lopez','Traumatología','ihospital4738@gmail.com');
INSERT INTO "TMEDICOS" ("ID","NOMBRE","ESPECIALIDAD","EMAIL") VALUES (2,'Pedro Domínguez Mauregato','Oncología','ihospital4738@gmail.com');
INSERT INTO "TMEDICOS" ("ID","NOMBRE","ESPECIALIDAD","EMAIL") VALUES (3,'Rodrigo Álvarez García','Alergología','ihospital4738@gmail.com');

INSERT INTO "TUSERS" ("ID","USER","PASSWORD","CATEGORY","ID_ASOCIADO","EMAIL","EXPIRATION") VALUES (1,'med_1','OY~xn^','MEDICO',1,'UO250825@uniovi.es','2021-11-27 13:03');
INSERT INTO "TUSERS" ("ID","USER","PASSWORD","CATEGORY","ID_ASOCIADO","EMAIL","EXPIRATION") VALUES (2,'med_2',';<=','MEDICO',2,'papapapa@uniovi.es',NULL);
INSERT INTO "TUSERS" ("ID","USER","PASSWORD","CATEGORY","ID_ASOCIADO","EMAIL","EXPIRATION") VALUES (3,'med_3',';<=','MEDICO',3,'pepepepe@uniovi.es',NULL);
INSERT INTO "TUSERS" ("ID","USER","PASSWORD","CATEGORY","ID_ASOCIADO","EMAIL","EXPIRATION") VALUES (4,'admin_1',';<=','ADMINISTRATIVO',1,'a',NULL);
INSERT INTO "TUSERS" ("ID","USER","PASSWORD","CATEGORY","ID_ASOCIADO","EMAIL","EXPIRATION") VALUES (5,'ger_1',';<=','GERENTE',1,'b',NULL);

INSERT INTO "TPACIENTES" ("ID","NOMBRE","INFO_CONTACTO","APELLIDO") VALUES (1,'Santi','654576323','Gómez de la Serna');
INSERT INTO "TPACIENTES" ("ID","NOMBRE","INFO_CONTACTO","APELLIDO") VALUES (2,'Lucas','621341275','Pérez de la Sarna');
INSERT INTO "TPACIENTES" ("ID","NOMBRE","INFO_CONTACTO","APELLIDO") VALUES (3,'Moisés','674837463','Manín Lolín');

INSERT INTO "TTRABAJADORES" ("ID","CATEGORIA","ID_MEDICO","ID_ENFERMERO") VALUES (1,'MEDICO',1,NULL);
INSERT INTO "TTRABAJADORES" ("ID","CATEGORIA","ID_MEDICO","ID_ENFERMERO") VALUES (2,'MEDICO',2,NULL);
INSERT INTO "TTRABAJADORES" ("ID","CATEGORIA","ID_MEDICO","ID_ENFERMERO") VALUES (3,'MEDICO',3,NULL);
INSERT INTO "TTRABAJADORES" ("ID","CATEGORIA","ID_MEDICO","ID_ENFERMERO") VALUES (4,'ENFERMERO',NULL,1);
INSERT INTO "TTRABAJADORES" ("ID","CATEGORIA","ID_MEDICO","ID_ENFERMERO") VALUES (5,'ENFERMERO',NULL,2);
INSERT INTO "TTRABAJADORES" ("ID","CATEGORIA","ID_MEDICO","ID_ENFERMERO") VALUES (6,'ENFERMERO',NULL,3);
INSERT INTO "TTRABAJADORES" ("ID","CATEGORIA","ID_MEDICO","ID_ENFERMERO") VALUES (7,'ENFERMERO',NULL,4);
	

INSERT INTO "THISTORIALES" ("IDPACIENTE","INFORMACIONADICIONAL") VALUES (1,'Problemas de espalda');
INSERT INTO "THISTORIALES" ("IDPACIENTE","INFORMACIONADICIONAL") VALUES (2,'Pierna rota');
INSERT INTO "THISTORIALES" ("IDPACIENTE","INFORMACIONADICIONAL") VALUES (3,'Adicto a la Coca Cola');
INSERT INTO "TANTECEDENTES" ("id","paciente_id","antecedente","fecha_comienzo","informacion") VALUES (1,1,'Asma-ERROR','26-11-2021',NULL);
INSERT INTO "TANTECEDENTES" ("id","paciente_id","antecedente","fecha_comienzo","informacion") VALUES (2,1,'Alergia','26-11-2021',NULL);
INSERT INTO "TCITAS" ("ID","HORARIO_INICIO","HORARIO_FIN","UBICACION","ID_PACIENTE","ID_MEDICO","ACUDIO","FECHA","INFORMACION","ESPECIALIDAD","HORA_ENTRADA","HORA_SALIDA") VALUES (1028474370,'12:28','13:28',NULL,1,2,'Acudio','26-11-2021',NULL,NULL,NULL,NULL);
INSERT INTO "TASIGNADOA" ("ID_CITA","ID_MEDICO") VALUES (1028474370,2);
INSERT INTO "TDIAGNOSTICOS" ("id","id_paciente","diagnostico","fecha","id_medico","hora_creacion","actual") VALUES (1,1,'Gripe','26-11-2021',2,'12:38','Sí');
INSERT INTO "TJORNADALABORAL" ("ID","ID_TRABAJADOR","DIA_COMIENZO","HORA_ENTRADA","DIA_FIN","HORA_SALIDA","LUNES","MARTES","MIERCOLES","JUEVES","VIERNES","SABADO","DOMINGO") VALUES (16,2,'2021-11-26','16:30','2021-11-28','23:30',1,1,1,1,1,0,0);
INSERT INTO "TJORNADALABORAL" ("ID","ID_TRABAJADOR","DIA_COMIENZO","HORA_ENTRADA","DIA_FIN","HORA_SALIDA","LUNES","MARTES","MIERCOLES","JUEVES","VIERNES","SABADO","DOMINGO") VALUES (17,6,'2021-11-26','17:00','2021-11-27','17:00',1,1,1,1,1,0,0);
INSERT INTO "TPRESCRIPCIONES" ("ID","PACIENTE_ID","NOMBRE","INDICACIONES","MEDICAMENTO","CANTIDAD","INTERVALO","DURACION","FECHA","HORA") VALUES (9,1,'Dalsy','Mantener fuera del alcance de los niños.',1,'10 mg.','12 horas','1 semana','2021-11-07','13:57');
INSERT INTO "TPRESCRIPCIONES" ("ID","PACIENTE_ID","NOMBRE","INDICACIONES","MEDICAMENTO","CANTIDAD","INTERVALO","DURACION","FECHA","HORA") VALUES (11,1,'Aspirina','Si despúes de la primera semana continúa el dolor seguir otra semana más.',1,'20 mg.','24 h.','1 semana','2021-11-08','20:20');
INSERT INTO "TPROCEDIMIENTOS" ("id","cita_id","procedimiento","fecha","hora") VALUES (1,1028474370,'Radiografía-ERROR','26-11-2021','12:39');
INSERT INTO "TPROCEDIMIENTOS" ("id","cita_id","procedimiento","fecha","hora") VALUES (2,1028474370,'Radiografía','26-11-2021','12:40');
INSERT INTO "TVACUNAS" ("ID","PACIENTE_ID","VACUNA","FECHA","HORA") VALUES (1,1,'Sarampión','26-11-2021','12:39');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (1,'med_1','Consulta calendario citas','2021-11-26','12:27');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (2,'admin_1','Consulta registro','2021-11-26','12:29');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (3,'admin_1','Consulta registro','2021-11-26','12:29');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (4,'med_1','Consulta calendario citas','2021-11-26','12:29');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (5,'med_1','Consulta calendario citas','2021-11-26','12:29');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (6,'med_1','Consulta calendario citas','2021-11-26','12:29');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (7,'med_1','Consulta calendario citas','2021-11-26','12:29');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (8,'med_1','Consulta calendario citas','2021-11-26','12:33');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (9,'admin_1','Consulta registro','2021-11-26','12:33');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (10,'med_2','Consulta calendario citas','2021-11-26','12:36');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (11,'med_2','Ha informado de una EDO: null','2021-11-26','12:36');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (12,'med_2','Consulta historial de paciente 1','2021-11-26','12:37');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (13,'med_2','Indica que el paciente 1 Acudio','2021-11-26','12:40');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (14,'med_2','Indica que el paciente 1 Acudio','2021-11-26','12:40');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (15,'ger_1','Consulta estadísticas','2021-11-26','12:40');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (16,'ger_1','Consulta estadísticas','2021-11-26','12:44');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (17,'med_1','Consulta calendario citas','2021-11-26','12:45');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (18,'med_2','Consulta calendario citas','2021-11-26','13:03');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (19,'med_2','Consulta calendario citas','2021-11-26','13:04');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (20,'med_2','Ha informado de una EDO. Nombre: null. Observaciones: Sin definir','2021-11-26','13:05');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (21,'med_2','Consulta calendario citas','2021-11-26','13:14');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (22,'med_2','Ha informado de una EDO. Nombre: Sin definir. Observaciones: Tiene mala pinta','2021-11-26','13:14');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (23,'med_2','Consulta calendario citas','2021-11-26','13:28');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (24,'med_2','Consulta calendario citas','2021-11-26','13:53');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (25,'med_2','Consulta calendario citas','2021-11-26','14:11');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (26,'admin_1','Asigna jornada','2021-11-26','14:12');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (27,'admin_1','Asigna jornada','2021-11-26','15:21');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (28,'admin_1','Asigna jornada','2021-11-26','15:43');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (29,'admin_1','Asigna jornada','2021-11-26','16:13');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (30,'admin_1','Asigna jornada','2021-11-26','16:13');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (31,'admin_1','Asigna jornada','2021-11-26','16:13');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (32,'admin_1','Asigna jornada','2021-11-26','16:15');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (33,'admin_1','Asigna jornada','2021-11-26','16:17');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (34,'admin_1','Asigna jornada','2021-11-26','16:17');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (35,'admin_1','Asigna jornada','2021-11-26','16:20');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (36,'admin_1','Asigna jornada','2021-11-26','16:21');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (37,'admin_1','Asigna jornada','2021-11-26','16:21');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (38,'admin_1','Asigna jornada','2021-11-26','16:21');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (39,'admin_1','Asigna jornada','2021-11-26','16:21');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (40,'admin_1','Asigna jornada','2021-11-26','16:22');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (41,'admin_1','Asigna jornada','2021-11-26','16:22');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (42,'admin_1','Asigna jornada','2021-11-26','16:22');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (43,'admin_1','Consulta lista de jornadas','2021-11-26','16:27');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (44,'admin_1','Asigna jornada','2021-11-26','16:31');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (45,'admin_1','Consulta lista de jornadas','2021-11-26','16:31');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (46,'admin_1','Consulta lista de jornadas','2021-11-26','16:46');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (47,'admin_1','Actualiza jornada16','2021-11-26','16:47');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (48,'admin_1','Consulta lista de jornadas','2021-11-26','16:55');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (49,'admin_1','Consulta lista de jornadas','2021-11-26','16:57');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (50,'admin_1','Consulta lista de jornadas','2021-11-26','16:59');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (51,'admin_1','Consulta lista de jornadas','2021-11-26','17:00');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (52,'admin_1','Asigna jornada','2021-11-26','17:00');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (53,'admin_1','Consulta lista de jornadas','2021-11-26','17:01');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (54,'admin_1','Consulta lista de jornadas','2021-11-26','17:02');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (55,'admin_1','Consulta lista de jornadas','2021-11-26','17:20');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (56,'admin_1','Consulta lista de jornadas','2021-11-26','17:21');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (57,'admin_1','Consulta lista de jornadas','2021-11-26','18:07');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (58,'admin_1','Consulta lista de jornadas','2021-11-26','18:08');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (59,'admin_1','Consulta lista de jornadas','2021-11-26','18:14');
INSERT INTO "TREGISTRO" ("ID","QUIEN","QUE","FECHA","HORA") VALUES (60,'admin_1','Consulta lista de jornadas','2021-11-26','18:19');
COMMIT;
