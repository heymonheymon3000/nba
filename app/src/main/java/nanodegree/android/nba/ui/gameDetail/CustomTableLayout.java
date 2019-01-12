package nanodegree.android.nba.ui.gameDetail;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.content.Context;


public class CustomTableLayout extends TableLayout {
    private Paint linePaint = null;
    private Rect tableLayoutRect;

    public CustomTableLayout(Context context) {
        super(context);
    }

    public CustomTableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        float strokeWidth = this.getContext().getResources().getDisplayMetrics().scaledDensity * 1;
        linePaint = new Paint(0);
        linePaint.setColor(0xff555555);
        linePaint.setStrokeWidth(strokeWidth);
        linePaint.setStyle(Paint.Style.STROKE);

        Rect rect = new Rect();
        int paddingTop= getPaddingTop();

        this.getDrawingRect(rect);
        tableLayoutRect = new Rect(rect.left, rect.top + paddingTop, rect.right, rect.bottom);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        Rect rect = new Rect();

        if (linePaint != null) {
            canvas.drawRect(tableLayoutRect, linePaint);
            float y = tableLayoutRect.top;
            for (int i = 0; i < getChildCount() - 1; i++) {
                if (getChildAt(i) instanceof TableRow) {
                    TableRow tableRow = (TableRow) getChildAt(i);
                    tableRow.getDrawingRect(rect);
                    y += rect.height();
                    canvas.drawLine(tableLayoutRect.left, y, tableLayoutRect.right, y, linePaint);
                    float x = tableLayoutRect.left;
                    for (int j = 0; j < tableRow.getChildCount() - 1; j++) {
                        View view = tableRow.getChildAt(j);
                        if (view != null) {
                            view.getDrawingRect(rect);
                            x += rect.width();
                            canvas.drawLine(x, tableLayoutRect.top, x, tableLayoutRect.bottom, linePaint);
                        }
                    }
                }
            }
        }
    }
}
