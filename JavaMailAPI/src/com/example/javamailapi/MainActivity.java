package com.example.javamailapi;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {

	EditText user;
	EditText pass;
	EditText subject;
	EditText body;
	EditText recipient;

	Button send;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		user = (EditText) findViewById(R.id.username);
		pass = (EditText) findViewById(R.id.password);
		subject = (EditText) findViewById(R.id.subject);
		body = (EditText) findViewById(R.id.body);
		recipient = (EditText) findViewById(R.id.recipient);

		send = (Button) findViewById(R.id.send);
		send.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.send) {
			String[] recp = { "1234@qq.com" };
			SendEmailAsyncTask email = new SendEmailAsyncTask();
			email.m = new Mail("nbls88888888@163.com", "xxxxx");
			email.m.set_from("nbls88888888@163.com");
			email.m.setBody(body.getText().toString());
			email.m.set_to(recp);
			email.m.set_subject("¥ÌŒÛ»’÷æ");
			email.execute();
		}
	}

}

class SendEmailAsyncTask extends AsyncTask<Void, Void, Boolean> {
	Mail m;

	public SendEmailAsyncTask() {
		if (BuildConfig.DEBUG)
			Log.v(SendEmailAsyncTask.class.getName(), "SendEmailAsyncTask()");

	}

	@Override
	protected Boolean doInBackground(Void... params) {
		if (BuildConfig.DEBUG)
			Log.v(SendEmailAsyncTask.class.getName(), "doInBackground()");
		try {
			m.send();
			return true;
		} catch (AuthenticationFailedException e) {
			Log.e(SendEmailAsyncTask.class.getName(), "Bad account details");
			e.printStackTrace();
			return false;
		} catch (MessagingException e) {
			Log.e(SendEmailAsyncTask.class.getName(), m.get_to() + "failed");
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
