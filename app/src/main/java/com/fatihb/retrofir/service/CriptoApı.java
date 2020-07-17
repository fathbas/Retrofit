package com.fatihb.retrofir.service;

import android.media.MediaCodec;

import com.fatihb.retrofir.model.Cripto;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CriptoApı
{
    //https://api.nomics.com/v1/prices?key=c6bcc529a5e464f5556459b5e41bcb44
    @GET("prices?key=c6bcc529a5e464f5556459b5e41bcb44")
    Observable<List<Cripto>> getData();
    //Call<List<Cripto>> getData();
}
