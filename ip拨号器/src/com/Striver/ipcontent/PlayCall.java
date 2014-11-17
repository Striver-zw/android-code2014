package com.Striver.ipcontent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
/**
 * 广播接收者
 * 需要配置
 * @author Striver
 * Date 2014-5-6
 */
public class PlayCall extends BroadcastReceiver {

	//android:name="android.intent.action.NEW_OUTGOING_CALL"
	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("有了~~~~~~~~~~~~~~");
		String number = getResultData();//获取外拨电话
		System.out.println("~~~~~~~~~~~~"+number);
		//替换号码
		SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		String ipnu= sp.getString("ipnumber", "");
		
		String ipnewnun = ipnu+number;
		setResultData(ipnewnun);
	}

}
