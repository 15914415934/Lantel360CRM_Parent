package com.lantel.mine.list.holder;

import android.view.View;
import android.widget.TextView;
import com.lantel.crmparent.R;
import com.xiao360.baselibrary.listview.BaseViewHolder;

public class CardHolder extends BaseViewHolder {
    public TextView title;
    public TextView value;

    public CardHolder(View view) {
        super(view);
        title = getView(R.id.title);
        value = getView(R.id.value);
    }
}
