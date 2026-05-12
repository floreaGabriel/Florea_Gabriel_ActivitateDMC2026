package com.florea_gabriel.lab11;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class ChartView extends View {
    private float[] values = new float[]{};
    public static final int BAR_CHART    = 0;
    public static final int PIE_CHART    = 1;
    public static final int COLUMN_CHART = 2;
    private int chartType = COLUMN_CHART;

    private final Paint barPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint axisPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public ChartView(Context context, AttributeSet attrs) {
        super(context, attrs);

        barPaint.setColor(Color.parseColor("#5C6BC0"));
        barPaint.setStyle(Paint.Style.FILL);

        axisPaint.setColor(Color.GRAY);
        axisPaint.setStrokeWidth(3f);
        axisPaint.setStyle(Paint.Style.STROKE);

        textPaint.setColor(Color.DKGRAY);
        textPaint.setTextSize(36f);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    public void setValues(float[] values) {
        this.values = values;
        invalidate(); // redeseneaza
    }

    public void setChartType(int type) {
        this.chartType = type;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (values == null || values.length == 0) return;

        switch (chartType) {
            case BAR_CHART:
                drawBarChart(canvas);
                break;
            case PIE_CHART:
                drawPieChart(canvas);
                break;
            case COLUMN_CHART:
                drawColumnChart(canvas);
                break;
        }
    }

    private void drawPieChart(Canvas canvas) {
        int width  = getWidth();
        int height = getHeight();
        float cx = width / 2f;
        float cy = height / 2f;
        float radius = Math.min(width, height) / 2f - 40f;

        float total = 0;
        for (float v : values) total += v;

        int[] colors = {
                Color.parseColor("#5C6BC0"),
                Color.parseColor("#EF5350"),
                Color.parseColor("#66BB6A"),
                Color.parseColor("#FFA726"),
                Color.parseColor("#26C6DA")
        };

        float startAngle = -90f;
        for (int i = 0; i < values.length; i++) {
            float sweep = (values[i] / total) * 360f;
            barPaint.setColor(colors[i % colors.length]);
            canvas.drawArc(cx - radius, cy - radius,
                    cx + radius, cy + radius,
                    startAngle, sweep, true, barPaint);

            float midAngle = startAngle + sweep / 2f;
            float textRadius = radius * 0.5f;

            float textX = cx + textRadius * (float) Math.cos(Math.toRadians(midAngle));
            float textY = cy + textRadius * (float) Math.sin(Math.toRadians(midAngle));

            canvas.drawText(String.valueOf(values[i]), textX, textY + 20f, textPaint);
            startAngle += sweep;
        }
    }
    private void drawBarChart(Canvas canvas) {
        // Bar chart orizontal
        if (values == null || values.length == 0) return;

        int width  = getWidth();
        int height = getHeight();

        float marginLeft   = 90f;
        float marginRight  = 60f;
        float marginTop    = 40f;
        float marginBottom = 40f;

        float chartWidth  = width  - marginLeft - marginRight;
        float chartHeight = height - marginTop  - marginBottom;

        float maxValue = values[0];
        for (float v : values) if (v > maxValue) maxValue = v;

        int count      = values.length;
        float totalGap = chartHeight * 0.3f;
        float barH     = (chartHeight - totalGap) / count;
        float gap      = totalGap / (count + 1);

        // axa X
        canvas.drawLine(marginLeft, marginTop + chartHeight,
                marginLeft + chartWidth, marginTop + chartHeight, axisPaint);
        // axa Y
        canvas.drawLine(marginLeft, marginTop,
                marginLeft, marginTop + chartHeight, axisPaint);

        for (int i = 0; i < count; i++) {
            float top    = marginTop + gap + i * (barH + gap);
            float bottom = top + barH;
            float right  = marginLeft + chartWidth * (values[i] / maxValue);

            barPaint.setColor(Color.parseColor("#5C6BC0"));
            canvas.drawRect(marginLeft, top, right, bottom, barPaint);

            // valoarea
            Paint vp = new Paint(Paint.ANTI_ALIAS_FLAG);
            vp.setColor(Color.WHITE);
            vp.setTextSize(28f);
            canvas.drawText(String.valueOf((int) values[i]),
                    right - 50f, top + barH / 2f + 10f, vp);

            // eticheta
            textPaint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText("Val " + (i + 1),
                    marginLeft - 8f, top + barH / 2f + 12f, textPaint);
        }
    }
    private void drawColumnChart(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();

        float marginLeft = 60f;
        float marginRight = 40f;
        float marginTop = 60f;
        float marginBottom = 80f;

        float chartWidth = width - marginLeft - marginRight;
        float chartHeight = height - marginTop - marginBottom;

        // valoarea maxima
        float maxValue = values[0];
        for (float v : values) {
            if (v > maxValue) maxValue = v;
        }

        int count = values.length;
        float totalGap = chartWidth * 0.3f;
        float barWidth = (chartWidth - totalGap) / count;
        float gap = totalGap / (count + 1);

        // axa Y
        canvas.drawLine(marginLeft, marginTop,
                marginLeft, marginTop + chartHeight, axisPaint);
        // axa X
        canvas.drawLine(marginLeft, marginTop + chartHeight,
                marginLeft + chartWidth, marginTop + chartHeight, axisPaint);

        // bare
        for (int i = 0; i < count; i++) {
            float left = marginLeft + gap + i * (barWidth + gap);
            float right = left + barWidth;
            float top = marginTop + chartHeight * (1f - values[i] / maxValue);
            float bottom = marginTop + chartHeight;

            canvas.drawRect(left, top, right, bottom, barPaint);

            // valoarea deasupra barei
            Paint valuePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            valuePaint.setColor(Color.WHITE);
            valuePaint.setTextSize(30f);
            valuePaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(String.valueOf((int) values[i]),
                    left + barWidth / 2f, top + 40f, valuePaint);

            // eticheta sub axa X
            canvas.drawText("Val " + (i + 1),
                    left + barWidth / 2f,
                    marginTop + chartHeight + textPaint.getTextSize() + 16f,
                    textPaint);
        }
    }
}
