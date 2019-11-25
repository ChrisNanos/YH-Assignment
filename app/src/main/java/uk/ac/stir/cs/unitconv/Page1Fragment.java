package uk.ac.stir.cs.unitconv;

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

    private Spinner category, unit1spin, unit2spin;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        category = view.findViewById(R.id.cat_spin);
        unit1spin = view.findViewById(R.id.unit_spin1);
        unit2spin = view.findViewById(R.id.unit_spin2);

        // Listener for changes in the Category Spinner
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
//                pageViewModel.setTo(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });
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

    /* The spinnerSelector method sets the values of the selectable units to that of the selected position */
    private void spinnerSelector(int pos){
        ArrayAdapter<String> newSpinner = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item);
        newSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unit1spin.setAdapter(newSpinner);
        unit2spin.setAdapter(newSpinner);

        // switch for each position of the position items
        switch (pos){
            case 0:
                newSpinner.addAll(getActivity().getResources().getStringArray(R.array.distance));
                pageViewModel.setSize(getActivity().getResources().getStringArray(R.array.distance).length);
                pageViewModel.setUnitSpinner(getActivity().getResources().getStringArray(R.array.distance));
                break;
            case 1:
                newSpinner.addAll(getActivity().getResources().getStringArray(R.array.weight));
                pageViewModel.setSize(getActivity().getResources().getStringArray(R.array.weight).length);
                pageViewModel.setUnitSpinner(getActivity().getResources().getStringArray(R.array.weight));
                break;
            case 2:
                newSpinner.addAll(getActivity().getResources().getStringArray(R.array.speed));
                pageViewModel.setSize(getActivity().getResources().getStringArray(R.array.speed).length);
                pageViewModel.setUnitSpinner(getActivity().getResources().getStringArray(R.array.speed));
                break;
        }
        newSpinner.notifyDataSetChanged();
    }
}
