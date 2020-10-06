package com.hmeng.calculateforme.ui.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.google.gson.Gson;
import com.hmeng.calculateforme.CommunityApplication;
import com.hmeng.calculateforme.Data.BalconyRadiusOperationTask;
import com.hmeng.calculateforme.Data.SharedPreferencesManager;
import com.hmeng.calculateforme.Helpers.LocaleHelper;
import com.hmeng.calculateforme.R;
import com.hmeng.calculateforme.Utils.Constants;
import com.hmeng.calculateforme.Utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SettingsFragment extends Fragment {
    private LinearLayout mLlLanguage;
    private TextView mTvLanguage;
    private AlertDialog ad;
    private LinearLayout mLlMeasureUnit;
    private TextView mTvUnit;
/*
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        //setPreferencesFromResource(R.xml.root_preferences, rootKey);

    }*/

    void init( View root) {
         mLlLanguage = root.findViewById(R.id.ll_language);
         mTvLanguage = root.findViewById(R.id.tv_language);
         mLlMeasureUnit = root.findViewById(R.id.ll_measure_unit);
         mTvUnit = root.findViewById(R.id.tv_unit);
        setDefaultLangAndUnit();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        init(root);
        mLlLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                CharSequence[] languages = getResources().getTextArray(R.array.languages);

                builder.setItems(languages, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0: // English
                                //changeLanguage("en");
                                setNewLocale(LocaleHelper.ENGLISH);
                                break;
                            case 1: // Arabic
                                //changeLanguage("ar");
                                setNewLocale(LocaleHelper.ARABIC);
                                break;


                            default:
                                break;
                        }
                    }
                });
                AlertDialog changeLangsDialog = builder.create();
                changeLangsDialog.setCancelable(true);
                changeLangsDialog.setCanceledOnTouchOutside(true);
                changeLangsDialog.show();
            }
        });

        mLlMeasureUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                CharSequence[] units = getResources().getTextArray(R.array.unit_entries);

                builder.setItems(units, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0: // English
                                setDefaultUnit("cm");
                                break;
                            case 1: // Arabic
                                setDefaultUnit("m");
                                break;


                            default:
                                break;
                        }
                    }
                });
                AlertDialog changeLangsDialog = builder.create();
                changeLangsDialog.show();
            }
        });
        return root;
    }
    private void setNewLocale( @LocaleHelper.LocaleDef String language) {
        LocaleHelper.setNewLocale(getActivity(), language);
        if (language.equals("en")) {
            mTvLanguage.setText(getResources().getString(R.string.english));
        } else {
            mTvLanguage.setText(getResources().getString(R.string.arabic));
        }
        Intent intent = getActivity().getIntent();
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }
    public void setDefaultUnit(String unit) {
        //String mesureUnit = SharedPreferencesManager.readFromPreferences(getContext(), null, Constants.MESURE_UNIT,"cm");
        if ("cm".equals(unit)) {
            mTvUnit.setText(getResources().getString(R.string.cm));
        } else {
            mTvUnit.setText(getResources().getString(R.string.unit_meter));
        }
        SharedPreferencesManager.saveToPreferences(getContext(),null, Constants.MESURE_UNIT,unit);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(LocaleHelper.setLocale(context));
    }
    void setDefaultLangAndUnit() {
        String mesureUnit = SharedPreferencesManager.readFromPreferences(getContext(), SharedPreferencesManager.DEFAULT_FILE_NAME, Constants.MESURE_UNIT, "cm");
        String lang = Locale.getDefault().getLanguage();
        if("cm".equals(mesureUnit)) {
            mTvUnit.setText(getResources().getString(R.string.cm));
        } else {
            mTvUnit.setText(getResources().getString(R.string.unit_meter));
        }

        if("en".equals(lang)) {
            mTvLanguage.setText(getResources().getString(R.string.english));
        } else {
            mTvLanguage.setText(getResources().getString(R.string.arabic));
        }

    }
}