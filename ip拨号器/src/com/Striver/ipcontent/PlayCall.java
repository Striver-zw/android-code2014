package com.Striver.ipcontent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
/**
 * �㲥������
 * ��Ҫ����
 * @author Striver
 * Date 2014-5-6
 */
public class PlayCall extends BroadcastReceiver {

	//android:name="android.intent.action.NEW_OUTGOING_CALL"
	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("����~~~~~~~~~~~~~~");
		String number = getResultData();//��ȡ�Ⲧ�绰
		System.out.println("~~~~~~~~~~~~"+number);
		//�滻����
		SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		String ipnu= sp.getString("ipnumber", "");
		
		String ipnewnun = ipnu+number;
		setResultData(ipnewnun);
	}

}
