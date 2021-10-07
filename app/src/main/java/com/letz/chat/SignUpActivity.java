package com.letz.chat;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpActivity extends AppCompatActivity
{
    public CircleImageView ivCircle;
    public TextInputEditText email, password, name;
    public Button register;
    public boolean imageControl = false;
    public FirebaseAuth auth;
    public FirebaseUser user;

    FirebaseDatabase database;
    DatabaseReference dbReference;

    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ivCircle = findViewById(R.id.ivCircle_Signup);
        email = findViewById(R.id.etEmail_Signup);
        password = findViewById(R.id.etPassword_Signup);
        name = findViewById(R.id.etUserName_Signup);
        register = findViewById(R.id.btnRegister_Signup);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        database = FirebaseDatabase.getInstance();
        dbReference = database.getReference();

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

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
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        launchLoadImage.launch(i);
        finish();
    }

    ActivityResultLauncher<Intent> launchLoadImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>()
            {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        imageUri = data.getData();
                        Picasso.get().load(imageUri).into(ivCircle);

                        imageControl = true;
                    } else {
                        imageControl = false;
                    }
                }
            }
    );

    //
    public void signup(String email, String password, String userName) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            dbReference.child("Users").child(auth.getUid()).child("userName").setValue(userName);

                            if (imageControl)//이미지 선택되었을 경우
                            {
                                UUID randomID = UUID.randomUUID();
                                String imageName = "image/" + randomID + ".jpg";
                                storageReference.child(imageName).putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
                                {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        StorageReference storageReference_imageName = firebaseStorage.getReference(imageName);
                                        storageReference_imageName.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
                                        {
                                            @Override
                                            public void onSuccess(Uri uri) {
                                                String filePath = uri.toString();
                                                dbReference.child("Users").child(auth.getUid()).child("image")
                                                        .setValue(filePath).addOnSuccessListener(new OnSuccessListener<Void>()
                                                {
                                                    @Override
                                                    public void onSuccess(Void unused) {
//                                                        Toast.makeText(SignUpActivity.this, "write to db is successful", Toast.LENGTH_SHORT).show();
                                                    }
                                                }).addOnFailureListener(new OnFailureListener()
                                                {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
//                                                        Toast.makeText(SignUpActivity.this, "write to db is not successful", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                            }
                                        });

                                    }
                                });

                            } else // null 넣어준다.
                            {
//                                dbReference.child("Users").child(auth.getUid()).child("image").setValue("null");
                            }

                            Intent igoMain = new Intent(SignUpActivity.this, MainActivity.class);
                            igoMain.putExtra("userName", userName);
                            startActivity(igoMain);
                            finish();
                        } else {
//                            Toast.makeText(SignUpActivity.this, "Failed SignUp", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}






