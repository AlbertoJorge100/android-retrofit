package com.example.webservice.Clases;

import com.example.webservice.BaseDatos.RespuestaLibros;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryName;

/**
 * En esta clase establecemos la conexion con el web services, aqui van todos
 * los metodos para conectarse con ella (post, get, put, etc)
 */
public interface LibrosServices {
    //...api/Libros?id=
    @GET("Libros")
    //users/list"
    Call<RespuestaLibros> getLibros(@Query("IdLibro") int IdLibro);
    //Call<RespuestaLibros> getLibros();


    @POST("AddLibros")
    Call<RespuestaLibros> AddLibros(@Field("Titulo") String Titulo,
                                    @Field("Autor") String Autor,
                                    @Field("Categoria") String Categoria,
                                    @Field("year") int Anio,
                                    @Field("Editorial") String Editorial);
}
