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
	private SharedPreferences sp;//把号码存下来
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ed = (EditText) findViewById(R.id.haoma);
		sp = getSharedPreferences("config", MODE_PRIVATE);
		ed.setText(sp.getString("ipnumber", ""));//默认为空
		tv = (TextView) findViewById(R.id.tv);
		//注册广播接受者java代码  
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);  
        //创建广播接受者对象  
        BatteryReceiver batteryReceiver = new BatteryReceiver();  
          
        //注册receiver  
        registerReceiver(batteryReceiver, intentFilter); 
	}

	/**
	 * 
	 * @param view
	 */
	public void bo(View view){
		String haoma = ed.getText().toString().trim();
		//放入文件中
		Editor editor = sp.edit();//得到编辑器
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
	 * 广播
	 * @author Striver
	 * Date 2014-5-6
	 */
	class BatteryReceiver extends BroadcastReceiver{  
		  
        @Override  
        public void onReceive(Context context, Intent intent) {  
            // TODO Auto-generated method stub  
            //判断它是否是为电量变化的Broadcast Action  
            if(Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())){  
                //获取当前电量  
                int level = intent.getIntExtra("level", 0);  
                //电量的总刻度  
                int scale = intent.getIntExtra("scale", 100);  
                //把它转成百分比  
                tv.setText("电池电量为"+((level*100)/scale)+"%");  
            }  
        }
	}

}
