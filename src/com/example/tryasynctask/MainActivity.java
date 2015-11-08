package com.example.tryasynctask;

import com.example.tryasynctask.MyImageView.IImageBitmapSetListener;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	private EditText editText;
	private Button button;
	private ProgressBar progressBar;
	private MyImageView imageView;
	private RelativeLayout layout;
//	private static final String URL_STRING = "http://photocdn.sohu.com/20110927/Img320705637.jpg";
	private static final String URL_STRING = "http://photocdn.sohu.com/20151107/mp40136742_1446865645708_2_th_fv23.jpeg";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		editText = (EditText) findViewById(R.id.edittext);
		editText.setText(URL_STRING);
		button = (Button) findViewById(R.id.button);
		progressBar = (ProgressBar) findViewById(R.id.progress);
		imageView = (MyImageView) findViewById(R.id.imageview);
		button.setOnClickListener(new ButtonListener());
		
		layout = (RelativeLayout)findViewById(R.id.imageview_layout);
	}

	class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			MyAsyncTask mTask = new MyAsyncTask(imageView, progressBar, button);
			mTask.execute(URL_STRING);
			imageView.setImageBitmapSetListener(new IImageBitmapSetListener(){

				@Override
				public void setHeight(int height, int width) {
					// TODO Auto-generated method stub
					Log.e("GaoMi","async set bitmap ("+height +"/"+width+")");
					RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) layout.getLayoutParams();
					//如果在xml中设置了match_parent，则这里取值一定是负的，因此需要取屏幕宽
					if(rlp.width == RelativeLayout.LayoutParams.MATCH_PARENT){
						DisplayMetrics dm =new DisplayMetrics();
						getWindowManager().getDefaultDisplay().getMetrics(dm);
						int screenWidth = dm.widthPixels ;
						if(screenWidth < width){
							rlp.height = (int)(((float)screenWidth/(float)width)*height);
							layout.setLayoutParams(rlp);
						}
					} else if (rlp.width < width){
						rlp.height = (int)(((float)rlp.width/(float)width)* height);
					    layout.setLayoutParams(rlp);
					}
				}				
			});
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
