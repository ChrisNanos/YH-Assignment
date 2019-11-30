package uk.ac.stir.cs.yh.chn00029;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


public class Page1Fragment extends Fragment {


    private PageViewModel pageViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // init ViewModel
        pageViewModel = ViewModelProviders.of(requireActivity()).get(PageViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        return inflater.inflate(R.layout.page1_layout, container, false);
    }

    //Declare spinners
    private Spinner category, unit1spin, unit2spin;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Connect spinners to the UI
        category = view.findViewById(R.id.cat_spin);
        unit1spin = view.findViewById(R.id.unit_spin1);
        unit2spin = view.findViewById(R.id.unit_spin2);

        // Listen for changes in the Category Spinner
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    //Passes the position of the selected item into the spinnerSelector method
                    spinnerSelector(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        // Listener for changes in the first unit Spinner
        unit1spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                pageViewModel.setP1(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });

        // Listener for changes in the second unit Spinner
        unit2spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                pageViewModel.setP2(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });

        /**
         * Observer for changes in the Unit Spinners.
         * If the conversion units are changed in Fragment2, the change is carried over to Fragment1.
         */
        pageViewModel.getFrom().observe(requireActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                unit1spin.setSelection(pageViewModel.getP1());
            }
        });
        pageViewModel.getTo().observe(requireActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                unit2spin.setSelection(pageViewModel.getP2());
            }
        });
    }

    /* The spinnerSelector method sets the values of the selectable units to that of the selected category */
    private void spinnerSelector(int pos){
        // A new instance of a spinner has to be created and populated with the selected category's units,
        // because the dropdown items cannot be edited once set.
        ArrayAdapter<String> newSpinner = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item);
        newSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unit1spin.setAdapter(newSpinner);
        unit2spin.setAdapter(newSpinner);

        // switch for each position of the category items
        switch (pos){
            case 0:
                //The new spinner gets populated with the distance units
                newSpinner.addAll(getActivity().getResources().getStringArray(R.array.distance));
                //The spinner information in the array.xml, is passed to the PageViewModel class
                pageViewModel.setUnitSpinner(getActivity().getResources().getStringArray(R.array.distance));
                //The category is also passed as a string to the PageViewModel class
                pageViewModel.setCategory("distance");
                break;
            case 1:
                //Similarly to the first but for weight
                newSpinner.addAll(getActivity().getResources().getStringArray(R.array.weight));
                pageViewModel.setUnitSpinner(getActivity().getResources().getStringArray(R.array.weight));
                pageViewModel.setCategory("weight");
                break;
            case 2:
                //Similarly to the first but for speed
                newSpinner.addAll(getActivity().getResources().getStringArray(R.array.speed));
                pageViewModel.setUnitSpinner(getActivity().getResources().getStringArray(R.array.speed));
                pageViewModel.setCategory("speed");
                break;
        }
        newSpinner.notifyDataSetChanged();
    }
}
