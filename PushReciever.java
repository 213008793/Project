package com.example.pc.myapplication;

import com.backendless.push.BackendlessBroadcastReceiver;
import com.backendless.push.BackendlessPushService;

/**
 * Created by PC on 8/10/2018.
 */

public class PushReciever extends BackendlessBroadcastReceiver
{
    @Override
    public Class<? extends BackendlessPushService> getServiceClass()
    {
        return PushService.class;
    }
}
