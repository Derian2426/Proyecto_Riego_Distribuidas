package com.example.practica_seguridad.service;

import com.example.practica_seguridad.model.*;
import com.example.practica_seguridad.interfaces.IZonaRiegoService;
import com.example.practica_seguridad.repository.*;
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
    @Autowired
    private MonitoreoSueloRepository monitoreoSueloRepository;
    @Autowired
    private MonitoreoTemperaturaRepository monitoreoTemperaturaRepository;
    @Autowired
    private DepositoAguaService depositoAguaService;
    @Autowired
    private InformeConsumoService informeConsumoService;

    @Override
    @Transactional
    public ZonaRiego create(DatosSensoresZonas zonaRiego) {
        ZonaRiego zonaRiegoRegistro;
        List<ZonaRiego> zonaRiegoList = zonaRiegoRepository.findByDireccionMAC(zonaRiego.getZonas().getDireccionMAC());
        try {
            if (zonaRiegoRepository.findByNombreZona(zonaRiego.getZonas().getNombreZona()) != null) {
                return new ZonaRiego(-1L, "");
            }
            if (zonaRiegoList.size() > 0) {
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

    @Transactional
    public ZonaRiego createZonaRiego(ZonaRiego zonaRiego) {
        ZonaRiego zonaRiegoRegistro;
        List<ZonaRiego> zonaRiegoList = zonaRiegoRepository.findByDireccionMAC(zonaRiego.getDireccionMAC());
        try {
            if (zonaRiegoRepository.findByNombreZona(zonaRiego.getNombreZona()) != null) {
                return new ZonaRiego(-1L, "");
            }
            if (zonaRiegoList.size() > 0) {
                for (ZonaRiego zona : zonaRiegoList) {
                    zona.setEstado(false);
                }
                zonaRiegoRepository.saveAll(zonaRiegoList);
            }
            zonaRiego.setEstado(true);
            zonaRiegoRegistro = zonaRiegoRepository.save(zonaRiego);
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
    public Integer registrarCantidadAguaConsumida(ZonaRiego zonaRiego) {
        try {
            ZonaRiego existingZonaRiego = zonaRiegoRepository.findByIdZona(Math.toIntExact(zonaRiego.getIdZona()));
            if (existingZonaRiego != null) {
                registarMonitoreoSuelo(zonaRiego);
                registarMonitoreoTemperatura(zonaRiego);
                actualizarZonaRiegoConsumo(zonaRiego);
                ConsumoTanque consumoTanque = new ConsumoTanque(-1L, monitoreoSueloRepository.getCurrentDatabaseDateTimeMinusFiveHours(),
                        (zonaRiego.getRecomendacionTemaperatura().doubleValue() - zonaRiego.getRecomendacionHumedadSuelo().doubleValue()), zonaRiego.getRecomendacionHumedadAmbiente().doubleValue(),
                        zonaRiego.getRecomendacionHumedadSuelo().doubleValue(), true, (long) zonaRiego.getRecomendacionNitrogeno().intValue());
                informeConsumoService.crearInformeCosumo(consumoTanque);
            }
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }


    @Transactional
    public Integer tomarDesiscionRiegoAgua(ZonaRiego zonaRiego) {
        try {
            int establecerTiempoRiego;
            ZonaRiego existingZonaRiego = zonaRiegoRepository.findByIdZona(Math.toIntExact(zonaRiego.getIdZona()));
            if (existingZonaRiego != null) {
                registarMonitoreoSuelo(zonaRiego);
                registarMonitoreoTemperatura(zonaRiego);
                actualizarZonaRiego(zonaRiego);
                int idTanqueAgua = Math.toIntExact(depositoAguaService.findByIdTanque(zonaRiego, "agua"));
                DepositoAgua agua = depositoAguaService.findById(idTanqueAgua);
                if (agua.getCantidadLiquido() <= 0.9) {
                    establecerTiempoRiego = 0;
                } else if (agua.getCantidadLiquido() > 0.9 && agua.getCantidadLiquido() <= 2) {
                    establecerTiempoRiego = 60000;
                } else if (agua.getCantidadLiquido() > 2 && agua.getCantidadLiquido() <= 3) {
                    establecerTiempoRiego = 90000;
                } else {
                    establecerTiempoRiego = 120000;
                }
                if ((zonaRiego.getUltimaHumedadSuelo() < zonaRiego.getRecomendacionHumedadSuelo())&&
                        (zonaRiego.getUltimaTemaperatura()<=zonaRiego.getRecomendacionTemaperatura()&&
                                zonaRiego.getUltimaHumedadAmbiente()<=zonaRiego.getRecomendacionHumedadAmbiente())) {
                    return establecerTiempoRiego;
                }else{
                    return 0;
                }
            }
            return 0;
        } catch (Exception e) {
            return 0;
        }
    }

    @Transactional
    public Integer tomarDesiscionRiegoNutriente(ZonaRiego zonaRiego) {
        try {
            int establecerTiempoRiego;
            ZonaRiego existingZonaRiego = zonaRiegoRepository.findByIdZona(Math.toIntExact(zonaRiego.getIdZona()));
            if (existingZonaRiego != null) {
                int idTanqueNutriente = Math.toIntExact(depositoAguaService.findByIdTanque(zonaRiego, "nutrientes"));
                DepositoAgua nutrientes = depositoAguaService.findById(idTanqueNutriente);
                registarMonitoreoSuelo(zonaRiego);
                registarMonitoreoTemperatura(zonaRiego);
                actualizarZonaRiego(zonaRiego);
                if (nutrientes.getCantidadLiquido() <= 0.9) {
                    establecerTiempoRiego = 0;
                } else if (nutrientes.getCantidadLiquido() > 0.9 && nutrientes.getCantidadLiquido() <= 2) {
                    establecerTiempoRiego = 60000;
                } else if (nutrientes.getCantidadLiquido() > 2 && nutrientes.getCantidadLiquido() <= 3) {
                    establecerTiempoRiego = 90000;
                } else {
                    establecerTiempoRiego = 120000;
                }
                if (zonaRiego.getUltimaFosforo() < zonaRiego.getRecomendacionFosforo() ||
                        zonaRiego.getUltimaPotasio() < zonaRiego.getRecomendacionPotasio() ||
                        zonaRiego.getUltimaNitrogeno() < zonaRiego.getRecomendacionNitrogeno()) {
                    return establecerTiempoRiego;
                }else{
                    return 0;
                }
            }
            return 0;
        } catch (Exception e) {
            return 0;
        }
    }
    @Transactional
    void actualizarZonaRiegoConsumo(ZonaRiego zonaRiego) {
        try {
            ZonaRiego existingZonaRiego = zonaRiegoRepository.findByIdZona(Math.toIntExact(zonaRiego.getIdZona()));
            existingZonaRiego.setUltimaFosforo(zonaRiego.getUltimaFosforo());
            existingZonaRiego.setUltimaPotasio(zonaRiego.getUltimaPotasio());
            existingZonaRiego.setUltimaNitrogeno(zonaRiego.getUltimaNitrogeno());
            existingZonaRiego.setUltimaTemaperatura(zonaRiego.getUltimaTemaperatura());
            existingZonaRiego.setUltimaHumedadSuelo(zonaRiego.getUltimaHumedadSuelo());
            existingZonaRiego.setUltimaHumedadAmbiente(zonaRiego.getUltimaHumedadAmbiente());
            existingZonaRiego.setAplicarAgua(false);
            updateZona(existingZonaRiego);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Transactional
    void actualizarZonaRiego(ZonaRiego zonaRiego) {
        try {
            ZonaRiego existingZonaRiego = zonaRiegoRepository.findByIdZona(Math.toIntExact(zonaRiego.getIdZona()));
            existingZonaRiego.setUltimaFosforo(zonaRiego.getUltimaFosforo());
            existingZonaRiego.setUltimaPotasio(zonaRiego.getUltimaPotasio());
            existingZonaRiego.setUltimaNitrogeno(zonaRiego.getUltimaNitrogeno());
            existingZonaRiego.setUltimaTemaperatura(zonaRiego.getUltimaTemaperatura());
            existingZonaRiego.setUltimaHumedadSuelo(zonaRiego.getUltimaHumedadSuelo());
            existingZonaRiego.setUltimaHumedadAmbiente(zonaRiego.getUltimaHumedadAmbiente());
            updateZona(existingZonaRiego);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Transactional
    void registarMonitoreoTemperatura(ZonaRiego zonaRiego) {
        try {
            MonitoreoTemperatura monitoreoTemperaturaAnterior = monitoreoTemperaturaRepository.findTopByZonaRiegoOrderByIdTemperaturaDesc(zonaRiego);
            MonitoreoTemperatura monitoreoTemperatura = new MonitoreoTemperatura(-1L, monitoreoSueloRepository.getCurrentDatabaseDateTimeMinusFiveHours(),
                    zonaRiego.getUltimaTemaperatura(),
                    zonaRiego.getUltimaHumedadAmbiente(), zonaRiego);
            if (monitoreoTemperaturaAnterior.getTemperatura() != monitoreoTemperatura.getTemperatura() ||
                    monitoreoTemperaturaAnterior.getHumedad() != monitoreoTemperatura.getHumedad()) {
                monitoreoTemperaturaRepository.save(monitoreoTemperatura);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Transactional
    void registarMonitoreoSuelo(ZonaRiego zonaRiego) {
        try {
            MonitoreoSuelo monitoreoSueloAnterior = monitoreoSueloRepository.findTopByZonaRiegoOrderByIdSueloDesc(zonaRiego);
            MonitoreoSuelo monitoreoSuelo = new MonitoreoSuelo(-1L, monitoreoSueloRepository.getCurrentDatabaseDateTimeMinusFiveHours(), zonaRiego.getUltimaHumedadSuelo(),
                    zonaRiego.getUltimaNitrogeno(), zonaRiego.getUltimaFosforo(), zonaRiego.getUltimaPotasio(),
                    "mg/kg", zonaRiego);
            if (monitoreoSueloAnterior.getHumedad() != monitoreoSuelo.getHumedad() ||
                    monitoreoSueloAnterior.getFosforo() != monitoreoSuelo.getFosforo() ||
                    monitoreoSueloAnterior.getNitrogeno() != monitoreoSuelo.getNitrogeno() ||
                    monitoreoSueloAnterior.getPotasio() != monitoreoSuelo.getPotasio()) {
                monitoreoSueloRepository.save(monitoreoSuelo);
            }
        } catch (Exception e) {
            throw new RuntimeException();
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
            ZonaRiego riego = new ZonaRiego();
            List<ZonaRiego> zonaRiegoList = zonaRiegoRepository.findByDireccionMAC(direccionMac);
            for (ZonaRiego zonaRiego : zonaRiegoList) {
                if (zonaRiego.getEstado()) {
                    riego = zonaRiego;
                }
            }
            return riego;
        } catch (Exception e) {
            return new ZonaRiego(-1L, e.getMessage());
        }
    }

    @Transactional
    public Integer findByDireccionMacId(String direccionMac) {
        try {
            ZonaRiego riego = new ZonaRiego();
            List<ZonaRiego> zonaRiegoList = zonaRiegoRepository.findByDireccionMAC(direccionMac);
            for (ZonaRiego zonaRiego : zonaRiegoList) {
                if (zonaRiego.getEstado()) {
                    riego = zonaRiego;
                }
            }
            return Math.toIntExact(riego.getIdZona());
        } catch (Exception e) {
            return -1;
        }
    }

    @Transactional
    public Boolean verificarRiegoAgua(String direccionMac) {
        try {
            ZonaRiego riego = findByDireccionMac(direccionMac);
            return riego.isAplicarAgua();
        } catch (Exception e) {
            return false;
        }

    }

    @Transactional
    public Boolean verificarRiegoNutrientes(String direccionMac) {
        try {
            ZonaRiego riego = findByDireccionMac(direccionMac);
            return riego.isAplicarNutrientes();
        } catch (Exception e) {
            return false;
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
