package tp.edu.musicstream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class PlaylistPage extends AppCompatActivity {

    ImageButton btnHome;
    ImageButton btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_page);

        btnHome = findViewById(R.id.taskbarHome);
        btnHome.setOnClickListener(view -> openHome());

        btnSearch = findViewById(R.id.taskbarSearch);
        btnSearch.setOnClickListener(view -> openSearch());
    }
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(PlaylistPage.this, MainActivity.class);
        startActivity(intent);
    }

    public void openHome()
    {
        Intent intent = new Intent(PlaylistPage.this, MainActivity.class);
        startActivity(intent);
    }

    public void openSearch()
    {
        Intent intent = new Intent(PlaylistPage.this, SearchPage.class);
        startActivity(intent);
    }

    public void StudyPlaylist(View view)
    {
        Intent intent = new Intent(this, StudyPlaylist.class);
        startActivity(intent);
    }

    public void ChillPlaylist(View view)
    {
        Intent intent = new Intent(this, ChillPlaylist.class);
        startActivity(intent);
    }

    public void SchoolPlaylist(View view)
    {
        Intent intent = new Intent(this, SchoolPlaylist.class);
        startActivity(intent);
    }
}