package com.example.animation;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Animations {

    @SuppressLint("ClickableViewAccessibility")
    public static void itemAnimate(ImageView imageView, TextView textView, int degree, int duration){
                    imageView.setOnTouchListener(new View.OnTouchListener() {
                        @SuppressLint("ClickableViewAccessibility")
                        @Override
                        public boolean onTouch( View view, MotionEvent motionEvent) {
                            float x = motionEvent.getX();
                            float y = motionEvent.getY();

                            switch (motionEvent.getAction()) {
                                case MotionEvent.ACTION_DOWN: // нажатие
                                    if (x >= imageView.getWidth() / 3 * 2) {  // нажатие на правую часть view
                                        imageView.animate().withLayer()
                                                .rotationY(degree)
                                                .setDuration(duration).start();
                                        textView.animate().withLayer()
                                                .rotationY(degree)
                                                .setDuration(duration).start();
                                    }
                                    if (x <= imageView.getWidth() / 3) {  // нажатие на левую часть view
                                        imageView.animate().withLayer()
                                                .rotationY(-degree)
                                                .setDuration(duration).start();
                                        textView.animate().withLayer()
                                                .rotationY(-degree)
                                                .setDuration(duration).start();
                                    }
                                    if (y <= imageView.getHeight() / 3) {  // нажатие на верхнюю часть view
                                        imageView.animate().withLayer()
                                                .rotationX(degree)
                                                .setDuration(duration).start();
                                        textView.animate().withLayer()
                                                .rotationX(degree)
                                                .setDuration(duration).start();
                                    }
                                    if (y >= imageView.getHeight() / 3 * 2) {  // нажатие на нижнюю часть view
                                        imageView.animate().withLayer()
                                                .rotationX(-degree)
                                                .setDuration(duration).start();
                                        textView.animate().withLayer()
                                                .rotationX(-degree)
                                                .setDuration(duration).start();
                                    }
                                    break;
                                case MotionEvent.ACTION_MOVE: // движение
                                    break;
                                case MotionEvent.ACTION_UP: // отпускание
                                case MotionEvent.ACTION_CANCEL:
                                    imageView.animate().withLayer()  // возвращаем к исходному положению , когда пользователь отпускает view
                                            .rotationY(0)
                                            .rotationX(0)
                                            .setDuration(1800).start();
                                    textView.animate().withLayer()
                                            .rotationY(0)
                                            .rotationX(0)
                                            .setDuration(1800).start();
                                    break;
                            }
                            return false;
                        }
                    });
    }

}
