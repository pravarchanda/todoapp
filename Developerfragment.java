package com.example.pravar.sidepanel;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class Developerfragment extends Fragment {
View view;
Button buttonfb,buttonln,buttonqr;
    public Developerfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_developerfragment, container, false);
        ((MainActivity) getActivity()).setactionbartitle("Developer");
        buttonfb = (Button) view.findViewById(R.id.buttonfb);
        buttonfb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.facebook.com/pravar.chanda"));
                startActivity(intent);
            }
        });
        buttonln = (Button) view.findViewById(R.id.buttonln);
        buttonln.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://in.linkedin.com/in/pravar-chanda-13633a98"));
                startActivity(intent);
            }
        });
        buttonqr = (Button) view.findViewById(R.id.buttonqr);
        buttonqr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.quora.com/profile/Pravar-Chanda"));
                startActivity(intent);
            }
        });
    return view;
    }

}
