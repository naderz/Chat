package com.nader.chat.shared.emoticons;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nader.chat.R;

import java.util.ArrayList;
import java.util.List;

public class EmoticonsAdapter extends ArrayAdapter<Emoticons.Emoticon> {

    private Context mContext;
    private int mLayoutResourceId;
    private List<Emoticons.Emoticon> mEmoticons;
    private List<Emoticons.Emoticon> mAutoCompleteSuggestions = new ArrayList<>();
    Filter shortCutFilter = new Filter() {
        public String convertResultToString(Object resultValue) {
            return ((Emoticons.Emoticon) (resultValue)).shortcut;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                mAutoCompleteSuggestions.clear();
                for (Emoticons.Emoticon emoticon : mEmoticons) {
                    if (emoticon.shortcut.toLowerCase()
                            .startsWith(constraint.toString().toLowerCase())) {
                        mAutoCompleteSuggestions.add(emoticon);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mAutoCompleteSuggestions;
                filterResults.count = mAutoCompleteSuggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            ArrayList<Emoticons.Emoticon> filteredList = (ArrayList<Emoticons.Emoticon>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (Emoticons.Emoticon c : filteredList) {
                    add(c);
                }
                return;
            }
        }
    };

    public EmoticonsAdapter(Context context, int layoutResourceId, List<Emoticons.Emoticon> emoticons) {
        super(context, layoutResourceId, emoticons);
        mLayoutResourceId = layoutResourceId;
        mContext = context;
        mEmoticons = new ArrayList<>(emoticons);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(mLayoutResourceId, null);
        }
        Emoticons.Emoticon customer = getItem(position);
        TextView customerNameLabel = (TextView) v.findViewById(R.id.emoticon_text);
        customerNameLabel.setText(customer.shortcut);

        ImageView emoticonImage = (ImageView) v.findViewById(R.id.emoticon_img);
        emoticonImage.setImageResource(customer.resource);

        return v;
    }

    @Override
    public Filter getFilter() {
        return shortCutFilter;
    }

}

