package de.miroit.deis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import de.miroit.deis.helper.Utils;

public class ErrorActivity extends AppCompatActivity {

	public final static String INTENT_MESSAGE = "msg";
	public final static String INTENT_STACKTRACE = "stacktrace";

	public static void handleExceptionAndCloseCurrentActivity(AppCompatActivity activity, Exception e) {
		e.printStackTrace();
		String stacktrace = stacktraceToString(e);
		startErrorActivity(activity, e, stacktrace);
		activity.finish();
	}

	private static String stacktraceToString(Exception e) {
		Writer writer = new StringWriter();
		e.printStackTrace(new PrintWriter(writer));
		return writer.toString();
	}

	private static void startErrorActivity(AppCompatActivity activity, Exception e, String stacktrace) {
		Intent intent = new Intent(activity, ErrorActivity.class);
		intent.putExtra(INTENT_MESSAGE, e.getMessage());
		intent.putExtra(INTENT_STACKTRACE, stacktrace);
		activity.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_error);
		Utils.setStatusBarBlack(this);

		String headline = getIntent().getStringExtra(INTENT_MESSAGE);
		String stacktrace = getIntent().getStringExtra(INTENT_STACKTRACE);

		TextView headlineTv = (TextView) findViewById(R.id.headline);
		TextView stacktraceTv = (TextView) findViewById(R.id.stacktrace);

		headlineTv.setText(headline);
		stacktraceTv.setText(stacktrace);
	}


}
