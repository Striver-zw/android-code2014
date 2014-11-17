package com.Striver.ipcontent;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private EditText ed ;
	private TextView tv;
	private SharedPreferences sp;//�Ѻ��������
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ed = (EditText) findViewById(R.id.haoma);
		sp = getSharedPreferences("config", MODE_PRIVATE);
		ed.setText(sp.getString("ipnumber", ""));//Ĭ��Ϊ��
		tv = (TextView) findViewById(R.id.tv);
		//ע��㲥������java����  
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);  
        //�����㲥�����߶���  
        BatteryReceiver batteryReceiver = new BatteryReceiver();  
          
        //ע��receiver  
        registerReceiver(batteryReceiver, intentFilter); 
	}

	/**
	 * 
	 * @param view
	 */
	public void bo(View view){
		String haoma = ed.getText().toString().trim();
		//�����ļ���
		Editor editor = sp.edit();//�õ��༭��
		editor.putString("ipnumber", haoma);
		editor.commit();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * �㲥
	 * @author Striver
	 * Date 2014-5-6
	 */
	class BatteryReceiver extends BroadcastReceiver{  
		  
        @Override  
        public void onReceive(Context context, Intent intent) {  
            // TODO Auto-generated method stub  
            //�ж����Ƿ���Ϊ�����仯��Broadcast Action  
            if(Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())){  
                //��ȡ��ǰ����  
                int level = intent.getIntExtra("level", 0);  
                //�������̶ܿ�  
                int scale = intent.getIntExtra("scale", 100);  
                //����ת�ɰٷֱ�  
                tv.setText("��ص���Ϊ"+((level*100)/scale)+"%");  
            }  
        }
	}

}
