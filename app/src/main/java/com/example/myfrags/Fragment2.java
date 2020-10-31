package com.example.myfrags;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class Fragment2 extends Fragment {

    //1
    private FragsData fragsData;
    private Observer<Integer> numberObserver; //prywatne pole obserwatora

    //2
    private TextView text;
    private Button button;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //return inflater.inflate(R.layout.fragment_2, container, false);//1 sposob na tworzenie layoutu we fragmencie1
        View view = inflater.inflate(R.layout.fragment_2, container, false);//2 sposob na tworzenie layoutu we fragmencie1

        //1

        text=(TextView)view.findViewById(R.id.current);
        button=(Button)view.findViewById(R.id.button_decrease);

        //2 tworze objekt fragsData klasy FragsData(bo FragsData extends ViewModel)

        fragsData=new ViewModelProvider(requireActivity()).get(FragsData.class);

        //3 tworze observatora (numberObserver przechowuje liczbe ta sama dla kazdego frgamentu)

        numberObserver=new Observer<Integer>() {

            //3.1 metoda onChange uruchamiana zawsze gdy wartosc observatora sie zmienia
            @Override
            public void onChanged(Integer integer) {
                text.setText(integer.toString());
            }
        };

        //4 podłączenie obserwatora do obserwowaniej zmiennej

        fragsData.counter.observe(getViewLifecycleOwner(), numberObserver);

        //5

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer i=fragsData.counter.getValue();//przechwyć liczbe z ViewModel (dla kazdego frgamentu)
                fragsData.counter.setValue(++i);//powiększ o 1 liczbe z ViewModel (dla kazdego frgamentu)
            }
        });



        return view;//2 sposob na tworzenie layoutu we fragmencie1

    }
}