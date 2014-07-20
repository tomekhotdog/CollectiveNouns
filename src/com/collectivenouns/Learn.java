package com.collectivenouns;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Learn extends Activity {
	
	private int NOUN_NUMBER = 0;
	private int NOUN_STATUS = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_learn);
		Bundle extras = getIntent().getExtras();
		NOUN_NUMBER = extras.getInt("NounNumber");
		setTextBoxes(NOUN_NUMBER);
		checkAorAn(NOUN_NUMBER);
	}
	
	public void NextNoun(View v) {
		if (NOUN_STATUS < 7) {
			NOUN_STATUS++;
			setProgressBoxesEmpty();
			setProgressBoxes(NOUN_STATUS);
			setTextBoxes(NOUN_NUMBER + NOUN_STATUS);
			checkAorAn(NOUN_NUMBER + NOUN_STATUS);
		} else {
			finish();
		}
	}
	
	public void PreviousNoun(View v) {
		if (NOUN_STATUS > 0) {
			NOUN_STATUS--;
			setProgressBoxesEmpty();
			setProgressBoxes(NOUN_STATUS);
			setTextBoxes(NOUN_NUMBER + NOUN_STATUS);
			checkAorAn(NOUN_NUMBER + NOUN_STATUS);
		}
	}
	
	public void quit(View v) {
		finish();
	}
	
	public void testOut(View v) {
		Intent intent_test = new Intent(getApplicationContext(), Test.class);
		intent_test.putExtra("NounNumber", NOUN_NUMBER);
		startActivity(intent_test);
	}
			
	private void setTextBoxes(int i) {
		TextView group = (TextView) findViewById(R.id.group);
		group.setText(NounBase.groups[i]);
		TextView collective = (TextView) findViewById(R.id.collective_noun);
		collective.setText(NounBase.collectives[i]);
	}
	
	private void checkAorAn(int i) {
		char l = getResources().getString(NounBase.collectives[i]).charAt(0);
		TextView an = (TextView) findViewById(R.id.a);
		if (l == 'a' || l == 'e' || l == 'i' || l == 'o' || l == 'u') {			
			an.setText(R.string.An);
		} else {
			an.setText(R.string.A);
		}
	}
	
	private void setProgressBoxes(int i) {
			switch(i) {
			case 7: ImageView progress8 = (ImageView) findViewById(R.id.progress8);
					progress8.setImageResource(R.drawable.progressboxfilled);
			case 6: ImageView progress7 = (ImageView) findViewById(R.id.progress7);
					progress7.setImageResource(R.drawable.progressboxfilled);
			case 5: ImageView progress6 = (ImageView) findViewById(R.id.progress6);
					progress6.setImageResource(R.drawable.progressboxfilled);
			case 4: ImageView progress5 = (ImageView) findViewById(R.id.progress5);
					progress5.setImageResource(R.drawable.progressboxfilled);
			case 3: ImageView progress4 = (ImageView) findViewById(R.id.progress4);
					progress4.setImageResource(R.drawable.progressboxfilled);
			case 2: ImageView progress3 = (ImageView) findViewById(R.id.progress3);
					progress3.setImageResource(R.drawable.progressboxfilled);
			case 1: ImageView progress2 = (ImageView) findViewById(R.id.progress2);
					progress2.setImageResource(R.drawable.progressboxfilled);
			case 0: ImageView progress1 = (ImageView) findViewById(R.id.progress1);
					progress1.setImageResource(R.drawable.progressboxfilled);
			}			
		}
	
	private void setProgressBoxesEmpty() {
		ImageView progress8 = (ImageView) findViewById(R.id.progress8);
		progress8.setImageResource(R.drawable.progressbox);
		ImageView progress7 = (ImageView) findViewById(R.id.progress7);
		progress7.setImageResource(R.drawable.progressbox);
		ImageView progress6 = (ImageView) findViewById(R.id.progress6);
		progress6.setImageResource(R.drawable.progressbox);
		ImageView progress5 = (ImageView) findViewById(R.id.progress5);
		progress5.setImageResource(R.drawable.progressbox);
		ImageView progress4 = (ImageView) findViewById(R.id.progress4);
		progress4.setImageResource(R.drawable.progressbox);
		ImageView progress3 = (ImageView) findViewById(R.id.progress3);
		progress3.setImageResource(R.drawable.progressbox);
		ImageView progress2 = (ImageView) findViewById(R.id.progress2);
		progress2.setImageResource(R.drawable.progressbox);
		ImageView progress1 = (ImageView) findViewById(R.id.progress1);
		progress1.setImageResource(R.drawable.progressbox);
	}


}
