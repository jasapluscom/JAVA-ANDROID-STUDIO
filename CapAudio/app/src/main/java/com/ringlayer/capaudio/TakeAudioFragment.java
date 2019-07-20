package com.ringlayer.capaudio;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.ringlayer.capaudio.R;
/*
 * Created by ringlayer on 24/11/18.
*/


public class TakeAudioFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TakeAudioFragment() {

    }

    public static TakeAudioFragment newInstance(String param1, String param2) {
        TakeAudioFragment fragment = new TakeAudioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_take_audio, container, false);
        try {
            final Button b = (Button) v.findViewById(R.id.tombol_audio);

            b.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        String aksi = b.getText().toString();
                        if (aksi != "Stop") {
                            b.setText("Stop");
                            ((MainActivity) getActivity()).RekamAudio();

                        }
                        else {
                            b.setText("Rekam Audio");
                            ((MainActivity) getActivity()).StopRekamAudio();
                        }

                    }
                });
        }
        catch (Exception e) {
            Log.e("Err : ", e.toString());
        }

        return v;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
