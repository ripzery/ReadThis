package com.onemorebit.readthis.fragment;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.onemorebit.readthis.FullscreenActivity;
import com.onemorebit.readthis.R;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PortraitFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PortraitFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "PortraitFragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String word = "";
    private TextView tvShow;
    private EditText etWord;
    private Button btnClose;
    private Button btnGo;

    public PortraitFragment() {
        // Required empty public constructor
    }

    public static PortraitFragment newInstance() {
        PortraitFragment fragment = new PortraitFragment();
        return fragment;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

            /* get string only from landscape */
        if (savedInstanceState != null) {
            if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                word = savedInstanceState.getString("word");
                tvShow.setText(word);
            } else if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                word = savedInstanceState.getString("word");
                etWord.setText(word);
            }
        }
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_portrait, container, false);
        tvShow = (TextView) rootView.findViewById(R.id.tvShow);
        etWord = (EditText) rootView.findViewById(R.id.etWord);
        btnClose = (Button) rootView.findViewById(R.id.btnClose);
        btnGo = (Button) rootView.findViewById(R.id.btnGo);
        if (etWord != null) {
            etWord.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                        return true;
                    }
                    return false;
                }
            });

            btnGo.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    word = etWord.getText().toString();
                    Timber.i("word :  %s", word);
                    startActivity(new Intent(getActivity(), FullscreenActivity.class).putExtra("word", word));
                }
            });
        } else {
            btnClose.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
            });
        }
        return rootView;
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        try {
            if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                word = etWord.getText().toString();
            }
            outState.putString("word", word);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onSaveInstanceState(outState);
    }
}
