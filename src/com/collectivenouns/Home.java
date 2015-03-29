package com.collectivenouns;

import java.util.Locale;
import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends FragmentActivity {
	
	private final static int PEOPLE1NUMBER = 0;	private final static int PEOPLE2NUMBER = 8;
	private final static int PEOPLE3NUMBER = 16; private final static int PEOPLE4NUMBER = 24;
	private final static int PEOPLE5NUMBER = 32; private final static int ANIMAL1NUMBER = 40;
	private final static int ANIMAL2NUMBER = 48; private final static int ANIMAL3NUMBER = 56;
	private final static int ANIMAL4NUMBER = 64; private final static int ANIMAL5NUMBER = 72;
	private final static int OBJECT1NUMBER = 80; private final static int OBJECT2NUMBER = 88;
	private final static int OBJECT3NUMBER = 96; private final static int OBJECT4NUMBER = 104;
	private final static int OBJECT5NUMBER = 112; private final static int ANIMAL6NUMBER = 120;
    private final static int ANIMAL7NUMBER = 128; private final static int ANIMAL8NUMBER = 136;
    private final static int ANIMAL9NUMBER = 144; private final static int NATIONALITIES1NUMBER = 152;


    /**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_home);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.Feedback:
                sendEmailIntent();
                break;
        }
        return true;
    }

    /**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			/*
			Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
			*/
			switch (position) {
			case 0 :
				// Home fragment
				return new HomeFragment();
			case 1 :
				// Exercises fragment
				return new ExercisesFragment();
			}

			return null;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 2;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_home_dummy,
					container, false);
			TextView dummyTextView = (TextView) rootView
					.findViewById(R.id.section_label);
			dummyTextView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			return rootView;
		}
	}

    public void learnClick(View v) {
        int mLearnSelection = 0;
        switch(v.getId()) {
            case R.id.people1Learn:
                mLearnSelection = PEOPLE1NUMBER;
                break;
            case R.id.people2Learn:
                mLearnSelection = PEOPLE2NUMBER;
                break;
            case R.id.people3Learn:
                mLearnSelection = PEOPLE3NUMBER;
                break;
            case R.id.people4Learn:
                mLearnSelection = PEOPLE4NUMBER;
                break;
            case R.id.people5Learn:
                mLearnSelection = PEOPLE5NUMBER;
                break;
            case R.id.animal1Learn:
                mLearnSelection = ANIMAL1NUMBER;
                break;
            case R.id.animal2Learn:
                mLearnSelection = ANIMAL2NUMBER;
                break;
            case R.id.animal3Learn:
                mLearnSelection = ANIMAL3NUMBER;
                break;
            case R.id.animal4Learn:
                mLearnSelection = ANIMAL4NUMBER;
                break;
            case R.id.animal5Learn:
                mLearnSelection = ANIMAL5NUMBER;
                break;
            case R.id.object1Learn:
                mLearnSelection = OBJECT1NUMBER;
                break;
            case R.id.object2Learn:
                mLearnSelection = OBJECT2NUMBER;
                break;
            case R.id.object3Learn:
                mLearnSelection = OBJECT3NUMBER;
                break;
            case R.id.object4Learn:
                mLearnSelection = OBJECT4NUMBER;
                break;
            case R.id.object5Learn:
                mLearnSelection = OBJECT5NUMBER;
                break;
            case R.id.nationalities1Learn:
                mLearnSelection = NATIONALITIES1NUMBER;
                break;
            case R.id.animal6Learn:
                mLearnSelection = ANIMAL6NUMBER;
                break;
            case R.id.animal7Learn:
                mLearnSelection = ANIMAL7NUMBER;
                break;
            case R.id.animal8Learn:
                mLearnSelection = ANIMAL8NUMBER;
                break;
            case R.id.animal9Learn:
                mLearnSelection = ANIMAL9NUMBER;
                break;
        }
        startLearnIntent(mLearnSelection);
    }

    public void testClick(View v) {
        int mLearnSelection = 0;
        switch(v.getId()) {
            case R.id.people1Test:
                mLearnSelection = PEOPLE1NUMBER;
                break;
            case R.id.people2Test:
                mLearnSelection = PEOPLE2NUMBER;
                break;
            case R.id.people3Test:
                mLearnSelection = PEOPLE3NUMBER;
                break;
            case R.id.people4Test:
                mLearnSelection = PEOPLE4NUMBER;
                break;
            case R.id.people5Test:
                mLearnSelection = PEOPLE5NUMBER;
                break;
            case R.id.animal1Test:
                mLearnSelection = ANIMAL1NUMBER;
                break;
            case R.id.animal2Test:
                mLearnSelection = ANIMAL2NUMBER;
                break;
            case R.id.animal3Test:
                mLearnSelection = ANIMAL3NUMBER;
                break;
            case R.id.animal4Test:
                mLearnSelection = ANIMAL4NUMBER;
                break;
            case R.id.animal5Test:
                mLearnSelection = ANIMAL5NUMBER;
                break;
            case R.id.object1Test:
                mLearnSelection = OBJECT1NUMBER;
                break;
            case R.id.object2Test:
                mLearnSelection = OBJECT2NUMBER;
                break;
            case R.id.object3Test:
                mLearnSelection = OBJECT3NUMBER;
                break;
            case R.id.object4Test:
                mLearnSelection = OBJECT4NUMBER;
                break;
            case R.id.object5Test:
                mLearnSelection = OBJECT5NUMBER;
                break;
            case R.id.nationalities1Test:
                mLearnSelection = NATIONALITIES1NUMBER;
                break;
            case R.id.animal6Test:
                mLearnSelection = ANIMAL6NUMBER;
                break;
            case R.id.animal7Test:
                mLearnSelection = ANIMAL7NUMBER;
                break;
            case R.id.animal8Test:
                mLearnSelection = ANIMAL8NUMBER;
                break;
            case R.id.animal9Test:
                mLearnSelection = ANIMAL9NUMBER;
                break;
        }
        startTestIntent(mLearnSelection);
    }

	private void startLearnIntent(int NounNumber) {
		Intent intent_learn = new Intent(getApplicationContext(), Learn.class);
		intent_learn.putExtra("NounNumber", NounNumber);
		startActivity(intent_learn);
	}
	private void startTestIntent(int NounNumber) {
		Intent intent_test = new Intent(getApplicationContext(), Test.class);
		intent_test.putExtra("NounNumber", NounNumber);
		startActivity(intent_test);
	}
	public void randomTest(View v) {
		Random random = new Random();
		int selection = random.nextInt(15);
		startTestIntent(selection * 8);
	}
    public void iconClick(View v) {
        TextView score = (TextView) findViewById(R.id.TotalScore); TextView posScore = (TextView) findViewById(R.id.TotalPossibleScore);
        String scoreText = score.getText().toString(); String posText = posScore.getText().toString();
        int mScore = Integer.valueOf(scoreText); int mPos = Integer.valueOf(posText);
        if(mScore == mPos) {
            Context context = getApplication();
            String text = "";
            Toast.makeText(context, text, Toast.LENGTH_LONG).show();
        }
    }
    public void sendEmailIntent() {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"tomek.szymaniec@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "CollectiveNouns Feedback");

        String deviceInfo = "("+Build.MANUFACTURER+", "+Build.MODEL+","+Build.DISPLAY+","+Build.VERSION.SDK_INT+")\n\n";

        i.putExtra(Intent.EXTRA_TEXT   , deviceInfo + "");
        try {
            startActivity(Intent.createChooser(i, "Sending Email..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Home.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

}
