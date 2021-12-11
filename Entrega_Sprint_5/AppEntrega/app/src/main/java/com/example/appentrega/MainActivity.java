package com.example.appentrega;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appentrega.model.Persona;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private int MY_PERMISSIONS_REQUEST_READ_CONTACTS;
    private FirebaseAuth mAuth;
    private Button registrar, ingreso;
    private String emailtexto, passwordtexto;
    private final String TAG = "OJO@ log Autentication";
    EditText password, mail;
    Datos datoPersona = Datos.getInstance();
    private FusedLocationProviderClient fusedLocationClient; // para realizar la localizacion
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ///////////////////////////////////////////
        this.setTitle("App IMC v1.0");
        mAuth = FirebaseAuth.getInstance();
        password = (EditText) findViewById(R.id.contrasenaType);
        mail = (EditText) findViewById(R.id.emailType);
        ingreso = (Button) findViewById(R.id.aceptar);
        registrar = (Button) findViewById(R.id.registrar_usuario);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        ingreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailtexto = mail.getText().toString();
                passwordtexto = password.getText().toString();
                if(!emailtexto.isEmpty() && !passwordtexto.isEmpty()&&
                        (emailtexto.contains("@edu.es")||emailtexto.contains("@edu.bu"))
                ){
                    datoPersona.setMail(emailtexto);//usuario antiguo
                    sigInWithFireBase(emailtexto,passwordtexto);
                    subirLatLongFireBare(); //pide permiso al usuario para acceder al gps para obtener la ubicacion
                }else{
                    Toast.makeText(MainActivity.this, "Verifique datos",Toast.LENGTH_SHORT).show();
                }
            }
        });
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailtexto = mail.getText().toString();
                passwordtexto = password.getText().toString();
                if(!emailtexto.isEmpty() && !passwordtexto.isEmpty()
                   &&(emailtexto.contains("@edu.es")||emailtexto.contains("@edu.bu"))
                ){
                    datoPersona.setMail(emailtexto);//usuario  nuevo
                    registrarUsuario(emailtexto, passwordtexto);
                    } else {
                        Toast.makeText(MainActivity.this, "1 Verifique datos ",
                                Toast.LENGTH_LONG).show();
                    }
                }
        });
    }


    private void sigInWithFireBase(String mail, String clave) {
        mAuth.signInWithEmailAndPassword(mail, clave)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            ir_a_datos();
                        }else {
                            Log.d(TAG, "signInWithEmail:failure", task.getException());
                            String msg=task.getException().getMessage().contains("no user record")?"Usuario no existe/Registrar":"timeout";
                            Toast.makeText(MainActivity.this, msg,Toast.LENGTH_LONG).show();
                            Log.d(TAG, task.getException().getMessage());
                        }
                    }
                });

    }

    private void registrarUsuario(String mail, String clave) {
        mAuth.createUserWithEmailAndPassword(mail, clave)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Regristro existoso",Toast.LENGTH_SHORT).show();
                            ir_a_datos();
                        } else {
                            String msg="No se pudo registrar el usuario";
                            msg+=task.getException().getMessage().contains("already in use")?"(Cuenta ya existe)":"";
                            msg+=task.getException().getMessage().contains("least 6 characters")?"(contraseña minimo 6 caracteres)":"";

                            Toast.makeText(MainActivity.this, msg,Toast.LENGTH_LONG).show();
                            Log.d(TAG, task.getException().getMessage());
                        }
                    }
                });
    }

    private void ir_a_datos() {
            Intent intent = new Intent(MainActivity.this, Activity_Datos.class);
            startActivity(intent);
            finish();
    }
//// subimos la ubicacion de los usuarios a una nueva coleccion en firebase en tiempo real
    private void subirLatLongFireBare(){
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            Log.e("Longitud: ", + location.getLatitude() + "longitud: " + location.getLongitude());
                            Map<String,Object> latlang =new HashMap<>();
                            latlang.put("latitud",location.getLatitude());
                            latlang.put("longitud",location.getLongitude());
                            mDatabase.child("usuarios").push().setValue(latlang);
                        }
                    }
                });
    }

}
