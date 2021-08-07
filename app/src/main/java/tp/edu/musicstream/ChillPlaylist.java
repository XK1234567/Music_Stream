package tp.edu.musicstream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ChillPlaylist extends AppCompatActivity {

    private ImageButton buttonHome;
    private ImageButton buttonSearch;
    private ImageButton buttonPlaylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chill_playlist);

        buttonHome = (ImageButton) findViewById(R.id.TaskbarHome);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHome();
            }
        });
        buttonSearch = (ImageButton) findViewById(R.id.TaskbarSearch);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSearch();
            }
        });
        buttonPlaylist = (ImageButton) findViewById(R.id.TaskbarPlaylist);
        buttonPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPlaylist();
            }
        });

    }

    public void openHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void openSearch() {
        Intent intent = new Intent(this, SearchPage.class);
        startActivity(intent);
    }
    public void openPlaylist() {
        Intent intent = new Intent(this, PlaylistPage.class);
        startActivity(intent);
    }

}