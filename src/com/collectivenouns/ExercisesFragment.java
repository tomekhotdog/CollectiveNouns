package com.collectivenouns;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ExercisesFragment extends Fragment {
	
	private static final int PLATINUM_TRIANGLE_THRESHOLD = 15;
	private static final int GREEN_TRIANGLE_THRESHOLD = 12;
	private final static int EXERCISES = 15;
	SharedPreferences prefs;
	Editor editor;
	private Integer[] iDs = {R.id.people1, R.id.people2, R.id.people3, R.id.people4, R.id.people5,
						 R.id.animal1, R.id.animal2, R.id.animal3, R.id.animal4, R.id.animal5,
						 R.id.object1, R.id.object2, R.id.object3, R.id.object4, R.id.object5};
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_exercises, container, false);
        setAchievementBackgrounds(rootView); 
        return rootView;
        
    }
	
	public void setAchievementBackgrounds(View v) {
		prefs = this.getActivity().getSharedPreferences("Achieved", 0);
		for (int i = 0; i < EXERCISES; i++) {
			ImageView img = (ImageView) v.findViewById(iDs[i]);			
			int bestScore = prefs.getInt(Integer.toString(i), 0);
			if (bestScore >= PLATINUM_TRIANGLE_THRESHOLD) {
				//Displays appropriate exercise icon along with colour
				//*****VALUES WILL NEED TO BE ADAPTED!!*****
				if (i >= 0) {
					img.setImageResource(R.drawable.peopleplatinum);
				} 
				if (i >= 5) {
					img.setImageResource(R.drawable.animalplatinum);
				}
				if (i >= 10) {
					img.setImageResource(R.drawable.objectplatinum);
				}
			} else if (bestScore >= GREEN_TRIANGLE_THRESHOLD) {
				if (i >= 0) {
					img.setImageResource(R.drawable.peoplegreen);
				} 
				if (i >= 5) {
					img.setImageResource(R.drawable.animalgreen);
				}
				if (i >= 10) {
					img.setImageResource(R.drawable.objectgreen);
				}
			}
		}
	}
	
}
