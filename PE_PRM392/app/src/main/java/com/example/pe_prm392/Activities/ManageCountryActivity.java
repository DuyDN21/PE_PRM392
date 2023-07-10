package com.example.pe_prm392.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pe_prm392.Adapter.CountryListAdapter;
import com.example.pe_prm392.Models.AppDatabase;
import com.example.pe_prm392.Models.Country;
import com.example.pe_prm392.R;

import java.util.List;

public class ManageCountryActivity extends AppCompatActivity {

    EditText edtName;
    EditText edtRank;
    EditText edtYear;
    EditText edtGDP;
    AppDatabase myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_country);

        edtName = (EditText)findViewById(R.id.editTextName);
        edtRank = (EditText)findViewById(R.id.editTextRank);
        edtYear = (EditText)findViewById(R.id.editTextYear);
        edtGDP = (EditText)findViewById(R.id.editTextGDP);
        myDB = AppDatabase.getInMemoryDatabase(getApplicationContext());

        generateCountries();

        List<Country> countryList = myDB.dao().getAllCountries();
        CountryListAdapter countryListAdapter = new CountryListAdapter(countryList);
        RecyclerView countryHistoryView = (RecyclerView) findViewById(R.id.rv_country_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        countryHistoryView.setLayoutManager(layoutManager);
        countryHistoryView.setAdapter(countryListAdapter);

        ((Button)findViewById(R.id.btnAdd)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String name = edtName.getText().toString();
                    int rank = Integer.parseInt(edtRank.getText().toString());
                    String year = edtYear.getText().toString();
                    long gdp = Long.parseLong(edtGDP.getText().toString());

                    Country c = new Country(name,rank,gdp,year);

                    myDB.dao().insert(c);
                    Toast.makeText(getApplicationContext(), "Insert Successful!", Toast.LENGTH_LONG).show();
                    countryListAdapter.setCountryList(myDB.dao().getAllCountries());
                    countryListAdapter.notifyDataSetChanged();
                }catch (Exception ex){
                    Toast.makeText(getApplicationContext(), "Insert Failed!", Toast.LENGTH_LONG).show();
                }
            }
        });

        ((Button)findViewById(R.id.btnDelete)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Country c = myDB.dao().getCountryByName(edtName.getText().toString());
                    myDB.dao().delete(c);
                    Toast.makeText(getApplicationContext(), "Delete Successful!", Toast.LENGTH_LONG).show();
                    countryListAdapter.setCountryList(myDB.dao().getAllCountries());
                    countryListAdapter.notifyDataSetChanged();
                }catch (Exception ex){
                    Toast.makeText(getApplicationContext(), "Delete Failed!", Toast.LENGTH_LONG).show();
                }
            }
        });

        ((Button)findViewById(R.id.btnUpdate)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Country c = myDB.dao().getCountryByName(edtName.getText().toString());
                    c.setGdppc(Long.parseLong(edtGDP.getText().toString()));
                    c.setRank(Integer.parseInt(edtRank.getText().toString()));
                    c.setYear(edtYear.getText().toString());
                    myDB.dao().updateCountry(c);
                    Toast.makeText(getApplicationContext(), "Update Successful!", Toast.LENGTH_LONG).show();
                    countryListAdapter.setCountryList(myDB.dao().getAllCountries());
                    countryListAdapter.notifyDataSetChanged();
                }catch (Exception ex){
                    Toast.makeText(getApplicationContext(), "Update Failed!", Toast.LENGTH_LONG).show();
                }
            }
        });

        ((Button)findViewById(R.id.btnList)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(edtName.getText()) ||
                        !TextUtils.isEmpty(edtGDP.getText()) ||
                        !TextUtils.isEmpty(edtRank.getText()) ||
                        !TextUtils.isEmpty(edtYear.getText())){
                    if(!TextUtils.isEmpty(edtGDP.getText()) && !TextUtils.isEmpty(edtRank.getText())){
                        countryListAdapter.setCountryList(myDB.dao().searchCountry
                                (edtName.getText().toString(),
                                        edtYear.getText().toString(),
                                        Integer.parseInt(edtRank.getText().toString()),
                                        Long.parseLong(edtGDP.getText().toString())));
                    }else if(TextUtils.isEmpty(edtGDP.getText()) && !TextUtils.isEmpty(edtRank.getText())){
                        countryListAdapter.setCountryList(myDB.dao().searchCountry3
                                (edtName.getText().toString(),
                                        edtYear.getText().toString(),
                                        Integer.parseInt(edtRank.getText().toString())));
                    } else if (!TextUtils.isEmpty(edtGDP.getText()) && TextUtils.isEmpty(edtRank.getText())) {
                        countryListAdapter.setCountryList(myDB.dao().searchCountry2
                                (edtName.getText().toString(),
                                        edtYear.getText().toString(),
                                        Long.parseLong(edtGDP.getText().toString())));
                    }else{
                        countryListAdapter.setCountryList(myDB.dao().searchCountry1
                                (edtName.getText().toString(),
                                        edtYear.getText().toString()));
                    }
                    countryListAdapter.notifyDataSetChanged();
                }else{
                    countryListAdapter.setCountryList(myDB.dao().getAllCountries());
                    countryListAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void generateCountries() {
        myDB.dao().insert(new Country("VietNam", 1, 200, "2023"));
        myDB.dao().insert(new Country("Thailand", 4, 100, "2023"));
        myDB.dao().insert(new Country("China", 2, 120, "2023"));
        myDB.dao().insert(new Country("Singapore", 2, 150, "2023"));
        myDB.dao().insert(new Country("Laos", 2, 120, "2023"));
        myDB.dao().insert(new Country("Cambodia", 3, 90, "2023"));
    }
}