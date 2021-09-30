package com.letz.chat;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpActivity extends AppCompatActivity
{
    private CircleImageView ivCircle;
    private TextInputEditText email, password, name;
    private Button register;
    boolean imageControl = false;
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ivCircle = findViewById(R.id.ivCircle);
        email = findViewById(R.id.etEmailSignUp);
        password = findViewById(R.id.etPasswordSignUp);
        name = findViewById(R.id.etUserNameSignUp);
        register = findViewById(R.id.btnRegister);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        ivCircle.setOnClickListener(v -> {
            imageChooser();
        });

        register.setOnClickListener(v -> {
            String userEmail = email.getText().toString();
            String userPassword = password.getText().toString();
            String userName = name.getText().toString();

            if (!userEmail.equals("") && !userPassword.equals("") && !userName.equals("")) {
                signup(userEmail, userPassword, userName);
            }
        });

    }

    public void imageChooser() {
        //image 열기
        Intent i = new Intent();
        i.setType("images/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        launchLoadImage.launch(i);
    }

    ActivityResultLauncher<Intent> launchLoadImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>()
            {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        Uri imageUri = data.getData();
                        Picasso.get().load(imageUri).into(ivCircle);
                        imageControl = true;
                    } else {
                        imageControl = false;
                    }
                }
            }
    );



}






