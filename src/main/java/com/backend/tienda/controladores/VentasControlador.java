package com.backend.tienda.controladores;

import com.backend.tienda.entidades.Ventas;
import com.backend.tienda.servicios.VentasServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentasControlador {

    @Autowired
    private VentasServicios ventasServicios;

    @GetMapping("/listar")
    public ResponseEntity<List<Ventas>> listaVentas(){
        List<Ventas> ventas = ventasServicios.listarVentas();
        if (ventas.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(ventas);
        }
    }

    @GetMapping("/{codigo_venta}")
    public ResponseEntity<Ventas> buscarVenta(@PathVariable("codigo_venta") Double codigo_venta){
        Ventas ventas = ventasServicios.buscarVenta(codigo_venta);
        if (ventas == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(ventas);
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<Ventas> guardarVenta(@RequestBody Ventas ventas){
        Ventas nueva_venta = ventasServicios.guardarVentas(ventas);
            return ResponseEntity.status(HttpStatus.CREATED).body(nueva_venta);
    }

    @PutMapping("/actualizar/{codigo_venta}")
    public ResponseEntity<Ventas> actualizarVentas(@PathVariable("codigo_venta") Double codigo_venta, @RequestBody Ventas ventas){
        ventas.setCodigo_venta(codigo_venta);
        Ventas ventaBD = ventasServicios.actualizarVentas(ventas);
        if (ventaBD == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(ventaBD);
        }
    }

    public void eliminarVenta (@PathVariable("codigo_venta") Double codigo_venta){
        ventasServicios.eliminarVentas(codigo_venta);
    }
}
