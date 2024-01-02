package com.example.todoapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.Adapter.ToDoAdapter;

public class RecyclerViewTouchHelper extends ItemTouchHelper.SimpleCallback {

    private ToDoAdapter adapter;
    public RecyclerViewTouchHelper(ToDoAdapter adapter) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.adapter = adapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        final int position = viewHolder.getAdapterPosition();
        if (direction == ItemTouchHelper.RIGHT) {
            AlertDialog.Builder builder = new AlertDialog.Builder(adapter.getContext());
            builder.setTitle("Delete Task");
            builder.setMessage("Are you sure?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    adapter.deleteTask(position);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    adapter.notifyItemChanged(position);
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            adapter.editItem(position);
        }
    }

    // Override onChildDraw for customizing swipe appearance
//    @Override
//    public void onChildDraw(
//            @NonNull Canvas c,
//            @NonNull RecyclerView recyclerView,
//            @NonNull RecyclerView.ViewHolder viewHolder,
//            float dX,
//            float dY,
//            int actionState,
//            boolean isCurrentlyActive
//    ) {
//        // Call super method to draw default behavior
//        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//
//        View itemView = viewHolder.itemView;
//
//        // Set background color for left swipe
//        if (dX > 0) {
//            int swipeBackgroundColor = ContextCompat.getColor(adapter.getContext(), R.color.colorAccent);
//            ColorDrawable background = new ColorDrawable(swipeBackgroundColor);
//            background.setBounds(itemView.getLeft(), itemView.getTop(), (int) dX, itemView.getBottom());
//            background.draw(c);
//        }
//        // Set background color for right swipe
//        else if (dX < 0) {
//            int swipeBackgroundColor = ContextCompat.getColor(adapter.getContext(), R.color.colorPrimary);
//            ColorDrawable background = new ColorDrawable(swipeBackgroundColor);
//            background.setBounds(itemView.getRight() + (int) dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
//            background.draw(c);
//        }
//
//        // Draw icons or other decorations here if needed
//    }
    

}
