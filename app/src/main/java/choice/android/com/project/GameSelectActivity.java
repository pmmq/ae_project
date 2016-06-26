package choice.android.com.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import choice.android.com.project.manager.GameManager;

public class GameSelectActivity extends AppCompatActivity implements View.OnClickListener
{
    Button btn_th_choice;
    Button btn_en_choice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_select);
        btn_th_choice = (Button) findViewById(R.id.btn_th_choice);
        btn_en_choice = (Button) findViewById(R.id.btn_en_choice);
        btn_th_choice.setOnClickListener(this);
        btn_en_choice.setOnClickListener(this);
    }

    @Override
    public void onClick(View pView)
    {
        Intent intent = new Intent(this,GameActivity.class);
        Bundle bundle = new Bundle();
        GameManager.Language language = GameManager.Language.TH;
        switch (pView.getId()){
            case R.id.btn_th_choice :
                language = GameManager.Language.TH;
                break;
            case R.id.btn_en_choice :
                language = GameManager.Language.EN;
                break;
        }
        bundle.putSerializable(GameActivity.LANG_KEY,language);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}