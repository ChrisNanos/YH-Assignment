package uk.ac.stir.cs.unitconv;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PageViewModel extends ViewModel {

    /**
     * Live Data Instance
     * Sets Live Data between the two fragments, which update with each change
     */
    public MutableLiveData<String[]> unitSpinner = new MutableLiveData<>();
    private MutableLiveData<String> fromName = new MutableLiveData<>();
    private MutableLiveData<String> toName = new MutableLiveData<>();
    private int size = 0;
    private int p1 = 0;
    private int p2 = 0;

    public int getP1() {
        return p1;
    }

    public void setP1(int p) {
        p1 = p;
        setFrom(p1);
    }

    public int getP2(){
        return p2;
    }

    public void setP2(int p){
        p2 = p;
        setTo(p2);
    }

    public String[] getUnitSpinner(){
        return unitSpinner.getValue();
    }

    public void setUnitSpinner(String[] a){
        unitSpinner.setValue(a);
    }

    public int getSize(){
        return size;
    }

    public void setSize(int s){
        size = s;
    }

    public void incCat(){
        if (p2 + 1 <= size - 1){
            p2 = p2 + 1;
        }
        setTo(p2);
    }

    public void decCat(){
        if (p2 - 1 >= 0){
            p2 = p2 - 1;
        }
        setTo(p2);
    }

    public void setFrom(int i){
        fromName.setValue(unitSpinner.getValue()[i]);
    }

    public void setTo(int i){
        toName.setValue(unitSpinner.getValue()[i]);
    }

    public LiveData<String> getFrom(){
        return fromName;
    }

    public LiveData<String> getTo(){
        return toName;
    }

    public void inverse(String from, String to){
        for(int i=0; i < size - 1; i++) {
            if (unitSpinner.getValue()[i].equals(from)) {
                setP2(i);
                break;
            }
        }
        for(int j=0; j < size - 1; j++){
            if (unitSpinner.getValue()[j].equals(to)) {
                setP1(j);
                break;
            }
        }
    }
}