package tp.edu.musicstream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class LogInAinSong extends AppCompatActivity {

    private ImageButton btnCancel;
    private EditText eName;
    private EditText ePassword;
    private Button eLogin;
    private TextView eAttemptsInfo;

    private String Email = "a";
    private String Password = "12345";

    boolean isValid = false;
    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        btnCancel = (ImageButton) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cancel();
            }
        });

        eName = findViewById(R.id.etName);
        ePassword = findViewById(R.id.etPassword);
        eLogin = findViewById(R.id.btnLogin);
        eAttemptsInfo = findViewById(R.id.tvAttemptsInfo);

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String inputName = eName.getText().toString();
                String inputPassword = ePassword.getText().toString();

                if (inputName.isEmpty() || inputPassword.isEmpty())
                {
                    Toast.makeText(LogInAinSong.this, "Please enter all the required details correctly!", Toast.LENGTH_SHORT).show();
                }else{

                    isValid = validate(inputName, inputPassword);

                    if (!isValid){

                        counter--;

                        Toast.makeText(LogInAinSong.this, "Incorrect credentials entered!", Toast.LENGTH_SHORT).show();

                        eAttemptsInfo.setText("Number of attempts left: " + counter);

                        if (counter == 0){
                            eLogin.setEnabled(false);
                        }

                    }else{

                        Toast.makeText(LogInAinSong.this, "Login successful!", Toast.LENGTH_SHORT).show();

                        // Add the code to go to new activity
                        Intent intent = new Intent(LogInAinSong.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }

            }
        });
    }

    public void Cancel() {
        Intent intent = new Intent(this, LoginSignup.class);
        startActivity(intent);
    }

    private boolean validate(String name, String Password){

        if (name.equals(Email) && Password.equals(Password)){
            return true;
        }

        return false;
    }
}