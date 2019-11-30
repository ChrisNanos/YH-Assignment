package uk.ac.stir.cs.yh.chn00029;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class Page2Fragment extends Fragment implements View.OnClickListener {
    private PageViewModel pageViewModel;

    /**
     * A 2D array, containing the ratios of every possible unit conversion, for each category.
     * The first index denotes the unit to be converted and the second, the unit to be converted to.
     * */
    private double[][] distance = { {1, 1000, 100000, 0.6213688756, 1093.6132983, 3280.839895, 39370.07874},
                                    {0.001, 1, 100, 0.0006213689, 1.0936132983, 3.280839895, 39.37007874},
                                    {0.00001, 0.01, 1, 0.00000621, 0.01093613, 0.03280839, 0.39370078},
                                    {1.60935, 1609.35, 160935, 1, 1760.0065617, 5280.019685, 63360.23622},
                                    {0.0009144, 0.9144, 91.44, 0.0005681797, 1, 3, 36},
                                    {0.0003048, 0.3048, 30.48, 0.0001893932, 0.3333333333, 1, 12},
                                    {0.0000254, 0.0254, 2.54, 0.0000157828, 0.0277777778, 0.0833333333, 1}};
    private double[][] weight =   { {1, 1000, 2.2046244202, 35.273990723, 0.157473044},
                                    {0.001, 1, 0.0022046244, 0.0352739907, 0.000157473044},
                                    {0.453592, 453.592, 1, 16, 0.0714285714},
                                    {0.0283495, 28.3495, 0.0625, 1, 0.00446428571},
                                    {6.35029318, 6350.29318, 14, 224, 1}};
    private double[][] speed = {  {1, 0.621371192}, {1.609344, 1}};

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

    //Declare EditTexts
    private EditText from_conv, to_conv;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Connect the EditText to the UI
        from_conv = view.findViewById(R.id.from_conv);
        to_conv = view.findViewById(R.id.to_conv);
        //Disable the text, so that it is not clickable and the default Android keyboard won't come out
        from_conv.setEnabled(false);
        to_conv.setEnabled(false);

        //Initialise calculator buttons
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
        Button dot = view.findViewById(R.id.dot);
        Button inverse = view.findViewById(R.id.inverse);
        Button convert = view.findViewById(R.id.convert);

        //A Listener for each button
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
        dot.setOnClickListener(this);
        inverse.setOnClickListener(this);
        convert.setOnClickListener(this);


        /**
         * Observers for the two EditTexts, to check for changes in pageViewModel.
         * The selected units in Fragment1 are set as Hints in Fragment2.
         */
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


    /**
     * The Listener activated when a Button is clicked
     * @param v the View passed as an argument
     */
    @Override
    public void onClick(View v) {
        //Gets the id of the button clicked
        int id = v.getId();
        //The id passes through a switch statement to identify the button clicked
        switch (id) {
            //Numbers ~ Added as text to the unit to be converted
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
            //Arrows that change the unit to be converted to
            case R.id.up_arrow:
                pageViewModel.decUnit(); //calls the method in pageViewModel
                to_conv.setText(""); //clears the text, to make the new unit visible
                break;
            case R.id.down_arrow:
                pageViewModel.incUnit(); //calls the method in pageViewModel
                to_conv.setText(""); //clears the text, to make the new unit visible
                break;
            //Clear Button
            case R.id.clear:
                from_conv.setText("");
                to_conv.setText("");
                break;
                //Inverse Button
            case R.id.inverse:
                //Passes the string of the two units to pageViewModel, through the inverse method.
                pageViewModel.inverse(from_conv.getHint().toString(), to_conv.getHint().toString());
                //The units are swapped and the text is cleared
                from_conv.setText("");
                to_conv.setText("");
                break;
            //Dot Button ~ Sets a dot
            case R.id.dot:
                //If the text is empty sets a 0 before the dot -> 0.
                //This resolve an error when trying to convert a dot without a number
                if (from_conv.getText().toString().matches("")){
                    from_conv.setText(from_conv.getText() + "0.");
                }else{
                    from_conv.setText(from_conv.getText() + ".");
                }
                break;
            //Convert Button
            case R.id.convert:
                //Checks to make sure the text isn't empty
                if (!from_conv.getText().toString().matches("")) {
                    double from = Double.parseDouble(from_conv.getText().toString()); //gets the from
                    double ratio = 0;
                    //Switch statement looks through the categories to find the correct 2DArray ratio
                    switch (pageViewModel.getCategory()) {
                        case "distance":
                            ratio = distance[pageViewModel.getP1()][pageViewModel.getP2()];
                            break;
                        case "weight":
                            ratio = weight[pageViewModel.getP1()][pageViewModel.getP2()];
                            break;
                        case "speed":
                            ratio = speed[pageViewModel.getP1()][pageViewModel.getP2()];
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value");
                    }
                    double to = from * ratio; //the conversion is made
                    to_conv.setText("" + to); //the converted number is displayed as a String, in the format of a Double
                }
        }
    }
}

