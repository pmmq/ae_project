package choice.android.com.project;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class EndActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.soundendgame);
        mp.start();
        ((ImageView)findViewById(R.id.btn_back)).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View pView)
            {
                Intent intent = new Intent(EndActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}
