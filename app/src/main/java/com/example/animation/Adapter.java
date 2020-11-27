package com.example.animation;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    ArrayList<String> strings = new ArrayList<>();
    OnItemCLickListener listener;

    public void setListener(OnItemCLickListener listener) {
        this.listener = listener;
    }

    public void setStrings(ArrayList<String> strings) {
        this.strings = strings;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_holder, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView cardView;

        @SuppressLint("ClickableViewAccessibility")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            cardView = itemView.findViewById(R.id.textView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(view);
                }
            });
            imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    float x = motionEvent.getX();
                    float y = motionEvent.getY();

                    switch (motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN: // нажатие
                            if (x >= imageView.getWidth() / 3 * 2) {  // нажатие на правую часть view
                                imageView.animate().withLayer()
                                        .rotationY(12)
                                        .setDuration(300).start();
                                cardView.animate().withLayer()
                                        .rotationY(12)
                                        .setDuration(300).start();
                            }
                            if (x <= imageView.getWidth() / 3) {  // нажатие на левую часть view
                                imageView.animate().withLayer()
                                        .rotationY(-12)
                                        .setDuration(300).start();
                                cardView.animate().withLayer()
                                        .rotationY(-12)
                                        .setDuration(300).start();
                            }
                            if (y <= imageView.getHeight() / 3) {  // нажатие на верхнюю часть view
                                imageView.animate().withLayer()
                                        .rotationX(12)
                                        .setDuration(300).start();
                                cardView.animate().withLayer()
                                        .rotationX(12)
                                        .setDuration(300).start();
                            }
                            if (y >= imageView.getHeight() / 3 * 2) {  // нажатие на нижнюю часть view
                                imageView.animate().withLayer()
                                        .rotationX(-12)
                                        .setDuration(300).start();
                                cardView.animate().withLayer()
                                        .rotationX(-12)
                                        .setDuration(300).start();
                            }
                            break;
                        case MotionEvent.ACTION_MOVE: // движение
                            break;
                        case MotionEvent.ACTION_UP: // отпускание
                        case MotionEvent.ACTION_CANCEL:
                            imageView.animate().withLayer()  // возвращаем к исходному положению , когда пользователь отпускает view
                                    .rotationY(0)
                                    .rotationX(0)
                                    .setDuration(1200).start();
                            cardView.animate().withLayer()
                                    .rotationY(0)
                                    .rotationX(0)
                                    .setDuration(1200).start();
                            break;
                    }
                    return false;
                }
            });
        }

    }
}
