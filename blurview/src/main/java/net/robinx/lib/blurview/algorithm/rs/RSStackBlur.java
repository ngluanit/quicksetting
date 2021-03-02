package net.robinx.lib.blurview.algorithm.rs;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;

import net.robinx.lib.blurview.algorithm.IBlur;
import net.robinx.lib.blurview.R;

import at.favre.app.blurbenchmark.ScriptC_stackblur;


/**
 * by kikoso
 * from https://github.com/kikoso/android-stackblur/blob/master/StackBlur/src/blur.rs
 */
public class RSStackBlur implements IBlur {
    private RenderScript _rs;
	private Context ctx;

    public RSStackBlur(RenderScript rs, Context ctx) {
		this.ctx = ctx;
		this._rs = rs;
    }

    @Override
    public Bitmap blur(int radius, Bitmap blurred) {
		radius = Math.min(radius,25);

		int width = blurred.getWidth();
		int height = blurred.getHeight();

		ScriptC_stackblur blurScript = new ScriptC_stackblur(_rs, ctx.getResources(), R.raw.stackblur);

		Allocation inAllocation = Allocation.createFromBitmap(_rs, blurred);

		blurScript.set_gIn(inAllocation);
		blurScript.set_width(width);
		blurScript.set_height(height);
		blurScript.set_radius(radius);

		int[] row_indices = new int[height];
		for (int i = 0; i < height; i++) {
			row_indices[i] = i;
		}

		Allocation rows = Allocation.createSized(_rs, Element.U32(_rs), height, Allocation.USAGE_SCRIPT);
		rows.copyFrom(row_indices);

		row_indices = new int[width];
		for (int i = 0; i < width; i++) {
			row_indices[i] = i;
		}

		Allocation columns = Allocation.createSized(_rs, Element.U32(_rs), width, Allocation.USAGE_SCRIPT);
		columns.copyFrom(row_indices);

		blurScript.forEach_blur_h(rows);
		blurScript.forEach_blur_v(columns);
		inAllocation.copyTo(blurred);

		return blurred;

    }
}
