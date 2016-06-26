package choice.android.com.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import choice.android.com.project.helper.ChoicesHelper;

public class MainActivity extends AppCompatActivity
{
    Button mPlayButton;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPlayButton = (Button)findViewById(R.id.btn_play_game);
        mPlayButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View pView)
            {
                Intent intent = new Intent(MainActivity.this,GameSelectActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}
