package com.collectivenouns;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.SumPathEffect;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.view.KeyEvent;

public class Test extends Activity {
	
	private int NOUN_NUMBER;
	private int CURRENT_NOUN_INDEX;
	private int CURRENT_QUESTION = 0;
	private int QUESTION_TYPE = 0;
	private static final int PLATINUM_TRIANGLE_THRESHOLD = 15;
	private static final int GREEN_TRIANGLE_THRESHOLD = 12;
	private boolean gate = true;
	private boolean readyForNextQuestion = false;
	private boolean homeResourceReload = false;
	private int[] scores = new int[16];
	private Integer[] progress = {0, R.id.progress1, R.id.progress2, R.id.progress3,
			R.id.progress4, R.id.progress5, R.id.progress6, R.id.progress7, R.id.progress8,
			R.id.progress9, R.id.progress10, R.id.progress11, R.id.progress12, R.id.progress13,
			R.id.progress14, R.id.progress15 };
	Random random;
	SharedPreferences prefs;
	Editor editor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_test);
		Bundle extras = getIntent().getExtras();
		NOUN_NUMBER = extras.getInt("NounNumber");
		CURRENT_NOUN_INDEX = NOUN_NUMBER;
		initialiseScores();
		askQuestion();
		
	}
	
	private void askQuestion() {
		unsetReadyForNextQuestion();
		random = new Random();
		int q = random.nextInt(8);
		int option = random.nextInt(4) + 1;
		switch (option) {
		case 1: askQType1(NOUN_NUMBER + q);
				break;
		case 2: askQType2(NOUN_NUMBER + q);
				break;
		case 3: askQType3(NOUN_NUMBER + q);
			break;
		case 4: askQType4(NOUN_NUMBER + q);
			break;
		}
	}
	
	private void askQType1(int index) {
		QUESTION_TYPE = 1;
		CURRENT_NOUN_INDEX = index;
		setContentView(R.layout.layout_questiontype1);
		setProgressBoxes();
		TextView group = (TextView) findViewById(R.id.group);
		group.setText(NounBase.groups[index]);
		setBannerInvisible();
		setAnswerBoxes(index, 1);
	}
	
	private void askQType2(int index) {
		QUESTION_TYPE = 2;
		CURRENT_NOUN_INDEX = index;
		setContentView(R.layout.layout_questiontype2);
		setProgressBoxes();
		TextView group = (TextView) findViewById(R.id.collective_noun);
		group.setText(NounBase.collectives[index]);
		setBannerInvisible();
		setAnswerBoxes(index, 2);
	}

	private void askQType3(int index) {
		CURRENT_NOUN_INDEX = index;
     	setContentView(R.layout.layout_questiontype3);
     	setProgressBoxes();
		TextView group = (TextView) findViewById(R.id.group);
		group.setText(NounBase.groups[index]);
		setBannerInvisible();
		setTextEditorListener();
	}
	
	private void askQType4(int index) {
		CURRENT_NOUN_INDEX = index;
		setContentView(R.layout.layout_questiontype4);
		setProgressBoxes();
		TextView collective = (TextView) findViewById(R.id.collective_noun);
		collective.setText(NounBase.collectives[index]);
		setBannerInvisible();
		setTextEditorListener();
	}
	
	public void q1Check(View v) {
		switchCheck_NextText();
		TextView answerText = (TextView) findViewById(R.id.collective_noun);
		String answer = (String) answerText.getText();
		if (answer.equalsIgnoreCase(getResources().getString(NounBase.collectives[CURRENT_NOUN_INDEX]))) {
			correctAnswer(CURRENT_QUESTION);
		} else {
			incorrectAnswer(CURRENT_QUESTION);
		}
	}
	
	public void q2Check(View v) {
		switchCheck_NextText();
		TextView answerText = (TextView) findViewById(R.id.group);
		String answer = (String) answerText.getText();
		if (answer.equalsIgnoreCase(getResources().getString(NounBase.groups[CURRENT_NOUN_INDEX]))) {
			correctAnswer(CURRENT_QUESTION);
		} else {
			incorrectAnswer(CURRENT_QUESTION);
		}
	}
	
	public void q3Check(View v) {
		switchCheck_NextText();
		hideEditText();
		EditText answerText = (EditText) findViewById(R.id.answerText);
		String answer = answerText.getText().toString();
		if (answer.equalsIgnoreCase(getResources().getString(NounBase.collectives[CURRENT_NOUN_INDEX]))) {
			correctAnswer(CURRENT_QUESTION);
		} else {
			incorrectAnswer(CURRENT_QUESTION);
		}
	}
	
	public void q4Check(View v) {
		switchCheck_NextText();
		hideEditText();
		EditText answerText = (EditText) findViewById(R.id.answerText);
		String answer = answerText.getText().toString();
		if (answer.equalsIgnoreCase(getResources().getString(NounBase.groups[CURRENT_NOUN_INDEX]))) {
			correctAnswer(CURRENT_QUESTION);
		} else {
			incorrectAnswer(CURRENT_QUESTION);
		}
	}

	private void incorrectAnswer(int answer_index) {
		// Incorrect answer animation
		System.out.println("Incorrect");		
		if (readyForNextQuestion()) {
			CURRENT_NOUN_INDEX++;
			CURRENT_QUESTION++;
			if (CURRENT_QUESTION < 15) {
			askQuestion();
			} else {
			finishTest();
			}	
		} else {
			setIncorrectBanner();
			markIncorrectAnswer(answer_index);
			//setProgressBoxes();
			setCorrectAnswerText(CURRENT_NOUN_INDEX);
			setReadyForNextQuestion();
			
		}			
	}
	
	private void correctAnswer(int answer_index) {
		// Incorrect answer animation
		System.out.println("Correct");
		if (readyForNextQuestion()) {
			CURRENT_NOUN_INDEX++;
			CURRENT_QUESTION++;
			if (CURRENT_QUESTION < 15) {
			askQuestion();
			} else {
			finishTest();
			}	
		} else {
			setCorrectBanner();
			markCorrectAnswer(answer_index);
			//setProgressBoxes();
			setCorrectAnswerText(CURRENT_NOUN_INDEX);
			setReadyForNextQuestion();
			
		}			
	}
	
	private void setAnswerBoxes(int index, int questionType) {
		int answer_index = index - NOUN_NUMBER;
		random = new Random();
		int a1 = random.nextInt(8); int a2 = random.nextInt(8); 
		int a3 = random.nextInt(8); int a4 = random.nextInt(8);
		while (a1 == answer_index) {
			a1 = random.nextInt(8);
		}
		while (a2 == answer_index || a2 == a1) {
			a2 = random.nextInt(8);
		}
		while (a3 == answer_index || a3 == a1 || a3 == a2) {
			a3 = random.nextInt(8);
		}
		while (a4 == answer_index || a4 == a1 || a4 == a2 || a4 == a3) {
			a4 = random.nextInt(8);
		}
		Button b1 = (Button) findViewById(R.id.option1);
		Button b2 = (Button) findViewById(R.id.option2);
		Button b3 = (Button) findViewById(R.id.option3);
		Button b4 = (Button) findViewById(R.id.option4);
		Button[] bs = {b1, b2, b3, b4};
		//Selects and sets button text for 4 random answers - then prepares answer 
		if (questionType == 1) {
			b1.setText(NounBase.collectives[a1 + NOUN_NUMBER]);
			b2.setText(NounBase.collectives[a2 + NOUN_NUMBER]);
			b3.setText(NounBase.collectives[a3 + NOUN_NUMBER]);
			b4.setText(NounBase.collectives[a4 + NOUN_NUMBER]);
			int a_position = random.nextInt(4);
			bs[a_position].setText(NounBase.collectives[index]);
		} else {
			b1.setText(NounBase.groups[a1 + NOUN_NUMBER]);
			b2.setText(NounBase.groups[a2 + NOUN_NUMBER]);
			b3.setText(NounBase.groups[a3 + NOUN_NUMBER]);
			b4.setText(NounBase.groups[a4 + NOUN_NUMBER]);
			int a_position = random.nextInt(4);
			bs[a_position].setText(NounBase.groups[index]);
		}		
	}
	
	private void finishTest() {
		setContentView(R.layout.layout_test_complete);
		TextView score = (TextView) findViewById(R.id.achieved_score);
		int scoreInt = getScore();
		score.setText(Integer.toString(scoreInt));
		saveBestScore(scoreInt);
		setScoreTriangle(scoreInt);
		
		prefs = getSharedPreferences("Achieved", MODE_PRIVATE);
		int bestScore = prefs.getInt(getExerciseId(), 0);
		TextView bestScoreText = (TextView) findViewById(R.id.achieved_achieved_score);
		bestScoreText.setText(Integer.toString(bestScore));
		setBestScoreTriangle(bestScore);
			
	}
	
	private void saveBestScore(int score) {
		prefs = getSharedPreferences("Achieved", MODE_PRIVATE);
		editor = prefs.edit();
		int currentBest = prefs.getInt(getExerciseId(), 0);
		if (score > currentBest) {
			editor.putInt(getExerciseId(), score);
			editor.commit();
			// Next request to quit will reload homepage
			homeResourceReload = true;
		}		
	}
	
	private String getExerciseId() {
		return String.valueOf(NOUN_NUMBER / 8);		
	}

	private void setScoreTriangle(int i) {
		ImageView img = (ImageView) findViewById(R.id.current_score_img);
		if (i >= PLATINUM_TRIANGLE_THRESHOLD) {
			img.setImageResource(R.drawable.plattriangle);
		} else if (i >= GREEN_TRIANGLE_THRESHOLD) {
			img.setImageResource(R.drawable.greentriangle);
		}		
	}
	
	private void setBestScoreTriangle(int i) {
		ImageView img = (ImageView) findViewById(R.id.best_score_img);
		if (i >= PLATINUM_TRIANGLE_THRESHOLD) {
			img.setImageResource(R.drawable.plattriangle);
		} else if (i >= GREEN_TRIANGLE_THRESHOLD) {
			img.setImageResource(R.drawable.greentriangle);
		}		
	}

	private void setIncorrectBanner() {
		LinearLayout background = (LinearLayout) findViewById(R.id.correction_background);
		background.setVisibility(0);
		background.setBackgroundColor(getResources().getColor(R.color.lightred));
		TextView incorrect = (TextView) findViewById(R.id.Correct_Incorrect);
		incorrect.setTextColor(getResources().getColor(R.color.darkred));
		incorrect.setText(R.string.incorrect);
		TextView correctAnswer = (TextView) findViewById(R.id.correctAnswer);
		correctAnswer.setTextColor(getResources().getColor(R.color.darkred));
	}
	
	private void setCorrectBanner() {
		LinearLayout background = (LinearLayout) findViewById(R.id.correction_background);
		background.setVisibility(0);
		background.setBackgroundColor(getResources().getColor(R.color.lightgreen));
		TextView incorrect = (TextView) findViewById(R.id.Correct_Incorrect);
		incorrect.setTextColor(getResources().getColor(R.color.green));
		incorrect.setText(R.string.correct);
		TextView correctAnswer = (TextView) findViewById(R.id.correctAnswer);
		correctAnswer.setTextColor(getResources().getColor(R.color.green));
	}
	
	private void setBannerInvisible() {
		LinearLayout background = (LinearLayout) findViewById(R.id.correction_background);
		background.setVisibility(View.INVISIBLE);
	}
	
	private boolean readyForNextQuestion() {
		return readyForNextQuestion;
	}
	
	private void setReadyForNextQuestion() {
		readyForNextQuestion = true;
	}
	
	private void unsetReadyForNextQuestion() {
		readyForNextQuestion = false;
	}
	
	private void setCorrectAnswerText(int index) {
		TextView answer = (TextView) findViewById(R.id.correctAnswer);
		answer.setText(getAnswer(index));
	}
	private void initialiseScores() {
		for (int i : scores) {
			i = 0;
		}
	}
	private void markCorrectAnswer(int index) {
		System.out.println("marked Correct");
		scores[index] = 1;
	}
	private void markIncorrectAnswer(int index) {
		scores[index] = -1;
	}		
	private void setProgressBoxes() {
		//for (int i : scores) {
		//	System.out.println(Integer.toString(i));
			/*if (i == 1) {
				ImageView imgX = (ImageView) findViewById(R.id.progress5);
				imgX.setImageResource(R.drawable.progressboxfilled);
				ImageView img = (ImageView) findViewById(progress[i]);
				img.setImageResource(R.drawable.progressboxfilled);
			}*/
			if (scores[0] == 1) {
				ImageView img = (ImageView) findViewById(R.id.progress1);
				img.setImageResource(R.drawable.progressboxfilled);
			}
			if (scores[1] == 1) {
				ImageView img = (ImageView) findViewById(R.id.progress2);
				img.setImageResource(R.drawable.progressboxfilled);
			}
			if (scores[2] == 1) {
				ImageView img = (ImageView) findViewById(R.id.progress3);
				img.setImageResource(R.drawable.progressboxfilled);
			}
			if (scores[3] == 1) {
				ImageView img = (ImageView) findViewById(R.id.progress4);
				img.setImageResource(R.drawable.progressboxfilled);
			}
			if (scores[4] == 1) {
				ImageView img = (ImageView) findViewById(R.id.progress5);
				img.setImageResource(R.drawable.progressboxfilled);
			}
			if (scores[5] == 1) {
				ImageView img = (ImageView) findViewById(R.id.progress6);
				img.setImageResource(R.drawable.progressboxfilled);
			}
			if (scores[6] == 1) {
				ImageView img = (ImageView) findViewById(R.id.progress7);
				img.setImageResource(R.drawable.progressboxfilled);
			}
			if (scores[7] == 1) {
				ImageView img = (ImageView) findViewById(R.id.progress8);
				img.setImageResource(R.drawable.progressboxfilled);
			}
			if (scores[8] == 1) {
				ImageView img = (ImageView) findViewById(R.id.progress9);
				img.setImageResource(R.drawable.progressboxfilled);
			}
			if (scores[9] == 1) {
				ImageView img = (ImageView) findViewById(R.id.progress10);
				img.setImageResource(R.drawable.progressboxfilled);
			}
			if (scores[10] == 1) {
				ImageView img = (ImageView) findViewById(R.id.progress11);
				img.setImageResource(R.drawable.progressboxfilled);
			}
			if (scores[11] == 1) {
				ImageView img = (ImageView) findViewById(R.id.progress12);
				img.setImageResource(R.drawable.progressboxfilled);
			}
			if (scores[12] == 1) {
				ImageView img = (ImageView) findViewById(R.id.progress13);
				img.setImageResource(R.drawable.progressboxfilled);
			}
			if (scores[13] == 1) {
				ImageView img = (ImageView) findViewById(R.id.progress14);
				img.setImageResource(R.drawable.progressboxfilled);
			}
			if (scores[14] == 1) {
				ImageView img = (ImageView) findViewById(R.id.progress15);
				img.setImageResource(R.drawable.progressboxfilled);
			}
			//Shameless redbox filling - can't get better method to work
			if (scores[0] == -1) {
				ImageView img = (ImageView) findViewById(R.id.progress1);
				img.setImageResource(R.drawable.progressboxfilledred);
			}
			if (scores[1] == -1) {
				ImageView img = (ImageView) findViewById(R.id.progress2);
				img.setImageResource(R.drawable.progressboxfilledred);
			}
			if (scores[2] == -1) {
				ImageView img = (ImageView) findViewById(R.id.progress3);
				img.setImageResource(R.drawable.progressboxfilledred);
			}
			if (scores[3] == -1) {
				ImageView img = (ImageView) findViewById(R.id.progress4);
				img.setImageResource(R.drawable.progressboxfilledred);
			}
			if (scores[4] == -1) {
				ImageView img = (ImageView) findViewById(R.id.progress5);
				img.setImageResource(R.drawable.progressboxfilledred);
			}
			if (scores[5] == -1) {
				ImageView img = (ImageView) findViewById(R.id.progress6);
				img.setImageResource(R.drawable.progressboxfilledred);
			}
			if (scores[6] == -1) {
				ImageView img = (ImageView) findViewById(R.id.progress7);
				img.setImageResource(R.drawable.progressboxfilledred);
			}
			if (scores[7] == -1) {
				ImageView img = (ImageView) findViewById(R.id.progress8);
				img.setImageResource(R.drawable.progressboxfilledred);
			}
			if (scores[8] == -1) {
				ImageView img = (ImageView) findViewById(R.id.progress9);
				img.setImageResource(R.drawable.progressboxfilledred);
			}
			if (scores[9] == -1) {
				ImageView img = (ImageView) findViewById(R.id.progress10);
				img.setImageResource(R.drawable.progressboxfilledred);
			}
			if (scores[10] == -1) {
				ImageView img = (ImageView) findViewById(R.id.progress11);
				img.setImageResource(R.drawable.progressboxfilledred);
			}
			if (scores[11] == -1) {
				ImageView img = (ImageView) findViewById(R.id.progress12);
				img.setImageResource(R.drawable.progressboxfilledred);
			}
			if (scores[12] == -1) {
				ImageView img = (ImageView) findViewById(R.id.progress13);
				img.setImageResource(R.drawable.progressboxfilledred);
			}
			if (scores[13] == -1) {
				ImageView img = (ImageView) findViewById(R.id.progress14);
				img.setImageResource(R.drawable.progressboxfilledred);
			}
			if (scores[14] == -1) {
				ImageView img = (ImageView) findViewById(R.id.progress15);
				img.setImageResource(R.drawable.progressboxfilledred);
			}
	}
	
	private String getAnswer(int index) {
		String collective = getResources().getString(NounBase.collectives[index]);
		String group = getResources().getString(NounBase.groups[index]);
		String a_an; char c = collective.charAt(0);
		if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
			a_an = "An";
		} else {
			a_an = "A";
		}
		return (a_an + " " + collective + " of " + group);
				
	}
	
	public void learn(View v) {
		Intent intent_learn = new Intent(getApplicationContext(), Learn.class);
		intent_learn.putExtra("NounNumber", NOUN_NUMBER);
		startActivity(intent_learn);		
	}
	public void testOut(View v) {
		Intent intent_test = new Intent(getApplicationContext(), Test.class);
		intent_test.putExtra("NounNumber", NOUN_NUMBER);
		startActivity(intent_test);
	}	
	public void quit(View v) {
		if (homeResourceReload) {
			homeResourceReload = false;
			Intent intent_home = new Intent(getApplicationContext(), Home.class);
			startActivity(intent_home);
			finish();
		} else {
			finish();
		}	
	}
	
	private void setTextEditorListener() {
		EditText editText = (EditText) findViewById(R.id.answerText);
		editText.setOnEditorActionListener(new OnEditorActionListener() {
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_DONE) {
					Button check = (Button) findViewById(R.id.check);
					check.performClick();
					return true;
				}
				return false;
			}
		});
	}
	
	private void hideEditText() {
		EditText et = (EditText) findViewById(R.id.answerText);
		InputMethodManager imm = (InputMethodManager)getSystemService(
				Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(et.getWindowToken(), 0);		
	}
	
	private void switchCheck_NextText() {
		Button b = (Button) findViewById(R.id.check);
		String text = b.getText().toString();
		if (text.equals("Check")){
			b.setText(R.string.contin);
		} else {			
			b.setText(R.string.check);
		}
	}
	
	private int getScore() {
		int count = 0;
		for (int i : scores) {
			if (i == 1) {
				count++;
			}
		}
		return count;
	}
	
	public void option1Selection(View v) {
		setUnderscoreText(1);		
	}
	public void option2Selection(View v) {
		setUnderscoreText(2);		
	}
	public void option3Selection(View v) {
		setUnderscoreText(3);		
	}
	public void option4Selection(View v) {
		setUnderscoreText(4);		
	}
	
	private void setUnderscoreText(int option) {
		Button b = null;
		switch (option) {
		case 1: b = (Button) findViewById(R.id.option1);
				break;
		case 2: b = (Button) findViewById(R.id.option2);
				break;
		case 3: b = (Button) findViewById(R.id.option3);
				break;
		case 4: b = (Button) findViewById(R.id.option4);
				break;
		}
		String answer = (String) b.getText();
		if (QUESTION_TYPE == 1) {
			TextView t = (TextView) findViewById(R.id.collective_noun);
			t.setText(answer);
		} else if (QUESTION_TYPE == 2) {
			TextView t = (TextView) findViewById(R.id.group);
			t.setText(answer);
		}
	}
	
	public void requestEditTextFocus(View v) {
		System.out.println("Focus request");
		EditText et = (EditText) findViewById(R.id.answerText);
		et.requestFocus();
		InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		mgr.showSoftInput(et,  InputMethodManager.SHOW_IMPLICIT);
	}


	

}