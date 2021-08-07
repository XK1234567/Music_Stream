package tp.edu.musicstream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class LoginSignup extends AppCompatActivity {

    private ImageButton btnSignup;
    private ImageButton btmLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        btnSignup = (ImageButton) findViewById(R.id.SignUp);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                openSignup();
            }
        });
        btmLogin = (ImageButton) findViewById(R.id.LogIn);
        btmLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                openLogin();
            }
        });


    }

    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(LoginSignup.this, LoginSignup.class);
        startActivity(intent);
        finish();
    }

    public void openSignup() {
        Intent intent = new Intent(this, SignUpAinSong.class);
        startActivity(intent);
    }
    public void openLogin() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

}