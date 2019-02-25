package jp.tomiyama.noir.qiitasample;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.moshi.Moshi;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MainActivity extends AppCompatActivity {

    private QiitaService qiitaService;
    private Retrofit retrofit;

    private Button button;
    private Button button2;
    private EditText editText;
    private TextView textView;

    private Integer integer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        Moshi moshi = new Moshi.Builder().build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://qiita.com/api/v2/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(okHttpClient)
                .build();
        qiitaService = retrofit.create(QiitaService.class);

        button = findViewById(R.id.button);
        button.setOnClickListener(onClickListener);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        button2 = findViewById(R.id.button2);
        button2.setEnabled(false);

        button2.setOnClickListener(onClickListener2);


    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            // キーボードが出てたら閉じる
            InputMethodManager im = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

            //EditTextから文字列を取得
            String username = editText.getText().toString();

            Call<User> mCall = qiitaService.getUser(username);

            mCall.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {

                    User mUser = response.body();

                    if(mUser!=null){
                        Log.d("followees_count", String.valueOf(mUser.getFolloweesCount()));
                        Log.d("followers_count", String.valueOf(mUser.getFollowersCount()));
                        Log.d("id",mUser.getId());

                        textView.setText(mUser.showDetail());

                        button2.setEnabled(true);
                    }

                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });

        }
    };


    private View.OnClickListener onClickListener2 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            //EditTextから文字列を取得
            String username = editText.getText().toString();

            // ListViewのアクティビティに移動する
            Intent intent = new Intent(getApplicationContext(),ListActivity.class);
            // editTextに含まれた文字列を送信する
            intent.putExtra("USERNAME",username);

            startActivity(intent);

        }
    };

}

