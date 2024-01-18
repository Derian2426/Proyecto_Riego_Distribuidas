package com.example.practica_seguridad.repository;

import com.example.practica_seguridad.model.MonitoreoTemperatura;
import com.example.practica_seguridad.model.ZonaRiego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MonitoreoTemperaturaRepository extends JpaRepository<MonitoreoTemperatura, Integer> {
    Optional<List<MonitoreoTemperatura>> findByZonaRiego(ZonaRiego zonaRiego);

    List<MonitoreoTemperatura> findByZonaRiegoAndFechaMedicion(ZonaRiego zonaRiego, Date fechaMedicion);

    @Query(value = "SELECT DISTINCT( Cast(m.fechaMedicion as date)) FROM MonitoreoTemperatura m " +
            "WHERE m.zonaRiego = :zonaRiego" +
            " AND Upper(to_char(m.fechaMedicion, 'TMMonth'))=Upper(:mes)" +
            " ORDER BY Cast(m.fechaMedicion as date) asc")
    List<Date> findDistinctFechaMedicionByZonaRiego(@Param("mes") String mes, @Param("zonaRiego") ZonaRiego zonaRiego);

    @Query(value = "SELECT DISTINCT to_char(m.fechaMedicion, 'TMMonth')" +
            " FROM MonitoreoTemperatura m WHERE m.zonaRiego= :zonaRiego" +
            " AND EXTRACT(Year FROM m.fechaMedicion)= :fecha" +
            " ORDER BY to_char(m.fechaMedicion, 'TMMonth') DESC")
    List<String> findFechaMedicionByZonaRiego(@Param("fecha") String fecha, @Param("zonaRiego") ZonaRiego zonaRiego);

    @Query(value = "select DISTINCT(EXTRACT(YEAR FROM m.fechaMedicion)) from MonitoreoTemperatura m" +
            " WHERE m.zonaRiego= :zonaRiego" +
            " ORDER BY EXTRACT(YEAR FROM m.fechaMedicion)")
    List<Integer> findAnioMedicionByZonaRiego(@Param("zonaRiego") ZonaRiego zonaRiego);

    @Query(value = "SELECT EXTRACT(HOUR FROM m.fechaMedicion) ," +
            " :fecha , AVG(m.temperatura) , AVG(m.humedad) , m.zonaRiego.idZona" +
            " FROM MonitoreoTemperatura m" +
            " WHERE " +
            " CAST(m.fechaMedicion AS date) = :fecha " +
            " AND EXTRACT(HOUR FROM m.fechaMedicion) IN (8, 11, 14, 17, 20, 23, 2, 5)" +
            " AND m.zonaRiego = :zonaRiego " +
            " GROUP BY EXTRACT(HOUR FROM m.fechaMedicion), m.zonaRiego.idZona" +
            " ORDER BY EXTRACT(HOUR FROM m.fechaMedicion)")
    List<Object> obtenerDatosTemperaturaPorFechaYHora(@Param("fecha") Date fecha, @Param("zonaRiego") ZonaRiego zonaRiego);

    @Query(value = "SELECT CASE WHEN X.fecha = 1 THEN 2 " +
            "        WHEN X.fecha = 2 THEN 5 " +
            "        WHEN X.fecha = 3 THEN 8 " +
            " WHEN X.fecha = 4 THEN 11" +
            " WHEN X.fecha = 5 THEN 14" +
            " WHEN X.fecha = 6 THEN 17" +
            " WHEN X.fecha = 7 THEN 20" +
            " WHEN X.fecha = 8 THEN 23" +
            "    END, :fecha, AVG(X.temperatura),AVG(X.humedad) FROM (" +
            " SELECT CEIL(ROW_NUMBER() OVER (ORDER BY EXTRACT(HOUR FROM m.fecha_medicion))/ 3.0) fecha," +
            " EXTRACT(HOUR FROM m.fecha_medicion) as hora,avg(m.temperatura) temperatura, avg(m.humedad) humedad" +
            "    FROM monitoreo_temperatura m" +
            "    WHERE CAST(m.fecha_medicion AS DATE) = :fecha" +
            " AND id_zona= :idZona" +
            " Group by EXTRACT(HOUR FROM m.fecha_medicion))AS X" +
            " Group by X.fecha", nativeQuery = true)
    List<Object> obtenerDatosTemperaturaPorFechaYHoraPromedio(@Param("fecha") Date fecha, @Param("idZona")Integer zonaRiego);


    @Query(value = "SELECT to_char(m.fechaMedicion, 'TMMonth') ," +
            " :fecha , AVG(m.temperatura) , AVG(m.humedad) , m.zonaRiego.idZona" +
            " FROM MonitoreoTemperatura m" +
            " WHERE " +
            " EXTRACT(YEAR FROM m.fechaMedicion) = :fecha" +
            " AND m.zonaRiego = :zonaRiego " +
            " GROUP BY to_char(m.fechaMedicion, 'TMMonth'), m.zonaRiego.idZona" +
            " ORDER BY EXTRACT(MONTH FROM MIN(m.fechaMedicion)) Asc")
    List<Object> obtenerDatosTemperaturaPorAnio(@Param("fecha") String fecha, @Param("zonaRiego") ZonaRiego zonaRiego);


    @Query(value = "SELECT to_char(m.fechaMedicion, 'TMDay')," +
            " :mes , AVG(m.temperatura) , AVG(m.humedad) , m.zonaRiego.idZona" +
            " FROM MonitoreoTemperatura m" +
            " WHERE " +
            " UPPER(to_char(m.fechaMedicion, 'TMMonth')) = UPPER(:mes)" +
            " AND m.zonaRiego = :zonaRiego " +
            " GROUP BY to_char(m.fechaMedicion, 'TMDay'), m.zonaRiego.idZona" +
            " ORDER BY CASE" +
            " WHEN to_char(m.fechaMedicion, 'TMDay') = 'Lunes' THEN 1" +
            " WHEN to_char(m.fechaMedicion, 'TMDay') = 'Martes' THEN 2" +
            " WHEN to_char(m.fechaMedicion, 'TMDay') = 'Miércoles' THEN 3" +
            " WHEN to_char(m.fechaMedicion, 'TMDay') = 'Jueves' THEN 4" +
            " WHEN to_char(m.fechaMedicion, 'TMDay') = 'Viernes' THEN 5" +
            " WHEN to_char(m.fechaMedicion, 'TMDay') = 'Sábado' THEN 6" +
            " WHEN to_char(m.fechaMedicion, 'TMDay') = 'Domingo' THEN 7 END")
    List<Object> obtenerDatosTemperaturaPorMes(@Param("mes") String mes, @Param("zonaRiego") ZonaRiego zonaRiego);

    /*select CASE
        WHEN y.numero_serie = 1 THEN 2
        WHEN y.numero_serie = 2 THEN 5
        WHEN y.numero_serie = 3 THEN 8
		WHEN y.numero_serie = 4 THEN 11
		WHEN y.numero_serie = 5 THEN 14
		WHEN y.numero_serie = 6 THEN 17
		WHEN y.numero_serie = 7 THEN 20
		WHEN y.numero_serie = 8 THEN 23
    END AS nuevo_numero_serie,avg(y.promedio_temperatura),avg(y.promedio_temperatura) from (
SELECT CEIL(ROW_NUMBER() OVER (ORDER BY subquery.hora) / 3.0) AS numero_serie, subquery.hora as hora,
    AVG(temperatura) AS promedio_temperatura,
    AVG(humedad) AS promedio_humedad
FROM (
    SELECT EXTRACT(HOUR FROM m.fecha_medicion) as hora,m.temperatura, m.humedad
    FROM monitoreo_temperatura m
    WHERE CAST(m.fecha_medicion AS DATE) = '2023-11-17 01:15:00'
) AS subquery
GROUP BY
    hora
ORDER BY
    hora ) as Y
	GROUP BY y.numero_serie;*/
}

