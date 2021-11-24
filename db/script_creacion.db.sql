BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "TMEDICOS" (
	"ID"	INTEGER NOT NULL UNIQUE,
	"NOMBRE"	TEXT,
	PRIMARY KEY("ID")
);
CREATE TABLE IF NOT EXISTS "TASIGNADOA" (
	"id_cita"	INTEGER,
	"id_medico"	INTEGER,
	FOREIGN KEY("id_medico") REFERENCES "TMEDICOS"("ID"),
	FOREIGN KEY("id_cita") REFERENCES "TCITAS"("id"),
	PRIMARY KEY("id_cita","id_medico")
);
CREATE TABLE "TCITAS" (
	"id"	NUMERIC,
	"horario_inicio"	TEXT,
	"horario_fin"	TEXT,
	"ubicacion"	TEXT,
	"contacto"	TEXT,
	"id_paciente"	INTEGER NOT NULL,
	"id_medico"	INTEGER,
	"acudio"	INTEGER,
	"fecha"	TEXT,
	FOREIGN KEY("id_paciente") REFERENCES "TPACIENTES"("ID"),
	PRIMARY KEY("id")
)
CREATE TABLE "TPACIENTES" (
	"ID"	INTEGER NOT NULL,
	"NOMBRE"	TEXT,
	"info_contacto"	TEXT,
	"apellido"	TEXT,
	PRIMARY KEY("ID")
)
CREATE TABLE IF NOT EXISTS "TENFERMEROS" (
	"ID"	INTEGER NOT NULL UNIQUE,
	"NOMBRE"	TEXT,
	PRIMARY KEY("ID" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "TJORNADALABORAL" (
	"ID"	INTEGER NOT NULL UNIQUE,
	"NOMBRE_TRABAJADOR"	TEXT NOT NULL,
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
	PRIMARY KEY("ID" AUTOINCREMENT)
);
CREATE TABLE "THISTORIALES" (
	"idPaciente"	INTEGER,
	"vacunas"	TEXT,
	"antecedentes"	TEXT,
	"informacionAdicional"	TEXT,
	PRIMARY KEY("idPaciente"),
	FOREIGN KEY("idPaciente") REFERENCES "TPACIENTES"("id")
)
INSERT INTO "TMEDICOS" VALUES (1,'María Fernández Lopez','');
INSERT INTO "TMEDICOS" VALUES (2,'Pedro Domínguez Mauregato','');
INSERT INTO "TASIGNADOA" VALUES (868206663,2);
INSERT INTO "TASIGNADOA" VALUES (868206663,1);
INSERT INTO "TASIGNADOA" VALUES (505965533,1);
INSERT INTO "TCITAS" VALUES (505965533,'05:22','08:22',NULL,NULL,1,1,0,'17-10-2021');
INSERT INTO "TCITAS" VALUES (868206663,NULL,NULL,'efeewgwe','wefw',1,2,0,"13-10-2021");
INSERT INTO "TPACIENTES" VALUES (1,'Santi',NULL);
INSERT INTO "TPACIENTES" VALUES (2,'Lucas','Telefono123');
INSERT INTO "TENFERMEROS" VALUES (1,'Pepito Pérez Mayor');
INSERT INTO "TJORNADALABORAL" VALUES (1,'María Fernández Lopez','2021-10-09','00:00','2021-10-08','16:00',1,1,1,1,1,0,0);
INSERT INTO "TJORNADALABORAL" VALUES (2,'María Fernández Lopez','2021-10-08','00:00','2021-10-08','16:00',1,1,1,1,1,0,0);
COMMIT;
