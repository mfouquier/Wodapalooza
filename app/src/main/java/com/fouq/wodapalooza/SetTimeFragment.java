package com.fouq.wodapalooza;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

public class SetTimeFragment extends AppCompatDialogFragment {

    private EditText editWorkoutTime;
    private setTimeListener listener;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.settime, null);

        builder.setView(view)
                .setTitle("Set Workout Time")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String workoutTime = editWorkoutTime.getText().toString();
                listener.applyTime(workoutTime);
            }
        });

        editWorkoutTime = view.findViewById(R.id.setTimeEditText);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (setTimeListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement setTimeListener");
        }
    }

    public interface setTimeListener {
        void applyTime(String time);
    }

}
