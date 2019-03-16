package com.socialnetwork.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.socialnetwork.Adapter.RecyclerAdapter;
import com.socialnetwork.Database.FirebaseManager;
import com.socialnetwork.R;
import com.socialnetwork.Utils.PreferencesManager;

import java.util.ArrayList;
import java.util.List;

import static com.socialnetwork.Utils.Constants.listGroup;
import static com.socialnetwork.Utils.Constants.listSkills;

public class ProfileActivity extends AppCompatActivity {

    private TextView nameText;
    private TextView emailText;
    private PreferencesManager preferencesManager;
    private Spinner spinnerType;
    static RecyclerView recyclerView;
    static RecyclerAdapter adapter;
    private EditText skillsTxt;
    static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        preferencesManager = new PreferencesManager(getApplicationContext());

        nameText = findViewById(R.id.userEmail);
        emailText = findViewById(R.id.userName);
        spinnerType = findViewById(R.id.spinnerType);
        skillsTxt = findViewById(R.id.skillsTxt);
        recyclerView = findViewById(R.id.recyclerView1);
        recyclerView.setHasFixedSize(true);
        int span = 1;
        GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), span);
        recyclerView.setLayoutManager(manager);


        addItemsSpinnerType();


        nameText.setText(preferencesManager.getUserName());
        emailText.setText(preferencesManager.getUserEmail());

        FirebaseManager.initializeInstance(getApplicationContext());

        context = getApplicationContext();

        skillsTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() >= 3) {
                    FirebaseManager.getSkills();

                }


            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void afterTextChanged(Editable s) {




            }
        });
    }

    // add items into spinner dynamically
    public void addItemsSpinnerType() {

        List<String> list = new ArrayList<String>();
        list.add("Pregrado");
        list.add("Especialización");
        list.add("Maestria");
        list.add("Doctorado");
        list.add("Pos Doctorado");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(dataAdapter);
    }

    public void clickConfigureProfile(View v){

    }

    public void clickTeacher(View v){

    }

    public void clickStudent(View v){

    }

    public static void setData(){
        recyclerView.setVisibility(View.VISIBLE);

        adapter = new RecyclerAdapter(context, listSkills, new RecyclerAdapter.OnClickListener() {
            @Override
            public void onItemClick(Object obj, int position) {
            }
        });
        recyclerView.setAdapter(adapter);
        listSkills = new ArrayList<>();
    }
}