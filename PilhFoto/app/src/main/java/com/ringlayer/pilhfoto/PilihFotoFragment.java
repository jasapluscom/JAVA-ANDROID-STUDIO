package com.ringlayer.pilhfoto;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.graphics.BitmapFactory;
import com.ringlayer.pilhfoto.R;
import java.io.File;

/*
 * Created by ringlayer on 24/11/18.
*/

public class PilihFotoFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    public PilihFotoFragment() {

    }
    public static PilihFotoFragment newInstance(String param1, String param2) {
        PilihFotoFragment fragment = new PilihFotoFragment();
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
        View v = inflater.inflate(R.layout.fragment_pilih_foto, container, false);
        try {

            Button button = (Button) v.findViewById(R.id.tombol_foto);
            button.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                    {
                        ((MainActivity) getActivity()).PilihFoto();
                    }
            });
        }
        catch (Exception e) {
            Log.e("Err : ", e.toString());
        }

        return v;
    }

    public void SetFotoImageViewFromPath(String full_path) {
        try {
            File imgFile = new  File(full_path);

            if(imgFile.exists()) {
                ImageView foto_view = (ImageView) getActivity().findViewById(R.id.foto_view);
                foto_view.setImageBitmap(loadImage(full_path));
            }
            Log.d("SetFototoImageView : ", full_path);
        }
        catch (Exception e) {
            Log.e("Err : ", e.toString());
        }
    }
    private Bitmap loadImage(String imgPath) {
        BitmapFactory.Options options;
        try {
            options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap bitmap = BitmapFactory.decodeFile(imgPath, options);
            return bitmap;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
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
