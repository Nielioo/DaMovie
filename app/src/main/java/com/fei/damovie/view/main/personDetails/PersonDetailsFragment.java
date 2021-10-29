package com.fei.damovie.view.main.personDetails;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fei.damovie.R;
import com.fei.damovie.helper.Const;
import com.fei.damovie.helper.ProgressBarGoogle;
import com.fei.damovie.model.Movies;
import com.fei.damovie.model.Person;
import com.fei.damovie.viewModel.MovieViewModel;
import com.fei.damovie.viewModel.PersonViewModel;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;

public class PersonDetailsFragment extends Fragment {

    private KenBurnsView personDetails_backdrop_kenBurns;
    private ImageView personDetails_poster_imageView;
    private TextView personDetails_name_textView, personDetails_bio_textView, personDetails_birthday_textView,personDetails_birthplace_textView,personDetails_popularity_textView;
    private ProgressBarGoogle progressBarGoogle;
    private PersonViewModel viewModel;
    private String person_id = "";
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_person_details, container, false);

        initialize();

        return view;
    }

    private void setAnimation() {
        RandomTransitionGenerator randomTransitionGenerator = new RandomTransitionGenerator(1000, new DecelerateInterpolator());
        personDetails_backdrop_kenBurns.setTransitionGenerator(randomTransitionGenerator);
    }

    private void initialize() {
        progressBarGoogle = new ProgressBarGoogle(getActivity());
        progressBarGoogle.startProgress();

        person_id = getArguments().getString("person_id");

        viewModel = new ViewModelProvider(getActivity()).get(PersonViewModel.class);

        viewModel.setResultGetPersonById(person_id);
        viewModel.getResultGetPersonById().observe(getActivity(), showPersonDetails);

        personDetails_backdrop_kenBurns = view.findViewById(R.id.personDetails_backdrop_kenBurns);
        personDetails_poster_imageView = view.findViewById(R.id.personDetails_poster_imageView);
        personDetails_name_textView = view.findViewById(R.id.personDetails_name_textView);
        personDetails_bio_textView = view.findViewById(R.id.personDetails_bio_textView);
        personDetails_birthday_textView = view.findViewById(R.id.personDetails_birthday_textView);
        personDetails_birthplace_textView = view.findViewById(R.id.personDetails_birthplace_textView);
        personDetails_popularity_textView = view.findViewById(R.id.personDetails_popularity_textView);

        setAnimation();
    }

    private Observer<Person> showPersonDetails = new Observer<Person>() {
        @Override
        public void onChanged(Person person) {

            String profile_path = person.getProfile_path().toString().trim();
            String name = person.getName();
            String bio = person.getBiography();
            String birthday = person.getBirthday();
            String birthplace = person.getPlace_of_birth();
            String popularity = String.valueOf(person.getPopularity());

            if(!(profile_path == null)){
                String full_path = Const.IMG_URL + profile_path;
                Glide.with(getActivity())
                        .load(full_path)
                        .into(personDetails_backdrop_kenBurns);
            }

            if(!(profile_path == null)){
                String full_path = Const.IMG_URL + profile_path;
                Glide.with(getActivity())
                        .load(full_path)
                        .into(personDetails_poster_imageView);
            }

            personDetails_name_textView.setText(name);
            personDetails_bio_textView.setText(bio);
            personDetails_birthday_textView.setText(birthday);
            personDetails_birthplace_textView.setText(birthplace);
            personDetails_popularity_textView.setText(popularity);

            progressBarGoogle.stopProgress();
        }
    };

}