package uk.ac.stir.cs.unitconv;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class Page2Fragment extends Fragment implements View.OnClickListener {
    private PageViewModel pageViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // initialise ViewModel
        pageViewModel = ViewModelProviders.of(requireActivity()).get(PageViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {

        return inflater.inflate(R.layout.page2_layout, container, false);
    }

    private EditText from_conv, to_conv;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        from_conv = view.findViewById(R.id.from_conv);
        to_conv = view.findViewById(R.id.to_conv);
        from_conv.setEnabled(false);
        to_conv.setEnabled(false);

        // initialise calculator buttons
        Button button1 = view.findViewById(R.id.b1);
        Button button2 = view.findViewById(R.id.b2);
        Button button3 = view.findViewById(R.id.b3);
        Button button4 = view.findViewById(R.id.b4);
        Button button5 = view.findViewById(R.id.b5);
        Button button6 = view.findViewById(R.id.b6);
        Button button7 = view.findViewById(R.id.b7);
        Button button8 = view.findViewById(R.id.b8);
        Button button9 = view.findViewById(R.id.b9);
        Button button0 = view.findViewById(R.id.b0);
        Button up_arrow = view.findViewById(R.id.up_arrow);
        Button down_arrow = view.findViewById(R.id.down_arrow);
        Button clear = view.findViewById(R.id.clear);
        Button inverse = view.findViewById(R.id.inverse);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button0.setOnClickListener(this);
        up_arrow.setOnClickListener(this);
        down_arrow.setOnClickListener(this);
        clear.setOnClickListener(this);
        inverse.setOnClickListener(this);


        pageViewModel.getFrom().observe(requireActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                from_conv.setHint(s);
            }
        });
        pageViewModel.getTo().observe(requireActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                to_conv.setHint(s);
            }
        });
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.b1:
                from_conv.setText(from_conv.getText() + "1");
                break;
            case R.id.b2:
                from_conv.setText(from_conv.getText() + "2");
                break;
            case R.id.b3:
                from_conv.setText(from_conv.getText() + "3");
                break;
            case R.id.b4:
                from_conv.setText(from_conv.getText() + "4");
                break;
            case R.id.b5:
                from_conv.setText(from_conv.getText() + "5");
                break;
            case R.id.b6:
                from_conv.setText(from_conv.getText() + "6");
                break;
            case R.id.b7:
                from_conv.setText(from_conv.getText() + "7");
                break;
            case R.id.b8:
                from_conv.setText(from_conv.getText() + "8");
                break;
            case R.id.b9:
                from_conv.setText(from_conv.getText() + "9");
                break;
            case R.id.b0:
                from_conv.setText(from_conv.getText() + "0");
                break;
            case R.id.up_arrow:
                pageViewModel.decCat();
                break;
            case R.id.down_arrow:
                pageViewModel.incCat();
                break;
            case R.id.clear:
                from_conv.setText("");
                to_conv.setText("");
                break;
            case R.id.inverse:
//                String[] spinner = pageViewModel.getUnitSpinner();
//                for(int i=0; i < spinner.length - 1; i++){
//                    if (spinner[i].equals(from_conv.getHint())) {
//                        pageViewModel.setP2(i);
//                        System.out.println(i);
//                        break;
//                    }
//                }
//                for(int i=0; i < spinner.length - 1; i++){
//                    if (spinner[i].equals(to_conv.getHint())) {
//                        pageViewModel.setP1(i);
//                        System.out.println(i);
//                        break;
//                    }
//                }
//                break;
                pageViewModel.inverse(from_conv.getHint().toString(), to_conv.getHint().toString());
                break;
        }

    }
}

