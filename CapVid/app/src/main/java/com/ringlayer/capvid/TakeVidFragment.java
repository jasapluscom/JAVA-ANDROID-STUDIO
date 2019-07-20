package com.ringlayer.capvid;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/*
 * Created by ringlayer on 24/11/18.
*/

public class TakeVidFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TakeVidFragment() {

    }


    public static TakeVidFragment newInstance(String param1, String param2) {
        TakeVidFragment fragment = new TakeVidFragment();
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
        View v = inflater.inflate(R.layout.fragment_take_vid, container, false);
        try {
            Button b = (Button) v.findViewById(R.id.tombol_foto);
            boolean front_cam_enable = CheckCamera("front");
            if (front_cam_enable == true) {
                Log.d("onCreate : ", "front camera enabled");
            }
            boolean rear_cam_enable = CheckCamera("rear");
            if (rear_cam_enable == true) {
                Log.d("onCreate : ", "rear camera enabled");
            }

            if (front_cam_enable == true || rear_cam_enable == true) {
                Button button = (Button) v.findViewById(R.id.tombol_foto);
                button.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        ((MainActivity) getActivity()).RekamVideo();
                    }
                });
            }

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

    private boolean CheckCamera(String front_or_back) {
        boolean res;
        if (front_or_back == "front") {
            res = getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT);
        }
        else {
            res = getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
        }

        return res;
    }
}
