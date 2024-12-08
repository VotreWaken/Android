package com.example.androidapplication;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidapplication.ChatActivity;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    private final List<ChatActivity.ChatMessage> chatMessages;
    private final String userNick;

    public ChatAdapter(List<ChatActivity.ChatMessage> chatMessages, String userNick) {
        this.chatMessages = chatMessages;
        this.userNick = userNick;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId = (viewType == 0) ? R.layout.chat_msg_own : R.layout.chat_msg_other;
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        ChatActivity.ChatMessage message = chatMessages.get(position);
        holder.bind(message);
    }

    @Override
    public int getItemViewType(int position) {
        return chatMessages.get(position).getAuthor().equals(userNick) ? 0 : 1;
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    static class ChatViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvMessage;
        private final TextView tvAuthor;
        private final TextView tvMoment;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMessage = itemView.findViewById(R.id.tvMessage);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            tvMoment = itemView.findViewById(R.id.tvMoment);
        }

        public void bind(ChatActivity.ChatMessage message) {
            tvMessage.setText(message.getText());
            tvAuthor.setText(message.getAuthor());
            tvMoment.setText(message.getMoment());
        }
    }
}
