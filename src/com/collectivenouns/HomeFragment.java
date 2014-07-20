package com.collectivenouns;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HomeFragment extends Fragment {
    private Activity mActivity;
    private SharedPreferences prefs;
    private static final int EXERCISES = 20;
    private static final int PLATINUM_TRIANGLE_THRESHOLD = 15;
    private static final int GREEN_TRIANGLE_THRESHOLD = 12;
    private int plat = 0;
    private int green = 0;
    private int amber = 0;
    private int total = 0;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        mActivity = getActivity();
        setContent(rootView);
         
        return rootView;
    }

    private void setContent(View v) {
        setScores(v);
        setTotalPossibleScore(v, EXERCISES);
    }

    private void setScores(View v) {
        TextView plat_score = (TextView) v.findViewById(R.id.platinum_achieved);
        TextView green_score = (TextView) v.findViewById(R.id.green_achieved);
        TextView amber_score = (TextView) v.findViewById(R.id.amber_achieved);
        TextView total_score = (TextView) v.findViewById(R.id.TotalScore);

        prefs = mActivity.getSharedPreferences("Achieved", mActivity.MODE_PRIVATE);
        for (int i = 0; i < EXERCISES; i++) {
            analyseScore(prefs.getInt(String.valueOf(i), 0));
        }

        plat_score.setText(String.valueOf(plat));
        green_score.setText(String.valueOf(green));
        amber_score.setText(String.valueOf(amber));
        total_score.setText(String.valueOf(total));
    }

    private void analyseScore(int i) {
        if (i >= GREEN_TRIANGLE_THRESHOLD) {
            if (i >= PLATINUM_TRIANGLE_THRESHOLD) {
                plat++;
            } else {
                green++;
            }
        } else {
                amber++;
        }
        total += i;
    }

    private void setTotalPossibleScore( View v,int i) {
        TextView totalPosScore = (TextView) v.findViewById(R.id.TotalPossibleScore);
        totalPosScore.setText(String.valueOf(i * 15));
    }


}
