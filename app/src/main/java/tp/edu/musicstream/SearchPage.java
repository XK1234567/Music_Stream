package tp.edu.musicstream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class SearchPage extends AppCompatActivity {

    SongCollection songCollection = new SongCollection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
    }

    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(SearchPage.this, MainActivity.class);
        startActivity(intent);
    }

    public void Home(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void Playlists(View view)
    {
        Intent intent = new Intent(this, PlaylistPage.class);
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