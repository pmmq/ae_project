package choice.android.com.project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import choice.android.com.project.manager.GameManager;

public class GameActivity extends AppCompatActivity
{
    GameManager mGameManager;
    GameManager.Language mLanguage = GameManager.Language.TH;

    LinearLayout ll_container;
    ImageView    iv_question;
    Button[]     btn_th;
    Button[]     btn_en;

    int[] btn_th_id;
    int[] btn_en_id;

    public static String LANG_KEY = "language";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            mLanguage = (GameManager.Language) bundle.getSerializable(LANG_KEY);
        }
        mGameManager = new GameManager(this, mLanguage);
        bindView();
    }

    private void bindView()
    {
        ll_container = (LinearLayout) findViewById(R.id.ll_container);
        iv_question = (ImageView) findViewById(R.id.iv_question);
        btn_th = new Button[8];
        btn_en = new Button[4];
        btn_th_id = getResources().getIntArray(R.array.btn_name_th);
        btn_en_id = getResources().getIntArray(R.array.btn_name_en);
        int[] btnDrawableResId = mGameManager.getCurrentResurceAnswer();
        switch (mLanguage){
            case TH :
                for (int i = 0; i < btn_th_id.length; i++)
                {
                    btn_th[i] = (Button) findViewById(btn_th_id[i]);
                    btn_th[i].setBackground(getResources().getDrawable(btnDrawableResId[i]));
                }
                break;
            case EN :
                for (int i = 0; i < btn_en_id.length; i++)
                {
                    btn_en[i] = (Button) findViewById(btn_en_id[i]);
                    btn_en[i].setBackground(getResources().getDrawable(btnDrawableResId[i]));
                }
                break;
        }
    }

}
