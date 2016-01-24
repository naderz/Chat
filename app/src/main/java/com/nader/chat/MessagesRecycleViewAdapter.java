package com.nader.chat;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nader.chat.models.ChatMessage;
import com.nader.chat.utils.Emoticons;
import com.nader.chat.utils.MessageParser;
import com.nader.chat.utils.finders.Finder;

import java.util.List;

/**
 * Created by nader on 24/01/16.
 */
public class MessagesRecycleViewAdapter extends RecyclerView.Adapter<MessagesRecycleViewAdapter.CustomViewHolder> {
    private List<ChatMessage> mMessageList;
    private Context mContext;

    public MessagesRecycleViewAdapter(Context context, List<ChatMessage> mMessageList) {
        this.mMessageList = mMessageList;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_message_from, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        ChatMessage message = mMessageList.get(i);

        customViewHolder.senderTextView.setText(message.getSender());
        customViewHolder.messageTextView.setText(replaceMatchesWithContent(message.getMessageMatches()));

        View.OnLongClickListener onLongClickListener = setCopyMessageOnLongClick();

        //Handle click event on both title and image click
        customViewHolder.messageContainer.setOnLongClickListener(onLongClickListener);
        customViewHolder.messageContainer.setTag(customViewHolder);

    }

    private int getThemeAttributeValue(int attr) {
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = mContext.getTheme();
        theme.resolveAttribute(attr, typedValue, true);
        return typedValue.data;
    }

    private SpannableStringBuilder replaceMatchesWithContent(MessageParser.Matches matches) {
        SpannableStringBuilder builder = new SpannableStringBuilder(matches.originalString);

        for (Finder.Match match : matches.mentions) {
            builder.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), match.startIndex, match.endIndex, 0);
        }

        for (Finder.Match match : matches.emoticons) {
            if (Emoticons.sEmoticonMap.containsKey(match.string)) {
                builder.setSpan(new ImageSpan(mContext, Emoticons.sEmoticonMap.get(match.string)), match.startIndex, match.endIndex, 0);
                builder.setSpan(new ForegroundColorSpan(getThemeAttributeValue(R.attr.colorPrimaryDark)), match.startIndex, match.endIndex, 0);
            }
        }

        return builder;
    }

    private View.OnLongClickListener setCopyMessageOnLongClick() {
        return new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                CustomViewHolder holder = (CustomViewHolder) view.getTag();
                int position = holder.getAdapterPosition();

                ChatMessage chatMessage = mMessageList.get(position);

                ClipboardManager clipboard = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText(chatMessage.getSender(), chatMessage.getMessageContent());
                clipboard.setPrimaryClip(clipData);
                Toast.makeText(mContext, "Message Copied", Toast.LENGTH_SHORT).show();
                return true;
            }
        };
    }

    @Override
    public int getItemCount() {
        return (mMessageList != null ? mMessageList.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView senderTextView;
        TextView messageTextView;
        View messageContainer;

        public CustomViewHolder(View view) {
            super(view);
            senderTextView = (TextView) view.findViewById(R.id.message_sender);
            messageTextView = (TextView) view.findViewById(R.id.message_content);
            messageContainer = view.findViewById(R.id.message_container);
        }
    }
}