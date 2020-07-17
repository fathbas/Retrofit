package com.fatihb.retrofir.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.fatihb.retrofir.R;
import com.fatihb.retrofir.adaptor.RecyleViewAdapter;
import com.fatihb.retrofir.model.Cripto;
import com.fatihb.retrofir.service.CriptoApı;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ArrayList<Cripto> criptos;
    private String BASE_URL = "https://api.nomics.com/v1/";
    Retrofit retrofit;
    RecyclerView recyclerView;
    RecyleViewAdapter recyleViewAdapter;

    CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //https://api.nomics.com/v1/prices?key=c6bcc529a5e464f5556459b5e41bcb44
        recyclerView = findViewById(R.id.recyclerView);

        //RETROFİT AND JSON
        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        loadData();
    }

    private void loadData(){
       final CriptoApı criptoApı = retrofit.create(CriptoApı.class);

       compositeDisposable = new CompositeDisposable();
       compositeDisposable.add(criptoApı.getData()
       .subscribeOn(Schedulers.io())
       .observeOn(AndroidSchedulers.mainThread())
       .subscribe(this::handleRes));

/*
        Call<List<Cripto>> call = criptoApı.getData();

        call.enqueue(new Callback<List<Cripto>>() {
            @Override
            public void onResponse(Call<List<Cripto>> call, Response<List<Cripto>> response) {
                if (response.isSuccessful()){
                    List<Cripto> responseList=response.body();
                    criptos = new ArrayList<>(responseList);

                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyleViewAdapter = new RecyleViewAdapter(criptos);
                    recyclerView.setAdapter(recyleViewAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<Cripto>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
            }
        });
 */
    }

    private void handleRes(List<Cripto> responseList){
        criptos = new ArrayList<>(responseList);

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyleViewAdapter = new RecyleViewAdapter(criptos);
        recyclerView.setAdapter(recyleViewAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
