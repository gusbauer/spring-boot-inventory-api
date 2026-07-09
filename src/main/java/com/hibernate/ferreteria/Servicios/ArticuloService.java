package com.hibernate.ferreteria.Servicios;
import com.hibernate.ferreteria.DTOs.ArticulosDTO;
import com.hibernate.ferreteria.Repositorios.Repo_Articulos;
import com.hibernate.ferreteria.entity.Articulos;
import com.hibernate.ferreteria.mapper.ArticuloMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticuloService {

    @Autowired
    private Repo_Articulos repo;

    public List<ArticulosDTO> serv_consulta() {
        return repo.findAll().stream()
                .map(ArticuloMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ArticulosDTO serv_buscaId(Long id) {
        Articulos articuloporId = repo.findById(id).orElseThrow(() -> new RuntimeException("Articulo con id:"+id + "no encontrado"));
        return ArticuloMapper.toDTO(articuloporId);

    }

    public ArticulosDTO serv_Inserta(ArticulosDTO dto){
    Articulos articulo = ArticuloMapper.toEntity(dto);
    Articulos insertado = repo.save(articulo);
    return ArticuloMapper.toDTO(insertado);
    }

    public ArticulosDTO serv_Actualiza (Long id, ArticulosDTO dto){
        Optional<Articulos> existe = repo.findById(id);

        if(existe.isPresent()){
            Articulos articulo = existe.get();
            articulo.setNombrearticulo(dto.getNombrearticulo());
            articulo.setPrecio(dto.getPrecio());
            articulo.setExistencia(dto.getExistencia());

            Articulos actualizado = repo.save(articulo);

            return ArticuloMapper.toDTO(actualizado);
        }else {
            throw new RuntimeException("Articulo no encontrado con id: "+ id);
        }
    }
    public String eliminArticulos(Long id){
        if (repo.existsById(id)){
            repo.deleteById(id);
            return "Articulo eliminado correctamente";
            }else {
                return "Articulo: "+id + "No encontrado";

            }
}

}