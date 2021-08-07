package tp.edu.musicstream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    ImageButton btnPlaylists;
    ImageButton btnSearch;
    ImageButton search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlaylists = findViewById(R.id.taskbarPlaylist);
        btnPlaylists.setOnClickListener(view -> openPlaylists());

        btnSearch = findViewById(R.id.taskbarSearch);
        btnSearch.setOnClickListener(view -> openSearch());

        search = findViewById(R.id.search);
        search.setOnClickListener(view -> openSearch());

        ImageButton ButtonStudy = (ImageButton) findViewById(R.id.btnStudy);
        ImageButton ButtonChill = (ImageButton) findViewById(R.id.btnChill);
        ImageButton ButtonSchool = (ImageButton) findViewById(R.id.btnSchool);
        ImageButton ButtonPlaylists = (ImageButton) findViewById(R.id.btnPlaylists);

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

        ButtonPlaylists.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PlaylistPage.class);
                startActivity(intent);
            }
        });

    }

    public void openPlaylists()
    {
        Intent intent = new Intent(MainActivity.this, PlaylistPage.class);
        startActivity(intent);
    }

    public void openSearch()
    {
        Intent intent = new Intent(MainActivity.this, SearchPage.class);
        startActivity(intent);
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LoginSignup.class));
        finish();
    }

}