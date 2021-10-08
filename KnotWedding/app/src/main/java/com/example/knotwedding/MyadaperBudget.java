package com.example.knotwedding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyadaperBudget extends RecyclerView.Adapter<com.example.knotwedding.MyadaperBudget.MyViewHolder> {
    ArrayList<ModelBudget> mList;
    Context context;
    public MyadaperBudget(Context context , ArrayList<ModelBudget> mList)
    {
        this.mList=mList;
        this.context=context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.budgetitem, parent, false);
       return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       ModelBudget model=mList.get(position);
       holder.uname.setText(model.getUname());
        holder.pnum.setText(model.getPnum());
        holder.ename.setText(model.getEname());
        holder.edate.setText(model.getEdate());
        holder.vname.setText(model.getVname());
        holder.vcost.setText(model.getVcost());
        holder.fname.setText(model.getFname());
        holder.fcost.setText(model.getFcost());
        holder.gquan.setText(model.getGquan());
        holder.dname.setText(model.getDname());
        holder.dcost.setText(model.getDcost());
        holder.pname.setText(model.getPname());
        holder.pcost.setText(model.getPcost());
        holder.tcost.setText(model.getTcost());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
TextView uname, pnum, ename, edate, vname, vcost, fname, fcost, gquan, dname, dcost, pname, pcost, tcost;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            uname=itemView.findViewById(R.id.name_text);
            pnum=itemView.findViewById(R.id.pne);
            ename=itemView.findViewById(R.id.ename2);
            edate=itemView.findViewById(R.id.date2);
            vname=itemView.findViewById(R.id.vname2);
            vcost=itemView.findViewById(R.id.vcost2);
            fname=itemView.findViewById(R.id.fname2);
            fcost=itemView.findViewById(R.id.pcost2);
            gquan=itemView.findViewById(R.id.gcount2);
            dname=itemView.findViewById(R.id.dname2);
            dcost=itemView.findViewById(R.id.dcost2);
            pname=itemView.findViewById(R.id.photoname2);
            pcost=itemView.findViewById(R.id.pcost3);
            tcost=itemView.findViewById(R.id.total2);
            
        }
    }
}
