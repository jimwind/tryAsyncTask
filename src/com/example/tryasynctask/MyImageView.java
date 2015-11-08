package com.example.tryasynctask;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;

public class MyImageView extends ImageView {
	private IImageBitmapSetListener listener;
	private Context mContext;
	public MyImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		mContext = context;
	}
	public MyImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mContext = context;
	}
	public MyImageView(Context context) {
		super(context);
		mContext = context;
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
    public void setImageBitmap(Bitmap bm) {
        // if this is used frequently, may handle bitmaps explicitly
        // to reduce the intermediate drawable object
        super.setImageBitmap(bm);        
        listener.setHeight(bm.getHeight(), bm.getWidth());
        
        
    }
	public void setImageBitmapSetListener(IImageBitmapSetListener listener) {
		this.listener = listener;
	}
	public interface IImageBitmapSetListener{
		public void setHeight(int height, int width);
	}
}
