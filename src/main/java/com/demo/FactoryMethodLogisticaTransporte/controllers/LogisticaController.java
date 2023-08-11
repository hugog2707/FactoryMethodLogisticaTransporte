package com.demo.FactoryMethodLogisticaTransporte.controllers;

import com.demo.FactoryMethodLogisticaTransporte.models.*;
import com.demo.FactoryMethodLogisticaTransporte.services.*;
import com.demo.FactoryMethodLogisticaTransporte.utils.ErrorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/logisticaApi")
@RestController
public class LogisticaController {
    @Autowired
    private InventarioServiceImpl inventarioService;
    @Autowired
    private TipoInventarioServiceImpl tipoInventarioService;
    @Autowired
    private PersonalServiceImpl personalService;
    @Autowired
    private CargoServiceImpl cargoService;
    @Autowired
    private ProductosServiceImpl productosService;
    @Autowired
    private HistorialProductosServiceImpl historialProductosService;
    // instance logger
    private static final Logger logger = LoggerFactory.getLogger(LogisticaController.class);

    @PostMapping("/guardarInventario")
    public ResponseEntity<String> guardarInventario(@RequestBody Inventario inventario) {

        List<Inventario> inventarios = inventarioService.obtenerInventario();

        if (inventario == null || inventario.getTipoInventario() == null || inventario.getTipoInventario().getNombre().isEmpty()) {
            logger.error("Error al guardar inventario, inventario: " + inventario);
            return ErrorUtils.buildMessageResponseIsEmpty("guardar inventario", "inventario");
        }

        try {

            for (Inventario i : inventarios) {

                if (i.getTipoInventario().getNombre().equals(inventario.getTipoInventario().getNombre())) {
                    logger.error("Error al guardar inventario, tipo de inventario: " + inventario.getTipoInventario().getNombre());
                    return ErrorUtils.buildMessageDuplicateError(inventario.getTipoInventario().getNombre(), "guardar inventario", "tipo de inventario");
                }

            }

            inventarioService.GuardarInventario(inventario);
            return new ResponseEntity<>("Inventario guardado, tipo de inventario: " + inventario.getTipoInventario().getNombre(), HttpStatus.CREATED);

        } catch (Exception e) {
            logger.error("Error al guardar inventario, tipo de inventario: " + inventario.getTipoInventario().getNombre());
            return ErrorUtils.buildMessageResponseError(inventario.getTipoInventario().getNombre(), "guardar inventario", e);
        }
    }

    @PostMapping("/guardarTipoInventario")
    public ResponseEntity<String> guardarTipoInventario(@RequestBody TipoInventario tipoInventario) {

        List<TipoInventario> tipoInventarios = tipoInventarioService.obtenerTipoInventario();

        if (tipoInventario == null || tipoInventario.getNombre().isEmpty()) {
            logger.error("Error al guardar tipo de inventario, el tipo de inventario es nulo o esta vacio");
            return ErrorUtils.buildMessageResponseIsEmpty("guardar tipo de inventario", "tipo de inventario");
        }

        try {

            for (TipoInventario ti : tipoInventarios) {

                if (ti.getNombre().equals(tipoInventario.getNombre())) {
                    logger.error("Error al guardar tipo de inventario, tipo de inventario: " + tipoInventario.getNombre());
                    return ErrorUtils.buildMessageDuplicateError(tipoInventario.getNombre(), "guardar tipo de inventario", "tipo de inventario");
                }

            }

            tipoInventarioService.GuardarTipoInventario(tipoInventario);
            return new ResponseEntity<>("Tipo de inventario guardado: " + tipoInventario.getNombre(), HttpStatus.CREATED);

        } catch (DataIntegrityViolationException e) {
            logger.error("Error al guardar tipo de inventario, tipo de inventario: " + tipoInventario.getNombre());
            return ErrorUtils.buildMessageDuplicateError(tipoInventario.getNombre(), "guardar tipo de inventario", "tipo de inventario");
        } catch (Exception e) {
            logger.error("Error al guardar tipo de inventario, tipo de inventario: " + tipoInventario.getNombre());
            return ErrorUtils.buildMessageResponseError(tipoInventario.getNombre(), "guardar tipo de inventario", e);
        }

    }

    @GetMapping("/obtenerTiposInventario")
    public Iterable<TipoInventario> obtenerTiposInventario() {
        return tipoInventarioService.obtenerTipoInventario();
    }

    @PostMapping("/guardarPersonal")
    public ResponseEntity<String> guardarPersonal(@RequestBody Personal personal) {

        List<Personal> personalList = personalService.obtenerPersonal();

        if (personal == null || personal.getNombre().isEmpty()) {
            logger.error("Error al guardar personal, personal: " + personal);
            return ErrorUtils.buildMessageResponseIsEmpty("guardar personal", "personal");
        }

        try {

            for (Personal p : personalList) {

                if (p.getNombre().equals(personal.getNombre())) {
                    logger.error("Error al guardar personal, personal: " + personal.getNombre());
                    return ErrorUtils.buildMessageDuplicateError(personal.getNombre(), "guardar personal", "nombre");
                } else if (p.getNumeroDocumento().equals(personal.getNumeroDocumento())) {
                    logger.error("Error al guardar personal, personal: " + personal.getNumeroDocumento());
                    return ErrorUtils.buildMessageDuplicateError(personal.getNumeroDocumento(), "guardar personal", "numero de documento");
                } else if (p.getCorreo().equals(personal.getCorreo())) {
                    logger.error("Error al guardar personal, personal: " + personal.getCorreo());
                    return ErrorUtils.buildMessageDuplicateError(personal.getCorreo(), "guardar personal", "correo");
                }

            }

            personalService.GuardarPersonal(personal);
            logger.info("Personal guardado, personal: " + personal.getNombre());
            return new ResponseEntity<>("Personal guardado: " + personal.getNombre(), HttpStatus.CREATED);

        } catch (Exception e) {

            logger.error("Error al guardar personal, personal: " + personal.getNombre());
            return ErrorUtils.buildMessageResponseError(personal.getNombre(), "guardar personal", e);

        }
    }

    @GetMapping("/obtenerPersonal/{id}")
    public ResponseEntity<Personal> obtenerPersonal(@PathVariable Long id) {

        if (id == null || id <= 0) {
            logger.error("Error al obtener personal, id: " + id);
            return ErrorUtils.buildMessageResponseBadRequest("personal", "obtener personal", "id");
        }

        try {

            Personal p = personalService.obtenerPersonalPorId(id);
            if (p != null) {

                logger.info("Personal obtenido, personal: " + p.getNombre());
                return new ResponseEntity<>(p, HttpStatus.OK);

            }

            logger.error("Error al obtener personal, id: " + id);
            return ErrorUtils.buildMessageResponseNotFound("personal", "obtener personal", "id");

        } catch (Exception e) {
            logger.error("Error al obtener personal, id: " + id);
            return ErrorUtils.buildMessageResponseError("personal", "obtener personal", e);
        }
    }

    @GetMapping("/obtenerPersonal")
    public ResponseEntity<Iterable<Personal>> obtenerPersonal() {

        try {
            Iterable<Personal> p = personalService.obtenerPersonal();
            logger.info("Personal obtenido");
            return new ResponseEntity<>(p, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error al obtener personal");
            return ErrorUtils.buildMessageResponseError("personal", "obtener personal", e);
        }

    }

    @PostMapping("/guardarCargo")
    public ResponseEntity<String> guardarCargo(@RequestBody Cargo cargo) {

        List<Cargo> cargos = cargoService.obtenerTodosLosCargos();

        if (cargo == null || cargo.getNombre().isEmpty()) {
            logger.error("Error al guardar cargo, cargo: " + cargo);
            return ErrorUtils.buildMessageResponseIsEmpty("guardar cargo", "cargo");
        }

        try {

            for (Cargo c : cargos) {
                if (c.getNombre().equals(cargo.getNombre())) {
                    logger.error("Error al guardar cargo, cargo: " + cargo.getNombre());
                    return ErrorUtils.buildMessageDuplicateError(cargo.getNombre(), "guardar cargo", "cargo");
                }
            }

            cargoService.GuardarCargo(cargo);
            logger.info("Cargo guardado, Nombre del cargo: " + cargo.getNombre());
            return new ResponseEntity<>("Cargo guardado, Nombre del cargo: " + cargo.getNombre(), HttpStatus.CREATED);

        } catch (DataIntegrityViolationException e) {
            logger.error("Error al guardar cargo, cargo: " + cargo.getNombre());
            return ErrorUtils.buildMessageDuplicateError(cargo.getNombre(), "guardar cargo", "cargo");
        } catch (Exception e) {
            logger.error("Error al guardar cargo, cargo: " + cargo.getNombre());
            return ErrorUtils.buildMessageResponseError(cargo.getNombre(), "guardar cargo", e);
        }

    }

    @PostMapping("/guardarProductos")
    public ResponseEntity<String> guardarProducto(@RequestBody Productos[] producto) {
        int contador = 0;
        List<String> errores = new ArrayList<>();

        if (producto == null || producto.length == 0) {
            logger.error("Error al guardar producto, producto: " + producto);
            return ErrorUtils.buildMessageResponseIsEmpty("guardar producto", "producto");
        }

        for (Productos p : producto) {

            if (productosService.obtenerProductoPorNombre(p.getNombre()) != null) {
                errores.add(ErrorUtils.buildMessageError("guardar producto", p.getNombre(), new Exception("el producto ya existe")));
                if (p.equals(producto[producto.length - 1])) {
                    logger.error("Error al guardar los productos");
                    return new ResponseEntity(errores, HttpStatus.CREATED);
                }
            } else {
                productosService.GuardarProducto(p);
                logger.info("Producto guardado, producto: " + p.getNombre());
                contador++;
            }

        }
        if (errores.size() > 0) {
            logger.error("Producto/s guardado/s, cantidad: " + contador + ", numero de productos no guardados: " + errores.size());
            return new ResponseEntity<>("Producto/s guardado/s, cantidad: " + contador + ", numero de productos no guardados: " + errores.size(), HttpStatus.CREATED);
        }

        return new ResponseEntity<>("Producto/s guardado/s, cantidad: " + contador, HttpStatus.CREATED);
    }

    @GetMapping("/obtenerProductos")
    public Iterable<Productos> obtenerProducto() {
        return productosService.obtenerProductos();
    }

    @GetMapping("/obtenerHistorial")
    public Iterable<HistorialProductos> obtenerHistorial() {
        if (historialProductosService.getHistorialProductos().isEmpty()) {
            return null;
        }
        return historialProductosService.getHistorialProductos();
    }

    @GetMapping("/obtenerHistorial/{fecha}")
    public Iterable<HistorialProductos> obtenerHistorialPorFecha(@PathVariable String fecha) {
        if (historialProductosService.getHistorialProductosByFecha(fecha).isEmpty()) {
            return null;
        }
        return historialProductosService.getHistorialProductosByFecha(fecha);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        String errorMessage = "Error en la solicitud: el cuerpo de la solicitud está vacío o es inválido.";
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
