package com.example.listycitylab3;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class EditFragment extends DialogFragment{
    private City originalCity;
    private EditCityDialogListener listener;

    public interface EditCityDialogListener{
        void onCityEdited(City oldCity, City newCity);
    }
    public static EditFragment newInstance(City city){
        Bundle args = new Bundle();
        args.putSerializable("city", city);
        EditFragment fragment = new EditFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        if(context instanceof EditCityDialogListener){
            listener = (EditCityDialogListener) context;
        }
        else{
            throw new RuntimeException(context + "Must implement EditCityDialogListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_city, container, false);

        EditText editCityName = view.findViewById(R.id.edit_city_name);
        EditText editProvinceName = view.findViewById(R.id.edit_province_name);
        Button buttonSave = view.findViewById(R.id.button_save);
        Button buttonCancel = view.findViewById(R.id.button_cancel);

        if (getArguments() != null) {
            originalCity = (City) getArguments().getSerializable("city");
            if (originalCity != null) {
                editCityName.setText(originalCity.getCityName());
                editProvinceName.setText(originalCity.getProvince());
            }
        }

        buttonSave.setOnClickListener(v -> {
            String newCityName = editCityName.getText().toString().trim();
            String newProvince = editProvinceName.getText().toString().trim();

            if (!newCityName.isEmpty() && !newProvince.isEmpty()) {
                City updatedCity = new City(newCityName, newProvince);
                listener.onCityEdited(originalCity, updatedCity);
                dismiss();
            }
        });

        buttonCancel.setOnClickListener(v -> dismiss());

        return view;
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Prevent keyboard dismissing fragment
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }
}





