package com.demo.FactoryMethodLogisticaTransporte.controller;

import com.demo.FactoryMethodLogisticaTransporte.models.*;
import com.demo.FactoryMethodLogisticaTransporte.service.*;
import com.demo.FactoryMethodLogisticaTransporte.utils.ErrorUtils;
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

    @PostMapping("/guardarInventario")
    public ResponseEntity<String> guardarInventario(@RequestBody Inventario inventario) {
        try {
            if (inventario != null) {
                inventarioService.GuardarInventario(inventario);
                return new ResponseEntity<>("Inventario guardado, tipo de inventario: " + inventario.getTipoInventario().getNombre(), HttpStatus.CREATED);
            } else {
                System.out.println("Error al guardar inventario: " + inventario.getTipoInventario().getNombre());
                return new ResponseEntity<>("Error al guardar inventario: ", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al guardar inventario: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/guardarTipoInventario")
    public ResponseEntity<String> guardarTipoInventario(@RequestBody TipoInventario tipoInventario) {
        try {
            if (tipoInventario != null) {
                tipoInventarioService.GuardarTipoInventario(tipoInventario);
                return new ResponseEntity<>("Tipo de inventario guardado: " + tipoInventario.getNombre(), HttpStatus.CREATED);
            } else {
                System.out.println("Error al guardar tipo de inventario: " + tipoInventario.getNombre());
                return new ResponseEntity<>("Error al guardar tipo de inventario: ", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            if (e.getMessage().contains("Duplicate")) {
                return new ResponseEntity<>("Error al guardar tipo de inventario: el tipo de inventario ya existe", HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>("Error al guardar tipo de inventario: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/obtenerTiposInventario")
    public Iterable<TipoInventario> obtenerTiposInventario() {
        return tipoInventarioService.obtenerTipoInventario();
    }

    @PostMapping("/guardarPersonal")
    public ResponseEntity<String> guardarPersonal(@RequestBody Personal personal) {
        try {
            if (personal != null) {
                personalService.GuardarPersonal(personal);
                return new ResponseEntity<>("Personal guardado: " + personal.getNombre(), HttpStatus.CREATED);
            } else {
                System.out.println("Error al guardar personal: " + personal.getNombre());
                return new ResponseEntity<>("Error al guardar personal: ", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            if (e.getMessage().contains("Duplicate")) {
                if (e.getMessage().contains("nombre"))
                    return new ResponseEntity<>("Error al guardar personal: el nombre ya existe", HttpStatus.CONFLICT);
                else if (e.getMessage().contains("numero_documento"))
                    return new ResponseEntity<>("Error al guardar personal: el número de documento ya existe", HttpStatus.CONFLICT);
                else if (e.getMessage().contains("correo"))
                    return new ResponseEntity<>("Error al guardar personal: el correo ya existe", HttpStatus.CONFLICT);

                return new ResponseEntity<>("Error al guardar el personal", HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>("Error al guardar personal: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/obtenerPersonal/{id}")
    public ResponseEntity<Personal> obtenerPersonal(@PathVariable Long id) {
        try {
            if (id != null) {
                Personal p = personalService.obtenerPersonalPorId(id);
                if (p != null)
                    return new ResponseEntity<>(p, HttpStatus.OK);
                else
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            } else {
                System.out.println("Error al obtener personal por el id: " + id);
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/obtenerPersonal")
    public ResponseEntity<Iterable<Personal>> obtenerPersonal() {
        try {
            Iterable<Personal> p = personalService.obtenerPersonal();
            return new ResponseEntity<>(p, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/guardarCargo")
    public ResponseEntity<String> guardarCargo(@RequestBody Cargo cargo) {
        try {
            if (cargo != null) {
                cargoService.GuardarCargo(cargo);
                return new ResponseEntity<>("Cargo guardado: " + cargo.getNombre(), HttpStatus.CREATED);
            } else {
                System.out.println("Error al guardar cargo: " + cargo.getNombre());
                return new ResponseEntity<>("Error al guardar cargo: ", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            if (e.getMessage().contains("Duplicate")) {
                return new ResponseEntity<>("Error al guardar cargo: el cargo ya existe", HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>("Error al guardar cargo: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/guardarProductos")
    public ResponseEntity<String> guardarProducto(@RequestBody Productos[] producto) {
        if (producto == null || producto.length == 0) {
            return new ResponseEntity<>("No se a enviado ningun producto", HttpStatus.BAD_REQUEST);
        }

        int contador = 0;
        List<String> errores = new ArrayList<>();

            for (Productos p : producto) {
                try {
                    productosService.GuardarProducto(p);
                    contador++;
                } catch (DataIntegrityViolationException e) {
                    errores.add(ErrorUtils.buildMessageError("guardar producto", p.getNombre(), e));
                } catch (Exception e) {
                    errores.add(ErrorUtils.buildMessageError("guardar producto", p.getNombre(), e));
                }
        }
        return new ResponseEntity<>("Producto/s guardado/s, cantidad: " + contador, HttpStatus.CREATED);
    }

    @GetMapping("/obtenerProductos")
    public Iterable<Productos> obtenerProducto() {
        return productosService.obtenerProductos();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        String errorMessage = "Error en la solicitud: el cuerpo de la solicitud está vacío o es inválido.";
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
