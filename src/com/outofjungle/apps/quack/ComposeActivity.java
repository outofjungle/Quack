package com.outofjungle.apps.quack;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.loopj.android.http.AsyncHttpResponseHandler;

public class ComposeActivity extends Activity {

	private ProgressDialog dialog;
	private Button btnTweet;
	private EditText etCompose;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose);
		setupViews();
		
		btnTweet.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				dialog = ProgressDialog.show(ComposeActivity.this, "", "tweeting...");
				String tweetText = etCompose.getText().toString();

				QuackApp.getRestClient().postTweet(tweetText, new AsyncHttpResponseHandler(){					
					@Override
					public void onSuccess(int arg0, String arg1) {
						dialog.dismiss();
						ComposeActivity.this.finish();
					}
					
					@Override
					public void onFailure(Throwable arg0, String arg1) {
						dialog.dismiss();
		    		}
				});
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compose, menu);
		return true;
	}
	
	private void setupViews() {
		btnTweet = (Button) findViewById(R.id.btnTweet);
		etCompose = (EditText) findViewById(R.id.etCompose);
	}

	public void onCancel(View v) {
		ComposeActivity.this.finish();
	}

	public void hideSoftKeyboard(View view){
	  InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
	  imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}
	
	
}
