package com.example.marcdeguzman_comp304sec002_lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Exercise1 extends AppCompatActivity {

    private ImageView reusableImageView;

    // variables for coordinates of line movement
    private int startx = 10;
    private int starty = 10;
    private int endx = 10;
    private int endy = 10;

    // properties for x and y coordinate
    private TextView xCoordText;
    private TextView yCoordText;

    // canvass variables
    private Paint paint;
    private Bitmap bitmap;
    private Canvas canvas;

    // line properties
    private String lineColor = "";
    private int strokeWidth = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise1);

        // create paint for the drawing
        paint = new Paint();
        paint.setStrokeWidth(strokeWidth);

        // creating a bitmap as content view for the image
        bitmap = Bitmap.createBitmap((int) getWindowManager()
                .getDefaultDisplay().getWidth(), (int) getWindowManager()
                .getDefaultDisplay().getHeight(), Bitmap.Config.ARGB_8888);

        // tell canvas about the content view
        canvas = new Canvas(bitmap);
        // set the background for your drawings
        canvas.drawColor(Color.BLACK);

        //setup the image view
        reusableImageView = (ImageView)findViewById(R.id.imageViewForDrawing);

        // tell image view for the bitmap format/content
        reusableImageView.setImageBitmap(bitmap);
        reusableImageView.setVisibility(View.VISIBLE);

        xCoordText = (TextView) findViewById(R.id.xCoordEditText);
        yCoordText = (TextView) findViewById(R.id.yCoordEditText);

        // setup line color selection
        setupLineColor();
    }

    public void clearCanvas(View v)
    {
        // reset the background to remove the previous drawing
        canvas.drawColor(Color.BLACK);

        // reset the coordinates; default to 10 since on 0 the line is not viewable on the sides
        startx = 10;
        starty = 10;
        endx = 10;
        endy = 10;

        // reset the edit text
        xCoordText.setText("");
        yCoordText.setText("");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        // we only need to override the arrow keys. other keys will not work without this e.g. back button
        if (!isArrowKeys(keyCode)) {
            return super.onKeyDown(keyCode, event);
        }
        // check if line thickness changed
        checkLineThickness();

        // check if the user already chose a color
        if (lineColor.equals("")) {
            Toast.makeText(getApplicationContext(), "Please select line color", Toast.LENGTH_SHORT).show();
            return false;
        }

        switch(keyCode) {
            case KeyEvent.KEYCODE_DPAD_DOWN:
                processLineMovement(LineMovement.DOWN);
                return true;
            case KeyEvent.KEYCODE_DPAD_UP:
                processLineMovement(LineMovement.UP);
                return true;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                processLineMovement(LineMovement.RIGHT);
                return true;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                processLineMovement(LineMovement.LEFT);
                return true;
        }

        return false;
    }

    /* handlers for the up down left right images to move the line */
    public void moveDown(View view) {
        processLineMovement(LineMovement.DOWN);
    }

    public void moveUp(View view) {
        processLineMovement(LineMovement.UP);
    }

    public void moveRight(View view) {
        processLineMovement(LineMovement.RIGHT);
    }

    public void moveLeft(View view) {
        processLineMovement(LineMovement.LEFT);
    }
    /* end handlers for movement image */

    private boolean isArrowKeys(int keyCode) {
        boolean retVal = false;

        if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN ||
                keyCode == KeyEvent.KEYCODE_DPAD_UP ||
                keyCode == KeyEvent.KEYCODE_DPAD_RIGHT ||
                keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
            retVal = true;
        }

        return retVal;
    }

    private void checkLineThickness() {
        Spinner lineThicknessSpinner = (Spinner) findViewById(R.id.lineThicknessSpinner);
        int strokeWidthValue = Integer.parseInt(lineThicknessSpinner.getSelectedItem().toString());
        if (strokeWidth != strokeWidthValue) {
            paint.setStrokeWidth(strokeWidthValue);
        }
    }

    private void processLineMovement(LineMovement lineMovement) {
        String xCoordString = xCoordText.getText().toString();
        String yCoordString = yCoordText.getText().toString();

        // handle the x value from edit view
        if (!xCoordString.isEmpty()) {
            int xFromEditView = Integer.parseInt(xCoordText.getText().toString());

            if (xFromEditView <= 10) {
                startx = 10;
                endx = 10;
            }

            if (xFromEditView != startx) {
                startx = xFromEditView;
                endx = xFromEditView;
            }
        }

        // handle the y value from edit view
        if (!yCoordString.isEmpty()) {
            int yFromEditView = Integer.parseInt(yCoordText.getText().toString());

            if (yFromEditView <= 10) {
                starty = 10;
                endy = 10;
            }

            if (yFromEditView != starty) {
                starty = yFromEditView;
                endy = yFromEditView;
            }
        }

        switch(lineMovement) {
            case DOWN:
                endy = endy + 5;
                drawLine(canvas);
                break;
            case UP:
                int endyMinus = endy - 5;
                if (endy <= 10) {
                    endy = 10;
                } else {
                    endy = endyMinus;
                }
                drawLine(canvas);
                break;
            case RIGHT:
                endx = endx + 5;

                drawLine(canvas);
                break;
            case LEFT:
                int endxMinus = endx - 5;
                if (endxMinus <= 10) {
                    endx = 10;
                } else {
                    endx = endx - 5;
                }

                drawLine(canvas);
                break;
        }
    }

    private void drawLine(Canvas canvas)
    {
        xCoordText.setText(String.valueOf(endx));
        yCoordText.setText(String.valueOf(endy));

        canvas.drawLine(startx, starty, endx, endy, paint);
        startx = endx;
        starty = endy;

        reusableImageView.setFocusable(true);
        reusableImageView.requestFocus();
        reusableImageView.invalidate();
    }

    private void setupLineColor() {
        // create the radio group
        RadioGroup lineColorRadioGrp = findViewById(R.id.lineColorRbGrp);
        lineColorRadioGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rbRed = findViewById(R.id.redRadioBtn);
                RadioButton rbGreen = findViewById(R.id.greenRadioBtn);

                if (rbRed.isChecked()) {
                    lineColor = "RED";
                    paint.setColor(Color.RED);
                } else if (rbGreen.isChecked()){
                    lineColor = "GREEN";
                    paint.setColor(Color.GREEN);
                } else {
                    lineColor = "YELLOW";
                    paint.setColor(Color.YELLOW);
                }
            }
        });
    }
}