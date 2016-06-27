package choice.android.com.project;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import choice.android.com.project.manager.GameManager;

public class GameActivity extends AppCompatActivity
{
    GameManager mGameManager;
    GameManager.Language mLanguage = GameManager.Language.TH;

    LinearLayout ll_container;
    LinearLayout ll_choices_container_th;
    LinearLayout ll_choices_container_en;
    ImageView    iv_question;
    ImageView[]  btn_th;
    ImageView[]  btn_en;
    ImageView    btn_back;
    TypedArray   btn_th_id;
    TypedArray   btn_en_id;

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

        btn_back = (ImageView) (findViewById(R.id.btn_back));
        btn_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View pView)
            {
                Intent intent = new Intent(GameActivity.this,GameSelectActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        ll_container = (LinearLayout) findViewById(R.id.ll_container);
        ll_container.setBackground(getResources().getDrawable(R.drawable.bg_game));
        bindView();
    }

    private void bindView()
    {

        iv_question = (ImageView) findViewById(R.id.iv_question);
        btn_th = new ImageView[8];
        btn_en = new ImageView[4];
        btn_th_id = getResources().obtainTypedArray(R.array.btn_name_th);
        btn_en_id = getResources().obtainTypedArray(R.array.btn_name_en);
        ll_choices_container_en = (LinearLayout) (findViewById(R.id.ll_choices_container_en));
        ll_choices_container_th = (LinearLayout) (findViewById(R.id.ll_choices_container_th));
        iv_question.setImageDrawable(getResources().getDrawable(mGameManager.getCurrentChoiceResource()));
        int[] btnDrawableResId = mGameManager.getCurrentResurceAnswer();
        switch (mLanguage)
        {
            case TH:
                ll_choices_container_th.setVisibility(View.VISIBLE);
                ll_choices_container_en.setVisibility(View.GONE);
                for (int i = 0; i < btn_th.length; i++)
                {
                    final int index = i;
                    btn_th[i] = (ImageView) findViewById(btn_th_id.getResourceId(i, -1));
                    btn_th[i].setBackgroundResource(btnDrawableResId[i]);
                    btn_th[i].setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View pView)
                        {
                            if (mGameManager.getCurrentCorrectIndex() == index)
                            {
                                mGameManager.nextChoice();
                                Toast.makeText(getApplicationContext(), getText(R.string.correct),
                                        Toast.LENGTH_LONG).show();
                                if (mGameManager.getCurrentChoiceIndex() < mGameManager.getQuestionSize())
                                {
                                    bindView();
                                } else
                                {
                                    Intent intent = new Intent(GameActivity.this, EndActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }
                            } else
                            {
                                Toast.makeText(getApplicationContext(), getText(R.string.wrong),
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                break;
            case EN:
                ll_choices_container_th.setVisibility(View.GONE);
                ll_choices_container_en.setVisibility(View.VISIBLE);
                for (int i = 0; i < btn_en.length; i++)
                {
                    final int index = i;
                    btn_en[i] = (ImageView) findViewById(btn_en_id.getResourceId(i, -1));
                    btn_en[i].setBackgroundResource(btnDrawableResId[i]);
                    btn_en[i].setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View pView)
                        {
                            if (mGameManager.getCurrentCorrectIndex() == index)
                            {
                                mGameManager.nextChoice();
                                Toast.makeText(getApplicationContext(), getText(R.string.correct),
                                        Toast.LENGTH_LONG).show();
                                if (mGameManager.getCurrentChoiceIndex() < mGameManager.getQuestionSize())
                                {
                                    bindView();
                                } else
                                {
                                    Intent intent = new Intent(GameActivity.this, EndActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }
                            } else
                            {
                                Toast.makeText(getApplicationContext(), getText(R.string.wrong),
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }
                break;
        }
    }

}
