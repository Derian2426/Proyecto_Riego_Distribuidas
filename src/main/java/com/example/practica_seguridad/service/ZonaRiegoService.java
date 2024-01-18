package com.example.practica_seguridad.service;

import com.example.practica_seguridad.model.*;
import com.example.practica_seguridad.interfaces.IZonaRiegoService;
import com.example.practica_seguridad.repository.DetalleSensorRepository;
import com.example.practica_seguridad.repository.SensorRepository;
import com.example.practica_seguridad.repository.ZonaRiegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ZonaRiegoService implements IZonaRiegoService {
    @Autowired
    private ZonaRiegoRepository zonaRiegoRepository;
    @Autowired
    private DetalleSensorRepository detalleSensorRepository;
    @Autowired
    private SensorRepository sensorRepository;

    @Override
    @Transactional
    public ZonaRiego create(DatosSensoresZonas zonaRiego) {
        ZonaRiego zonaRiegoRegistro;
        List<ZonaRiego> zonaRiegoList = zonaRiegoRepository.findByDireccionMAC(zonaRiego.getZonas().getDireccionMAC());
        try {
            if (zonaRiegoRepository.findByNombreZona(zonaRiego.getZonas().getNombreZona()) != null) {
                return new ZonaRiego(-1L, "");
            }
            if(zonaRiegoList.size()>0){
                for (ZonaRiego zona : zonaRiegoList) {
                    zona.setEstado(false);
                }
                zonaRiegoRepository.saveAll(zonaRiegoList);
            }
            zonaRiego.getZonas().setEstado(true);
            zonaRiegoRegistro = zonaRiegoRepository.save(zonaRiego.getZonas());
            for (DetalleSensor sensor : zonaRiego.getSensores()) {
                sensor.setZona(zonaRiegoRegistro);
            }
            detalleSensorRepository.saveAll(zonaRiego.getSensores());
            return zonaRiegoRegistro;
        } catch (Exception e) {
            return new ZonaRiego(-1L, e.getMessage());
        }
    }

    @Override
    @Transactional
    public ZonaRiego update(DatosSensoresZonas zonaRiego) {
        try {
            ZonaRiego zonaRiegoRegistro = zonaRiegoRepository.findByIdZona(Math.toIntExact(zonaRiego.getZonas().getIdZona()));
            ZonaRiego existingZonaRiego = zonaRiegoRepository.findByNombreZona(zonaRiego.getZonas().getNombreZona());
            if (existingZonaRiego != null && !existingZonaRiego.getIdZona().equals(zonaRiego.getZonas().getIdZona())) {
                return new ZonaRiego(-1L, "");
            }
            for (DetalleSensor sensor : zonaRiego.getSensores()) {
                sensor.setZona(zonaRiegoRegistro);
            }
            deleteListSensor(zonaRiego.getSensores(), zonaRiegoRegistro);
            return zonaRiegoRepository.save(zonaRiego.getZonas());
        } catch (Exception e) {
            return new ZonaRiego(-1L, e.getMessage());
        }
    }

    @Transactional
    public ZonaRiego updateZona(ZonaRiego zonaRiego) {
        try {
            ZonaRiego existingZonaRiego = zonaRiegoRepository.findByNombreZona(zonaRiego.getNombreZona());
            if (existingZonaRiego != null && !existingZonaRiego.getIdZona().equals(zonaRiego.getIdZona())) {
                return new ZonaRiego(-1L, "");
            }
            return zonaRiegoRepository.save(zonaRiego);
        } catch (Exception e) {
            return new ZonaRiego(-1L, e.getMessage());
        }
    }

    private boolean deleteListSensor(List<DetalleSensor> detalleSensors, ZonaRiego zonaRiego) {
        try {
            List<DetalleSensor> detalleSensorList = detalleSensorRepository.findByZona(zonaRiego);
            for (DetalleSensor sensor : detalleSensors) {
                if (!verificaDetalleSensor(detalleSensorList, sensor)) {
                    detalleSensorRepository.save(sensor);
                }
            }
            for (DetalleSensor sensor : detalleSensorList) {
                if (!verificaDetalleSensor(detalleSensors, sensor)) {
                    detalleSensorRepository.delete(sensor);
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean verificaDetalleSensor(List<DetalleSensor> sensors, DetalleSensor sensor) {
        try {
            for (DetalleSensor detalleSensor : sensors) {
                if (Objects.equals(detalleSensor.getSensor().getIdSensor(), sensor.getSensor().getIdSensor())) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional
    public ZonaRiego findById(Integer idzonaRiego) {
        try {
            Optional<ZonaRiego> zonaRiego = zonaRiegoRepository.findById(idzonaRiego);
            return zonaRiego.orElse(null);
        } catch (Exception e) {
            return new ZonaRiego(-1L, e.getMessage());
        }
    }

    @Transactional
    public ZonaRiego findByDireccionMac(String direccionMac) {
        try {
            ZonaRiego riego= new ZonaRiego();
            List<ZonaRiego> zonaRiegoList = zonaRiegoRepository.findByDireccionMAC(direccionMac);
            for (ZonaRiego zonaRiego : zonaRiegoList) {
                if(zonaRiego.getEstado()){
                    riego=zonaRiego;
                }
            }
            return riego;
        } catch (Exception e) {
            return new ZonaRiego(-1L, e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<ZonaRiego> findAll() {
        try {
            return zonaRiegoRepository.findAll();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    @Transactional
    public void delete(Integer idzonaRiego) {
        try {
            zonaRiegoRepository.deleteById(idzonaRiego);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    @Transactional
    public List<ZonaRiego> findBySistemaRiego(SistemaRiego sistemaRiego) {
        try {
            return zonaRiegoRepository.findBySistemaRiego(sistemaRiego)
                    .orElseThrow(() -> new IllegalArgumentException("Does not have a watering system installed."));
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Transactional
    public List<Sensor> findListaSensores(ZonaRiego zonaRiego) {
        try {
            List<DetalleSensor> detalleSensorsList = detalleSensorRepository.findByZona(zonaRiego);
            List<Long> detalleSensorIds = detalleSensorsList.stream()
                    .map(detalleSensor -> detalleSensor.getSensor().getIdSensor())
                    .toList();
            return sensorRepository.findByDetalleSensorIn(detalleSensorIds);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Transactional
    public List<ZonaRiego> findByNombre(String nombre) {
        try {
            return zonaRiegoRepository.findByNombreZonaContainingIgnoreCase(nombre);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Transactional
    public ZonaRiego findByZonaRiego(ZonaRiego zonaRiego) {
        try {
            return zonaRiegoRepository.findByNombreZona(zonaRiego.getNombreZona());
        } catch (Exception e) {
            return new ZonaRiego();
        }
    }
}
