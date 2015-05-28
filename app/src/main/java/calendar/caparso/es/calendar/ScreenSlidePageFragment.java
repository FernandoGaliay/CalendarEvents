package calendar.caparso.es.calendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Fernando Galiay on 28/05/2015.
 */
public class ScreenSlidePageFragment extends Fragment {

    public static final String KEY_MILISECONDS = "KEY_MILISECONDS";

    private Long miliseconds;

    public static ScreenSlidePageFragment newInstance(Long miliseconds) {
        ScreenSlidePageFragment f = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putLong(KEY_MILISECONDS, miliseconds);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        miliseconds = getArguments().getLong(KEY_MILISECONDS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_pager, container, false);
        TextView tvFecha = (TextView) rootView.findViewById(R.id.tv_fecha);
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = new Date(miliseconds);
        tvFecha.setText(sf.format(fecha));
        return rootView;
    }
}