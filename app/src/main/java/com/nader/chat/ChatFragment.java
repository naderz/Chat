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

import java.util.ArrayList;
import java.util.List;


public class ChatFragment extends Fragment {

    private List<ChatMessage> mMessageList;
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
        //TODO: Get message history from a source stored locally and Or from a service?.
        if (mMessageAdapter == null) {
            mMessageList = new ArrayList<>();
            mMessageAdapter = new MessagesRecycleViewAdapter(getActivity(), mMessageList);
        }
        mMessagesRecyclerView = (RecyclerView) getView().findViewById(R.id.message_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setStackFromEnd(false);
        layoutManager.setReverseLayout(true);
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
                postMessage(mMessageEditText.getText().toString());
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

    private void postMessage(final String message) {
        final ChatController messageController = new ChatController();
        final ChatMessage chatMessage = messageController.createChatMessageItem(message);
        mMessageEditText.setText("");
        mMessageAdapter.insert(chatMessage);
        mMessagesRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                mMessagesRecyclerView.smoothScrollToPosition(0);
            }
        });

        AsyncTaskCompat.executeParallel(new AsyncTask<Void, Void, ChatMessage>() {
            @Override
            protected ChatMessage doInBackground(Void... params) {
                ChatMessage chatMessageReturned = messageController.postMessage(chatMessage);
                return chatMessageReturned;
            }

            @Override
            protected void onPostExecute(ChatMessage message) {
                mMessageAdapter.updateItem(message);
            }
        });
    }
}