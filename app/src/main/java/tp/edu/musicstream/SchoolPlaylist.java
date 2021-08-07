package tp.edu.musicstream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class SchoolPlaylist extends AppCompatActivity {

    ImageButton btnHome;
    ImageButton btnSearch;
    ImageButton btnPlaylist;
    SongCollection songCollection = new SongCollection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_playlist);

        btnHome = (ImageButton) findViewById(R.id.TaskbarHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHome();
            }
        });
        btnSearch = (ImageButton) findViewById(R.id.TaskbarSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSearch();
            }
        });
        btnPlaylist = (ImageButton) findViewById(R.id.TaskbarPlaylist);
        btnPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPlaylists();
            }
        });
    }
    public void openHome()
    {
        Intent intent = new Intent(SchoolPlaylist.this, MainActivity.class);
        startActivity(intent);
    }

    public void openSearch()
    {
        Intent intent = new Intent(SchoolPlaylist.this, SearchPage.class);
        startActivity(intent);
    }

    public void openPlaylists()
    {
        Intent intent = new Intent(SchoolPlaylist.this, PlaylistPage.class);
        startActivity(intent);
    }

    public void sendDataToActivity(int index)
    {
        Intent intent = new Intent(this, PlaySongActivity.class);
        intent.putExtra("index", index);
        startActivity(intent);
    }

    public void handleSelection(View myView)
    {
        String resourceId = getResources().getResourceEntryName(myView.getId());
        int currentArrayIndex = songCollection.searchSongById(resourceId);
        Log.d("temasek", "The id of the pressed ImageButton is: " + resourceId);
        sendDataToActivity(currentArrayIndex);
    }

}