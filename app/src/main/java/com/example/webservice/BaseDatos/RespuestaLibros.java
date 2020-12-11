package com.example.webservice.BaseDatos;

import com.example.webservice.Clases.Libros;

import java.util.ArrayList;

public class RespuestaLibros {
    private ArrayList<Libros> data;

    public ArrayList<Libros>getData(){
        return data;
    }

    public void setData(ArrayList<Libros> data){
        this.data=data;
    }
}
