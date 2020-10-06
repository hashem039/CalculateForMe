package com.hmeng.calculateforme.ui.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;

import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hmeng.calculateforme.Data.BalconyRadiusOperationTask;
import com.hmeng.calculateforme.Data.SharedPreferencesManager;
import com.hmeng.calculateforme.Helpers.LocaleHelper;
import com.hmeng.calculateforme.R;
import com.hmeng.calculateforme.Utils.Constants;
import com.hmeng.calculateforme.Utils.Utils;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.content.pm.PackageManager.GET_META_DATA;

public class BalconyRadius extends AppCompatActivity {
    private EditText mEtWidth;
    private EditText mEtLength;
    private TextView mTvRadiud;
    private LinearLayout mLlInputContainer;
    private Button mSubmit;
    private Button mReset;
    RadioGroup radioGroup;
    private TextView mTvWidthUnit;
    private TextView mTvLengthUnit;
    private TextView mTvRadiusUnit;
    private String measureUnit;
    private RadioButton radioDefaultButton;

    public void init() {
        mEtWidth = findViewById(R.id.ed_balcony_width_value);
        mEtLength = findViewById(R.id.ed_balcony_length_value);
        mTvRadiud = findViewById(R.id.tv_radius_value);
        mTvRadiud = findViewById(R.id.tv_radius_value);
        mTvRadiud = findViewById(R.id.tv_radius_value);
        mTvWidthUnit = findViewById(R.id.tv_width_unit);
        mTvLengthUnit = findViewById(R.id.tv_length_unit);
        mTvRadiusUnit = findViewById(R.id.tv_radius_unit);
        mLlInputContainer = findViewById(R.id.ll_balcony_data_input);
        mSubmit = findViewById(R.id.bt_balcony_radius_button);
        mReset = findViewById(R.id.bt_reset_balcony_radius);
        radioGroup = (RadioGroup) findViewById(R.id.rg_mark_type);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balcony_radius);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.title_activity_balcony_radius));
        setSupportActionBar(toolbar);
        //toolbar.setTitle(getResources().getString(R.string.app_name));
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        init();
         measureUnit = SharedPreferencesManager.readFromPreferences(getApplicationContext(), null, Constants.MESURE_UNIT,"cm");
         if ("cm".equals(measureUnit)) {
             radioDefaultButton = radioGroup.findViewById(R.id.rb_unit_cm);
             showMeasureUnit(getResources().getString(R.string.cm));
         } else {
             radioDefaultButton = radioGroup.findViewById(R.id.rb_unit_m);
             showMeasureUnit(getResources().getString(R.string.unit_meter));
         }
         radioDefaultButton.setChecked(true);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_unit_cm:
                        showMeasureUnit(getResources().getString(R.string.cm));
                        break;
                    case R.id.rb_unit_m:
                        showMeasureUnit(getResources().getString(R.string.unit_meter));
                        break;
                }
            }
        });
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkInput(mEtWidth, mEtLength)) {
                    double width = Double.parseDouble(mEtWidth.getText().toString());
                    double length = Double.parseDouble(mEtLength.getText().toString());
                    String radius = Utils.arabicToDecimal(new DecimalFormat("#.###").format(calculateRadius(width, length)));
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mSubmit.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
                    mTvRadiud.setText(radius);
                    storeDataToSharedPreferences(new BalconyRadiusOperationTask(width, length, Double.parseDouble(radius), Utils.getFormatedDate(new Date()) + "\n   " + Utils.getFormatedTime(new Date())));

                } else {
                    Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.invalid_input), Toast.LENGTH_SHORT).show();
                }
            }
        });
        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetInput(mEtWidth, mEtLength, mTvRadiud);
            }
        });


    }
    public void storeDataToSharedPreferences(BalconyRadiusOperationTask task) {
        List<BalconyRadiusOperationTask> data = new ArrayList<>();
        List<BalconyRadiusOperationTask> newData = new ArrayList<>();
        Gson gson = new Gson();
        String json = SharedPreferencesManager.readFromPreferences(getApplicationContext(), "root_preferences",SharedPreferencesManager.BALCONY_RADIUS_SERVICE_DATA,null);
        Type type = new TypeToken<List<BalconyRadiusOperationTask>>() {}.getType();
        data = gson.fromJson(json, type);
        newData.add(task);
        if (data != null) {
            if (data.size() > 30) {
                data.remove(data.size() - 1);
            }

            newData.addAll(data);
        }
        String preTasksString = gson.toJson(newData);
        SharedPreferencesManager.saveToPreferences(getApplicationContext(),"root_preferences", SharedPreferencesManager.BALCONY_RADIUS_SERVICE_DATA,preTasksString);
    }
    public void showMeasureUnit(String unit) {
        mTvWidthUnit.setText(unit);
        mTvLengthUnit.setText(unit);
        mTvRadiusUnit.setText(unit);
    }
    public boolean checkInput(EditText in1, EditText in2) {
        if ("".equals(in1.getText().toString()) || "".equals(in2.getText().toString())) {
            return false;
        }
        return true;
    }
    public void resetInput(EditText in1, EditText in2, TextView tvRadius) {
        in1.getText().clear();
        in2.getText().clear();
        tvRadius.setText("");
    }
    public double calculateRadius(double width, double length) {
        return ((width * width) + 4 * (length * length))/(8 * length);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.setLocale(base));
    }

}