package com.lantel.studylibrary.classes.list.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.lantel.homelibrary.R;
import com.lantel.studylibrary.classes.list.holder.ClassesHolder;
import com.lantel.studylibrary.classes.list.model.ClassesCardModel;
import com.xiao360.baselibrary.listview.BaseRecyclerViewAdapter;
import com.xiao360.baselibrary.listview.BaseViewHolder;

import java.util.List;

public class ClassesAdapter extends BaseRecyclerViewAdapter<ClassesCardModel> {
    public ClassesAdapter(Context context, List<ClassesCardModel> datas) {
        super(context, datas);
    }

    @Override
    protected BaseViewHolder CreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.classes_item, parent, false);
        return new ClassesHolder(view);
    }

    @Override
    protected void bindViewHolder(BaseViewHolder holder, ClassesCardModel data, int position, int viewType) {
        ClassesHolder classesHolder = (ClassesHolder) holder;

    }
}
