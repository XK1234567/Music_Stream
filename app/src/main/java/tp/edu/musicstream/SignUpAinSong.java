package tp.edu.musicstream;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpAinSong extends AppCompatActivity {

    private ImageButton btnCancel;
    EditText mFullName, mEmail, mPassword;
    Button mSignUpBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btnCancel = (ImageButton) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cancel();
            }
        });

        mFullName = findViewById(R.id.Name);
        mEmail = findViewById(R.id.EmailSignUp);
        mPassword = findViewById(R.id.PasswordSignUp);
        mSignUpBtn = findViewById(R.id.btnSignUp);

        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        if (fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),NewUserHome.class));
            finish();
        }


        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required !");
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    mPassword.setError("Password is Required !");
                    return;
                }

                if (password.length() < 6){
                    mPassword.setError("Password must be at least 6 characters long !");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                // register user in firebase

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(SignUpAinSong.this, "Successfully created account!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),NewUserHome.class));
                        }else{
                            Toast.makeText(SignUpAinSong.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

    public void Cancel() {
        Intent intent = new Intent(this, LoginSignup.class);
        startActivity(intent);
        finish();
    }

}