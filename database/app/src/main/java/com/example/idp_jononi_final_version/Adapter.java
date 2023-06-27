package com.example.idp_jononi_final_version;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.Collection;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    ArrayList<Modelclass> arrayList;
    Context context;

    public Adapter(ArrayList<Modelclass> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    @NonNull
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_design,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void  onBindViewHolder(@NonNull final ViewHolder holder,  int position) {
               holder.tv_query.setText(arrayList.get(holder.getAbsoluteAdapterPosition()).question);
               holder.tv_ans.setText(arrayList.get(holder.getAbsoluteAdapterPosition()).answer);

               holder.tv_query.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       if(arrayList.get(holder.getAbsoluteAdapterPosition()).isVisible){
                           holder.tv_ans.setVisibility(View.GONE);
                           holder.rl_ans_line.setVisibility(View.GONE);
                           holder.rl_query_line.setVisibility(View.VISIBLE);
                           arrayList.get(holder.getAbsoluteAdapterPosition()).isVisible=false;

                       }
                       else{
                           holder.tv_ans.setVisibility(View.VISIBLE);
                           holder.rl_ans_line.setVisibility(View.VISIBLE);
                           holder.rl_query_line.setVisibility(View.GONE);
                           arrayList.get(holder.getAbsoluteAdapterPosition()).isVisible=true;

                       }
                   }
               });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_query;
        TextView tv_ans;
        RelativeLayout rl_query_line;
        RelativeLayout rl_ans_line;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_query=itemView.findViewById(R.id.query);
            tv_ans=itemView.findViewById(R.id.ans);
            rl_ans_line=itemView.findViewById(R.id.rl_ans_line);
            rl_query_line=itemView.findViewById(R.id.rl_query_line);


        }
    }
}
