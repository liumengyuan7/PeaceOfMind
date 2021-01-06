package cn.edu.peaceofmind.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import cn.edu.peaceofmind.R;

public class ReadFragment extends Fragment {
    private TextView tvRead;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read,container,false);
        getViews(view);
        return view;
    }
    //获取视图的控件
    private void getViews(View view) {
        tvRead = view.findViewById(R.id.tvRead);
    }
}
