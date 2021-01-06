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

public class NewFragment extends Fragment {
    private TextView tvNews;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news,container,false);
        getViews(view);
        return view;
    }
    //获取视图的控件
    private void getViews(View view) {
        tvNews = view.findViewById(R.id.tvNews);
    }
}
