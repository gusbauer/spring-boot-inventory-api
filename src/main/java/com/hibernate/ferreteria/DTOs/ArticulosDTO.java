package com.hibernate.ferreteria.DTOs;

public class ArticulosDTO {
    private long id;
    private String nombrearticulo;
    private Double precio;
    private Integer existencia;

    public ArticulosDTO(long id, String nombrearticulo, Double precio, Integer existencia) {
        this.id = id;
        this.nombrearticulo = nombrearticulo;
        this.precio = precio;
        this.existencia = existencia;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getNombrearticulo() {
        return nombrearticulo;
    }

    public void setNombrearticulo(String nombrearticulo) {
        this.nombrearticulo = nombrearticulo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getExistencia() {
        return existencia;
    }

    public void setExistencia(Integer existencia) {
        this.existencia = existencia;
    }

    @Override
    public String toString() {
        return "ArticulosDTO{" +
                "id=" + id +
                ", nombrearticulo='" + nombrearticulo + '\'' +
                ", precio=" + precio +
                ", existencia=" + existencia +
                '}';
    }
}
