package com.fouq.wodapalooza;

/**
 * Author: Matthew Fouquier
 * Date: May 1, 2022
 * Project: Wodapalooza
 */

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.NumberPicker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DelayFragment extends AppCompatDialogFragment {
    private NumberPicker delayTime;
    private delayTimeListener delayListener;
    private int lastAction = -1;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.setdelay, null);

        delayTime = view.findViewById(R.id.delayPicker);
        delayTime.setMinValue(0);
        delayTime.setMaxValue(10);
        delayTime.setClickable(true);
        delayTime.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                delayListener.applyDelay(newVal * 1000);
            }
        });
        //CLOSE THE NUMBER PICKER ON A TOUCH DOWN UP
        delayTime.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                boolean solution = false;
                switch (event.getAction()) {
                    case (MotionEvent.ACTION_DOWN):
                        lastAction = MotionEvent.ACTION_DOWN;
                        solution = false;
                        break;
                    case (MotionEvent.ACTION_UP):
                        if (lastAction == MotionEvent.ACTION_DOWN) {
                            solution = true;
                        }
                        lastAction = MotionEvent.ACTION_UP;
                        break;
                    case (MotionEvent.ACTION_SCROLL):
                        lastAction = MotionEvent.ACTION_SCROLL;
                        solution = false;
                        break;
                    default:
                        lastAction = -1;
                        solution = false;
                        break;
                }
                if (solution) {
                    getDialog().dismiss();
                }
                return solution;
            }
        });

        builder.setView(view)
                .setTitle("Timer Delay");

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            delayListener = (delayTimeListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement delayTimeListener");
        }
    }

    public interface delayTimeListener {
        void applyDelay(int delay);
    }
}
