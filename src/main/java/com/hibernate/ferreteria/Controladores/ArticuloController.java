package com.hibernate.ferreteria.Controladores;

import com.hibernate.ferreteria.DTOs.ArticulosDTO;
import com.hibernate.ferreteria.Servicios.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") //
@RestController
@RequestMapping("/api/articulos")
public class ArticuloController {

    @Autowired
    private ArticuloService servicio;

    @GetMapping
    public List<ArticulosDTO> listar(){
        return servicio.serv_consulta();
    }

    @GetMapping("/{id}")
    public ArticulosDTO buscarPorId(@PathVariable Long id) {
        return servicio.serv_buscaId(id);
    }

    @PostMapping
    public ArticulosDTO insertaArticulo(@RequestBody ArticulosDTO dto){
        return servicio.serv_Inserta(dto);
    }

    @PutMapping("/{id}")
    public ArticulosDTO actualizaArticulo(@PathVariable Long id, @RequestBody ArticulosDTO dto){
        return servicio.serv_Actualiza(id, dto);
    }

    @DeleteMapping("/{id}")
    public String borrArticulos(@PathVariable Long id){
        return servicio.eliminArticulos(id);
    }
}