package app.gram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private int[] data = new int[] { 0,50, 60, 0, 75, 85, 0, 89,85,0,56,89 };
    private int[] text = new int[] { 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0 };
    private HistogramView histogramView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        histogramView = (HistogramView) this.findViewById(R.id.histogram);

        histogramView.setProgress(data);
        histogramView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int step = (v.getWidth() - 30) / 8;
                int x = (int) event.getX();
                for (int i = 0; i < 7; i++) {
                    if (x > (30 + step * (i + 1) - 30)
                            && x < (30 + step * (i + 1) + 30)) {
                        text[i] = 1;
                        for (int j = 0; j < 7; j++) {
                            if (i != j) {
                                text[j] = 0;
                            }
                        }
                        histogramView.setText(text);
                    }
                }

                return false;
            }
        });

    }
}
