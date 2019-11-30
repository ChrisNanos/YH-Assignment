package uk.ac.stir.cs.yh.chn00029;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PageViewModel extends ViewModel {

    /**
     * Live Data Instance
     * Sets Live Data between the two fragments, which update with each change
     */
    //The unit spinner represent the units of the selected category
    public MutableLiveData<String[]> unitSpinner = new MutableLiveData<>();
    //The text of the unit to be converted
    private MutableLiveData<String> fromName = new MutableLiveData<>();
    //The text of the converted unit
    private MutableLiveData<String> toName = new MutableLiveData<>();
    private String category;
    private int size = 0;
    private int p1 = 0; //position of fromName
    private int p2 = 0; //position of toName

    //Getter Setter of Category
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    //Getter Setter of P1
    public int getP1() {
        return p1;
    }

    public void setP1(int p) {
        p1 = p;
        setFrom(p1);
    }

    //Getter Setter of P2
    public int getP2(){
        return p2;
    }

    public void setP2(int p){
        p2 = p;
        setTo(p2);
    }

    //Setter of unitSpinner
    public void setUnitSpinner(String[] a){

        unitSpinner.setValue(a);
        size = a.length;
    }

    //Method to increment the converted unit's position
    public void incUnit(){
        if (p2 + 1 <= size - 1){
            p2 = p2 + 1;
        }
        setTo(p2);
    }

    //Method to decrement the converted unit's position
    public void decUnit(){
        if (p2 - 1 >= 0){
            p2 = p2 - 1;
        }
        setTo(p2);
    }

    //Getter Setter of the unit to be converted
    public LiveData<String> getFrom(){
        return fromName;
    }

    public void setFrom(int i){
        fromName.setValue(unitSpinner.getValue()[i]);
    }

    //Getter Setter of the converted unit
    public LiveData<String> getTo(){
        return toName;
    }

    public void setTo(int i){
        toName.setValue(unitSpinner.getValue()[i]);
    }

    /*Method to inverse the unit to be converted and the unit it's converted to.
      Takes the units as a string, finds their position in unitSpinner and swaps them.
    */
    public void inverse(String from, String to){
        //Looks for position of from
        for(int i=0; i < size; i++) {
            if (unitSpinner.getValue()[i].equals(from)) { //Once found
                //Sets it as the position of P2
                setP2(i);
                break;
            }
        }
        //Looks for the position of to
        for(int j=0; j < size; j++){
            if (unitSpinner.getValue()[j].equals(to)) { //Once found
                //Set it as the position of P1
                setP1(j);
                break;
            }
        }
    }
}