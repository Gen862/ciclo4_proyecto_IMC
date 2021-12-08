package com.example.appentrega;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button registrar,ingreso;
    private String emailtexto,passwordtexto;
    private final String TAG = "OJO@ log Autentication";
    EditText password, mail;
    Datos datoPersona=Datos.getInstance();
    public MainActivity() {    }

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

}
