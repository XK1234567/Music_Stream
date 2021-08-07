package tp.edu.musicstream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton ButtonStudy = (ImageButton) findViewById(R.id.btnStudy);
        ImageButton ButtonChill = (ImageButton) findViewById(R.id.btnChill);
        ImageButton ButtonSchool = (ImageButton) findViewById(R.id.btnSchool);

        ButtonStudy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StudyPlaylist.class);
                startActivity(intent);
            }
        });

        ButtonChill.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChillPlaylist.class);
                startActivity(intent);
            }
        });

        ButtonSchool.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SchoolPlaylist.class);
                startActivity(intent);
            }
        });

    }

}