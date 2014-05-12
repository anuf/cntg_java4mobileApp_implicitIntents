package anuf.exemplo.implicitintents;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		private Button btnBrowser;
		private EditText etBrowser;
		private EditText etPhone;
		private Button btnSMS;
		private EditText etTextToSend;
		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			
			etBrowser = (EditText) rootView.findViewById(R.id.etURIbrowser);
			btnBrowser = (Button) rootView.findViewById(R.id.btnBrowser);
			etPhone =   (EditText) rootView.findViewById(R.id.etPhone);
			etTextToSend = (EditText) rootView.findViewById(R.id.etTextTosend);
			btnSMS = (Button) rootView.findViewById(R.id.btnSMS);
			
			
			btnBrowser.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					String url = etBrowser.getText().toString();
					Intent unIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
					if(unIntent.resolveActivity(getActivity().getPackageManager()) != null){
						startActivity(unIntent);
					}
					
				}
			});
			
			btnSMS.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					String texto = etTextToSend.getText().toString();
					String phone = etPhone.getText().toString(); // emulator number if no real device is running
					Intent unIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"+phone));
					unIntent.putExtra("sms_body",texto); // If we want to send some pre-loaded text
					if(unIntent.resolveActivity(getActivity().getPackageManager()) != null){
						startActivity(unIntent);
					}
					
				}
			});
			
			return rootView;
		}
	}

}
