package com.nader.chat;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.util.SortedListAdapterCallback;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nader.chat.models.ChatMessage;
import com.nader.chat.shared.Emoticons;
import com.nader.chat.shared.message.MessageParser;
import com.nader.chat.shared.message.finders.Finder;
import com.nader.chat.utils.ThemeUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessagesRecycleViewAdapter extends RecyclerView.Adapter<MessagesRecycleViewAdapter.MessageViewHolder> {
    final Map<String, ChatMessage> mUniqueMapping = new HashMap<>();
    private SortedList<ChatMessage> mMessageList;
    private Context mContext;

    public MessagesRecycleViewAdapter(Context context, List<ChatMessage> messageList) {
        this.mContext = context;

        mMessageList = new SortedList<>(ChatMessage.class,
                new SortedListAdapterCallback<ChatMessage>(this) {
                    @Override
                    public void onChanged(int position, int count) {
                        super.onChanged(position, count);
                    }

                    @Override
                    public int compare(ChatMessage o1, ChatMessage o2) {

                        return (int) (o2.getLastEdited() - o1.getLastEdited());
                    }

                    @Override
                    public boolean areContentsTheSame(ChatMessage oldItem,
                                                      ChatMessage newItem) {
                        if (!oldItem.getMessageContent().equals(newItem.getMessageContent())) {
                            return false;
                        }
                        return oldItem.isPending() == newItem.isPending();
                    }

                    @Override
                    public boolean areItemsTheSame(ChatMessage item1, ChatMessage item2) {
                        return item1.getId().equals(item2.getId());
                    }
                });

        mMessageList.addAll(messageList);
    }

    public void updateItem(ChatMessage message) {
        ChatMessage existing = mUniqueMapping.get(message.getId());
        if (existing == null) {
            Log.i("Nothing to update", message.getId());
            return;
        }
        int pos = mMessageList.indexOf(existing);
        mUniqueMapping.put(message.getId(), message);
        mMessageList.updateItemAt(pos, message);
    }

    public void insert(ChatMessage item) {
        ChatMessage existing = mUniqueMapping.put(item.getId(), item);
        if (existing == null) {
            mMessageList.add(item);
        } else {
            int pos = mMessageList.indexOf(existing);
            mMessageList.updateItemAt(pos, item);
        }
    }

    private SpannableStringBuilder replaceMatchesWithContent(String message) {
        SpannableStringBuilder builder = new SpannableStringBuilder(message);

        MessageParser.Matches matches = new MessageParser().findMatches(message);

        for (Finder.Match match : matches.mentions) {
            builder.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), match.startIndex, match.endIndex, 0);
        }

        for (Finder.Match match : matches.emoticons) {
            if (Emoticons.sEmoticonMap.containsKey(match.string)) {
                builder.setSpan(new ImageSpan(mContext, Emoticons.sEmoticonMap.get(match.string).resource),
                        match.startIndex, match.endIndex, 0);
                builder.setSpan(new ForegroundColorSpan(ThemeUtils.getThemeAttributeValue(R.attr.colorPrimaryDark, mContext)),
                        match.startIndex, match.endIndex, 0);
            }
        }
        return builder;
    }

    private View.OnLongClickListener setCopyMessageOnLongClick() {
        return new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                MessageViewHolder holder = (MessageViewHolder) view.getTag();
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
    public MessageViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_message_from, viewGroup, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder messageViewHolder, int i) {
        ChatMessage message = mMessageList.get(i);

        messageViewHolder.senderTextView.setText(message.getSender());
        messageViewHolder.messageTextView.setText(replaceMatchesWithContent(message.getMessageContent()));

        View.OnLongClickListener onLongClickListener = setCopyMessageOnLongClick();

        messageViewHolder.messageContainer.setOnLongClickListener(onLongClickListener);
        messageViewHolder.messageContainer.setTag(messageViewHolder);

        messageViewHolder.progressBar.setVisibility(message.isPending() ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return (mMessageList != null ? mMessageList.size() : 0);
    }

    class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView senderTextView;
        TextView messageTextView;
        View messageContainer;
        ProgressBar progressBar;

        public MessageViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
            senderTextView = (TextView) view.findViewById(R.id.message_sender);
            messageTextView = (TextView) view.findViewById(R.id.message_content);
            messageContainer = view.findViewById(R.id.message_container);
        }
    }
}