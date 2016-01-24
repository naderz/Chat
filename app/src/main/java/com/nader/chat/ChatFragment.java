package com.nader.chat;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.os.AsyncTaskCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.nader.chat.models.MessageMetaData;
import com.nader.chat.utils.MessageParser;


public class ChatFragment extends Fragment {

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

        getView().findViewById(R.id.btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText messageText = (EditText) getView().findViewById(R.id.et_message);
                parseMessage(messageText.getText().toString());
            }
        });
    }

    private void parseMessage(final String message) {

        AsyncTaskCompat.executeParallel(new AsyncTask<Void, Void, MessageMetaData>() {
            @Override
            protected MessageMetaData doInBackground(Void... params) {
                return MessageParser.getMessageMetaData(message);

            }

            @Override
            protected void onPostExecute(MessageMetaData metaData) {
                //TODO:
                Toast.makeText(ChatFragment.this.getActivity(), "Done", Toast.LENGTH_LONG);

            }
        });


    }


}
