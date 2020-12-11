package com.example.webservice.Clases;
import com.google.gson.annotations.SerializedName;
public class Libros {
    @SerializedName("IdLibro")
    private int IdLibro;
    @SerializedName("Titulo")
    private String Titulo;
    @SerializedName("Autor")
    private String Autor;
    @SerializedName("Categoria")
    private String Categoria;
    @SerializedName("Anio")
    private int Anio;
    @SerializedName("Editorial")
    private String Editorial;

    public int getIdLibro() {
        return IdLibro;
    }

    public void setIdLibro(int idLibro) {
        IdLibro = idLibro;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String autor) {
        Autor = autor;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public int getAnio() {
        return Anio;
    }

    public void setAnio(int anio) {
        Anio = anio;
    }

    public String getEditorial() {
        return Editorial;
    }

    public void setEditorial(String editorial) {
        Editorial = editorial;
    }
}
