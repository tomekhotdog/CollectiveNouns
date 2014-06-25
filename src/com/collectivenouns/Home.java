package com.collectivenouns;

import java.util.Locale;
import java.util.Random;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Home extends FragmentActivity {
	
	private final static int PEOPLE1NUMBER = 0;	private final static int PEOPLE2NUMBER = 8;
	private final static int PEOPLE3NUMBER = 16; private final static int PEOPLE4NUMBER = 24;
	private final static int PEOPLE5NUMBER = 32; private final static int ANIMAL1NUMBER = 40;
	private final static int ANIMAL2NUMBER = 48; private final static int ANIMAL3NUMBER = 56;
	private final static int ANIMAL4NUMBER = 64; private final static int ANIMAL5NUMBER = 72;
	private final static int OBJECT1NUMBER = 80; private final static int OBJECT2NUMBER = 88;
	private final static int OBJECT3NUMBER = 96; private final static int OBJECT4NUMBER = 104;
	private final static int OBJECT5NUMBER = 112;

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
			case 2 :
				// About fragment
				return new AboutFragment();
			}
			
			return null;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
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

	public void learnPeople1(View v) {
		startLearnIntent(PEOPLE1NUMBER);
	}
	public void learnPeople2(View v) {
		startLearnIntent(PEOPLE2NUMBER);
	}
	public void learnPeople3(View v) {
		startLearnIntent(PEOPLE3NUMBER);
	}
	public void learnPeople4(View v) {
		startLearnIntent(PEOPLE4NUMBER);
	}
	public void learnPeople5(View v) {
		startLearnIntent(PEOPLE5NUMBER);
	}
	public void learnAnimal1(View v) {
		startLearnIntent(ANIMAL1NUMBER);
	}
	public void learnAnimal2(View v) {
		startLearnIntent(ANIMAL2NUMBER);
	}
	public void learnAnimal3(View v) {
		startLearnIntent(ANIMAL3NUMBER);
	}
	public void learnAnimal4(View v) {
		startLearnIntent(ANIMAL4NUMBER);
	}
	public void learnAnimal5(View v) {
		startLearnIntent(ANIMAL5NUMBER);
	}
	public void learnObject1(View v) {
		startLearnIntent(OBJECT1NUMBER);
	}
	public void learnObject2(View v) {
		startLearnIntent(OBJECT2NUMBER);
	}
	public void learnObject3(View v) {
		startLearnIntent(OBJECT3NUMBER);
	}
	public void learnObject4(View v) {
		startLearnIntent(OBJECT4NUMBER);
	}
	public void learnObject5(View v) {
		startLearnIntent(OBJECT5NUMBER);
	}
	public void testPeople1(View v) {
		startTestIntent(PEOPLE1NUMBER);
	}
	public void testPeople2(View v) {
		startTestIntent(PEOPLE2NUMBER);
	}
	public void testPeople3(View v) {
		startTestIntent(PEOPLE3NUMBER);
	}
	public void testPeople4(View v) {
		startTestIntent(PEOPLE4NUMBER);
	}
	public void testPeople5(View v) {
		startTestIntent(PEOPLE5NUMBER);
	}
	public void testAnimal1(View v) {
		startTestIntent(ANIMAL1NUMBER);
	}
	public void testAnimal2(View v) {
		startTestIntent(ANIMAL2NUMBER);
	}
	public void testAnimal3(View v) {
		startTestIntent(ANIMAL3NUMBER);
	}
	public void testAnimal4(View v) {
		startTestIntent(ANIMAL4NUMBER);
	}
	public void testAnimal5(View v) {
		startTestIntent(ANIMAL5NUMBER);
	}
	public void testObject1(View v) {
		startTestIntent(OBJECT1NUMBER);
	}
	public void testObject2(View v) {
		startTestIntent(OBJECT2NUMBER);
	}
	public void testObject3(View v) {
		startTestIntent(OBJECT3NUMBER);
	}
	public void testObject4(View v) {
		startTestIntent(OBJECT4NUMBER);
	}
	public void testObject5(View v) {
		startTestIntent(OBJECT5NUMBER);
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

}
