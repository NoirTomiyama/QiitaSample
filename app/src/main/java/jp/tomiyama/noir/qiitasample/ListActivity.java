package jp.tomiyama.noir.qiitasample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.squareup.moshi.Moshi;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ListActivity extends AppCompatActivity {

    private QiitaService qiitaService;
    private Retrofit retrofit;

    ItemAdapter mItemAdapter;
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mListView = findViewById(R.id.listView);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        Moshi moshi = new Moshi.Builder().build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://qiita.com/api/v2/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(okHttpClient)
                .build();
        qiitaService = retrofit.create(QiitaService.class);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("USERNAME");

        getUserItems(userName);
    }

    private void getUserItems(String username){

        Call<List<ItemEntity>> mCall = qiitaService.getItems(username,1, 10);

        mCall.enqueue(new Callback<List<ItemEntity>>() {
            @Override
            public void onResponse(Call<List<ItemEntity>> call, Response<List<ItemEntity>> response) {

                List<ItemEntity> itemEntities = response.body();

                if(itemEntities!=null){
                    mItemAdapter = new ItemAdapter(getApplicationContext(),R.layout.item,itemEntities);
                    mListView.setAdapter(mItemAdapter);
                    for (ItemEntity itemEntity : itemEntities) {
//                        Log.d("title:", itemEntity.getTitle());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ItemEntity>> call, Throwable t) {

            }
        });
    }
}
