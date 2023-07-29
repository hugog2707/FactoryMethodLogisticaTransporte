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

        List<Inventario> inventarios = inventarioService.obtenerInventario();

        if (inventario == null || inventario.getTipoInventario() == null || inventario.getTipoInventario().getNombre().isEmpty()) {
            return ErrorUtils.buildMessageResponseIsEmpty("guardar inventario", "inventario");
        }

        try {

            for (Inventario i : inventarios) {

                if (i.getTipoInventario().getNombre().equals(inventario.getTipoInventario().getNombre())) {
                    return ErrorUtils.buildMessageDuplicateError(inventario.getTipoInventario().getNombre(), "guardar inventario", "tipo de inventario");
                }

            }

            inventarioService.GuardarInventario(inventario);
            return new ResponseEntity<>("Inventario guardado, tipo de inventario: " + inventario.getTipoInventario().getNombre(), HttpStatus.CREATED);

        } catch (Exception e) {
            return ErrorUtils.buildMessageResponseError(inventario.getTipoInventario().getNombre(), "guardar inventario", e);
        }
    }

    @PostMapping("/guardarTipoInventario")
    public ResponseEntity<String> guardarTipoInventario(@RequestBody TipoInventario tipoInventario) {

        List<TipoInventario> tipoInventarios = tipoInventarioService.obtenerTipoInventario();

        if (tipoInventario == null || tipoInventario.getNombre().isEmpty()) {
            return ErrorUtils.buildMessageResponseIsEmpty("guardar tipo de inventario", "tipo de inventario");
        }

        try {

            for (TipoInventario ti : tipoInventarios) {

                if (ti.getNombre().equals(tipoInventario.getNombre())) {
                    return ErrorUtils.buildMessageDuplicateError(tipoInventario.getNombre(), "guardar tipo de inventario", "tipo de inventario");
                }

            }

            tipoInventarioService.GuardarTipoInventario(tipoInventario);
            return new ResponseEntity<>("Tipo de inventario guardado: " + tipoInventario.getNombre(), HttpStatus.CREATED);

        } catch (DataIntegrityViolationException e) {
            return ErrorUtils.buildMessageDuplicateError(tipoInventario.getNombre(), "guardar tipo de inventario", "tipo de inventario");
        } catch (Exception e) {
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
            return ErrorUtils.buildMessageResponseIsEmpty("guardar personal", "personal");
        }

        try {

            for (Personal p : personalList) {

                if (p.getNombre().equals(personal.getNombre())) {
                    return ErrorUtils.buildMessageDuplicateError(personal.getNombre(), "guardar personal", "nombre");
                } else if (p.getNumeroDocumento().equals(personal.getNumeroDocumento())) {
                    return ErrorUtils.buildMessageDuplicateError(personal.getNumeroDocumento(), "guardar personal", "numero de documento");
                } else if (p.getCorreo().equals(personal.getCorreo())) {
                    return ErrorUtils.buildMessageDuplicateError(personal.getCorreo(), "guardar personal", "correo");
                }

            }

            personalService.GuardarPersonal(personal);
            return new ResponseEntity<>("Personal guardado: " + personal.getNombre(), HttpStatus.CREATED);

        } catch (Exception e) {

            return ErrorUtils.buildMessageResponseError(personal.getNombre(), "guardar personal", e);

        }
    }

    @GetMapping("/obtenerPersonal/{id}")
    public ResponseEntity<Personal> obtenerPersonal(@PathVariable Long id) {

        if (id == null || id <= 0) {
            return ErrorUtils.buildMessageResponseBadRequest("personal", "obtener personal", "id");
        }

        try {

            Personal p = personalService.obtenerPersonalPorId(id);
            if (p != null)
                return new ResponseEntity<>(p, HttpStatus.OK);

            return ErrorUtils.buildMessageResponseNotFound("personal", "obtener personal", "id");

        } catch (Exception e) {
            return ErrorUtils.buildMessageResponseError("personal", "obtener personal", e);
        }
    }

    @GetMapping("/obtenerPersonal")
    public ResponseEntity<Iterable<Personal>> obtenerPersonal() {

        try {
            Iterable<Personal> p = personalService.obtenerPersonal();
            return new ResponseEntity<>(p, HttpStatus.OK);
        } catch (Exception e) {
            return ErrorUtils.buildMessageResponseError("personal", "obtener personal", e);
        }

    }

    @PostMapping("/guardarCargo")
    public ResponseEntity<String> guardarCargo(@RequestBody Cargo cargo) {

        List<Cargo> cargos = cargoService.obtenerTodosLosCargos();

        if (cargo == null || cargo.getNombre().isEmpty()) {
            return ErrorUtils.buildMessageResponseIsEmpty("guardar cargo", "cargo");
        }

        try {

            for (Cargo c : cargos) {
                if (c.getNombre().equals(cargo.getNombre())) {
                    return ErrorUtils.buildMessageDuplicateError(cargo.getNombre(), "guardar cargo", "cargo");
                }
            }

            cargoService.GuardarCargo(cargo);
            return new ResponseEntity<>("Cargo guardado, Nombre del cargo: " + cargo.getNombre(), HttpStatus.CREATED);

        } catch (DataIntegrityViolationException e) {
            return ErrorUtils.buildMessageDuplicateError(cargo.getNombre(), "guardar cargo", "cargo");
        } catch (Exception e) {
            return ErrorUtils.buildMessageResponseError(cargo.getNombre(), "guardar cargo", e);
        }

    }

    @PostMapping("/guardarProductos")
    public ResponseEntity<String> guardarProducto(@RequestBody Productos[] producto) {
        int contador = 0;
        List<String> errores = new ArrayList<>();
        List<Productos> productos = productosService.obtenerProductos();

        if (producto == null || producto.length == 0) {
            return ErrorUtils.buildMessageResponseIsEmpty("guardar producto", "producto");
        }

        for (Productos productos1 : productos) {

            for (Productos p : producto) {

                if (p.getNombre().equals(productos1.getNombre())) {
                    errores.add(ErrorUtils.buildMessageError("guardar producto", p.getNombre(), new Exception("el producto ya existe")));
                    //pendiente verificar que sea el ultimo producto del array
                } else {
                    productosService.GuardarProducto(p);
                    System.out.println("Producto guardado: " + p.getNombre());
                    contador++;
                }

            }

        }

        for (Productos p : producto) {
            try {
                for (Productos p2 : productos) {

                    if (p2.getNombre().equals(p.getNombre())) {

                        System.out.println("#####");
                        errores.add(ErrorUtils.buildMessageError("guardar producto", p.getNombre(), new Exception("el producto ya existe")));
                        // verificar si es el ultimo producto del array p2
                        if (p2.equals(productos.get(productos.size() - 1))) {
                            return new ResponseEntity(errores, HttpStatus.CREATED);
                        }

                    } else {
                        productosService.GuardarProducto(p);
                        System.out.println("Producto guardado: " + p.getNombre());
                        contador++;
                    }

                }

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
