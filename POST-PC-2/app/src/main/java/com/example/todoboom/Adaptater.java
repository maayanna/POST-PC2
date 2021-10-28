package com.example.todoboom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptater extends RecyclerView.Adapter<Holder> {

    private ArrayList<Todo> myList;

    Adaptater(ArrayList<Todo> myList){
        this.myList = myList;
    }

    public ArrayList getMyList() {
        return myList;
    }

    public void addItem(Todo item){
        myList.add(item);
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final Context myContext = parent.getContext();
        View myView = LayoutInflater.from(myContext).inflate(R.layout.item_one_todo, parent, false);
        final Holder myHolder = new Holder(myView);

        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( ! (myList.get(myHolder.getAdapterPosition())).getDone()){
                    Toast.makeText(myContext,"TODO "+ myList.get(myHolder.getAdapterPosition()).getDescription() + " is now DONE. BOOM!",Toast.LENGTH_SHORT).show();
                    ImageView myImg = myHolder.getMyImage();
                    myImg.setImageResource(R.drawable.ic_check_box_black_24dp);
                    myList.get(myHolder.getAdapterPosition()).setDone(true);
                }
            }
        });

        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Todo myTodo = myList.get(position);
        holder.getMyText().setText(myTodo.getDescription());

        ImageView myImg = holder.getMyImage();
        if(myTodo.getDone()){
            myImg.setImageResource(R.drawable.ic_check_box_black_24dp);
        }
        else{
            myImg.setImageResource(R.drawable.ic_check_box_outline_blank_black_24dp);
        }
    }

    @Override
    public int getItemCount() {
        return getMyList().size();
    }
}
