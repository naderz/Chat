package com.nader.chat;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.os.AsyncTaskCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;

import com.nader.chat.models.ChatMessage;
import com.nader.chat.utils.MessageParser;

import java.util.ArrayList;
import java.util.List;


public class ChatFragment extends Fragment {

    private List<ChatMessage> messageList = new ArrayList<>();
    private MessagesRecycleViewAdapter mMessageAdapter;
    private RecyclerView mMessagesRecyclerView;
    private AutoCompleteTextView mMessageEditText;
    private ImageButton mSendBtn;

    public ChatFragment() {
    }

    public static ChatFragment createInstance() {
        return new ChatFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initMessageRecyclerView();

        initSendButton();

        addTextWatcherToMessageBox();
    }

    private void initMessageRecyclerView() {
        //TODO: Get message history from a source stored locally? and Or from a service?
        mMessageAdapter = new MessagesRecycleViewAdapter(getActivity(), messageList);
        mMessagesRecyclerView = (RecyclerView) getView().findViewById(R.id.message_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setStackFromEnd(true);
        mMessagesRecyclerView.setLayoutManager(layoutManager);
        mMessagesRecyclerView.setAdapter(mMessageAdapter);
    }

    private void initSendButton() {
        mSendBtn = (ImageButton) getView().findViewById(R.id.btn_send);
        mMessageEditText = (AutoCompleteTextView) getView().findViewById(R.id.et_message);

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,countries);
//        actv.setAdapter(adapter);

        mSendBtn.setEnabled(false);

        mSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseMessage(mMessageEditText.getText().toString());
            }
        });
    }

    private void addTextWatcherToMessageBox() {
        mMessageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mSendBtn.setEnabled(!s.toString().trim().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void parseMessage(final String message) {
        AsyncTaskCompat.executeParallel(new AsyncTask<Void, Void, ChatMessage>() {
            @Override
            protected ChatMessage doInBackground(Void... params) {
                MessageParser messageParser = new MessageParser();
                ChatMessage chatMessage = messageParser.generateChatMessage(message);
                return chatMessage;
            }

            @Override
            protected void onPostExecute(ChatMessage message) {
                messageList.add(message);
                mMessageEditText.setText("");
                int lastItem = mMessageAdapter.getItemCount() - 1;
                mMessagesRecyclerView.smoothScrollToPosition(lastItem);
                mMessageAdapter.notifyItemInserted(lastItem);

            }
        });
    }

}