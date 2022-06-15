package com.ua.eventsfinder.Objetos;


import com.google.gson.Gson;

import ru.blizzed.opensongkick.ApiCallException;
import ru.blizzed.opensongkick.ApiCaller;
import ru.blizzed.opensongkick.ApiErrorException;
import ru.blizzed.opensongkick.OpenSongKickContext;
import ru.blizzed.opensongkick.SongKickApi;
import ru.blizzed.opensongkick.params.SongKickParams;

public class ThreadExecutarPedido implements Runnable
{

    private ApiCaller<Object> apiCaller;

    public ThreadExecutarPedido(ApiCaller<Object> apiCaller) {
        this.apiCaller = apiCaller;

    }



    public void run() {
        OpenSongKickContext.initialize("lKLDro9R9AqqXm1b");
        try {
            SongKickApi.eventSearch()
                    .byLocation(SongKickParams.LOCATION_GEO.of(41.06062, -8.588943333333333))
                    .execute()
                    .getResults()
                    .forEach(evento -> System.out.println((new Gson()).toJson(evento)));
        } catch (ApiCallException e) {
            e.printStackTrace();
        } catch (ApiErrorException e) {
            e.printStackTrace();
        }
    }

}
