package br.org.venturus.venturus4tech;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private String[] mData = {"1","2","3","4","5"};

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inicialmente cria as views até atingir o tamanho max

        // Create a new View
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_list_item,parent,false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // A partir do momento no qual todas as foram criadas, apenas atualiza as views

        holder.mLinearLayout.setText(mData[position]);
    }

    @Override
    public int getItemCount() {
        return mData.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout mLinearLayout;
        public ViewHolder(LinearLayout v) {
            super(v);
            mLinearLayout = v;
        }
    }
}
