package com.example.webservice;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.webservice.BaseDatos.RespuestaLibros;
import com.example.webservice.Clases.Libros;
import com.example.webservice.Clases.LibrosServices;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private final int REQUEST_CODE_PERMISO_ESCRITURA_EXTERNA=100;
    private Retrofit retrofit;
    ListView listaLibros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.listaLibros=findViewById(R.id.listaLibros);
        this.retrofit=new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ValidarPermisos();
        CargarLibrosServicio();
    }


    //Obtiene informacion de los libros a travez del web services
    private void CargarLibrosServicio(){
        LibrosServices servicio=retrofit.create(LibrosServices.class);
        Call<RespuestaLibros> respuestaLibros=servicio.getLibros(1);
        respuestaLibros.enqueue(new Callback<RespuestaLibros>() {
            @Override
            public void onResponse(Call<RespuestaLibros> call, Response<RespuestaLibros> response) {
                //Validamos que la respeusta es correcta
                if(response.isSuccessful()){
                    RespuestaLibros respuesta=response.body();
                    ArrayList<Libros> listaLibros=respuesta.getData();
                    CargarLibros(listaLibros);
                }else{
                    Toast.makeText(MainActivity.this,"No fue posible obtener la informacion de los libros",
                    Toast.LENGTH_SHORT).show();
                    Log.d("ERR_SERVICES", response.message());
                }
            }


            @Override
            public void onFailure(Call<RespuestaLibros> call, Throwable t) {
                //Si falla enviara aqui
                Toast.makeText(MainActivity.this,"Error al obtener el servicio werb"
                ,Toast.LENGTH_SHORT).show();
                Log.d("ERR_SERVICES",t.getMessage());
            }
        });
    }

    private void CargarLibros(ArrayList<Libros> libros){
        List<String> librosToString=new ArrayList<String>();
        for(Libros libro:libros){
            librosToString.add(libro.getTitulo()+" "+libro.getAutor()+" "+libro.getCategoria()
            +" "+libro.getEditorial()+" "+libro.getAnio());
        }
        this.listaLibros.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,librosToString));
    }


    private void ValidarPermisos(){
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            Toast.makeText(this,"Permisos por medio de manifest",Toast.LENGTH_SHORT).show();
        }else{
            //Verificamos si tenemos permisos de escritura
            int permisoLectura=checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if(permisoLectura!= PackageManager.PERMISSION_GRANTED){
                //Solicitamos el permiso al usuario
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISO_ESCRITURA_EXTERNA);
            }else{
                Toast.makeText(this,"Permisos otorgados", Toast.LENGTH_SHORT).show();
            }
        }
    }
}