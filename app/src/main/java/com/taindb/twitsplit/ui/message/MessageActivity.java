package com.taindb.twitsplit.ui.message;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.taindb.twitsplit.R;
import com.taindb.twitsplit.SimpleTextWatcher;

import java.util.List;

/**
 * Copyright (C) 2018, Taindb.
 *
 * @author taindb
 * @since 4/2/18
 */

public class MessageActivity extends AppCompatActivity implements View.OnClickListener, MessageView {

    private ImageView mBackBtn;

    private ImageView mSendBtn;

    private RecyclerView mMessageList;

    private EditText mInputMessage;

    private final MessageAdapter mMessageAdapter = new MessageAdapter();

    private final MessagePresenter mPresenter = new MessagePresenter();

    private final Object mLock = new Object();

    // check state of edittext to set send message button state
    private final SimpleTextWatcher mInputMessageWatcher = new SimpleTextWatcher() {
        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            super.onTextChanged(charSequence, start, before, count);
            int sendBtnResId = TextUtils.isEmpty(charSequence) ? R.mipmap.ic_send_message_normal : R.mipmap.ic_send_message_activated;
            mSendBtn.setImageResource(sendBtnResId);
        }
    };

    private void initView() {
        setContentView(R.layout.activity_message);
        mBackBtn = findViewById(R.id.back_btn);
        mBackBtn.setOnClickListener(this);

        mSendBtn = findViewById(R.id.send_message_btn);
        mSendBtn.setOnClickListener(this);

        mInputMessage = findViewById(R.id.input_message_edt);
        mInputMessage.addTextChangedListener(mInputMessageWatcher);

        mMessageList = findViewById(R.id.message_list);
        setUpMessageList();
    }

    private void setUpMessageList() {
        final LinearLayoutManager lLinearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean supportsPredictiveItemAnimations() {
                return true;
            }
        };
        mMessageList.setLayoutManager(lLinearLayoutManager);
        mMessageList.setAdapter(mMessageAdapter);
    }

    private void smoothScrollToBottom() {
        synchronized (mLock) {
            if (mMessageAdapter.getItemCount() > 0) {
                if (mMessageList != null) {
                    mMessageList.smoothScrollToPosition(mMessageAdapter.getItemCount() - 1);
                }
            }
        }
    }

    private void resetInputMessage() {
        if (mInputMessage != null) {
            mInputMessage.setText(null);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        mPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_btn:
                onBackPressed();
                break;

            case R.id.send_message_btn:
                mPresenter.sendMessage(mInputMessage.getText().toString());
                break;
        }
    }

    @Override
    public void appendMessage(List<String> messageData) {
        if (!isFinishing()) {
            mMessageAdapter.appendItems(messageData);
            smoothScrollToBottom();
            resetInputMessage();
        }
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getApplicationContext(),  error, Toast.LENGTH_LONG).show();
    }
}
