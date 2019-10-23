package com.nu9ve.quests.util

import android.graphics.*
import com.squareup.picasso.Transformation

/**
 *Picasso round images
 **/

class CropCircleTransformation : Transformation {
    override fun transform(source: Bitmap): Bitmap {
        val size: Int = Math.min(source.width, source.height)
        val width = (source.width - size) / 2
        val height = (source.height - size) / 2

        val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint()
        val shader = BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

        if (width != 0 || height != 0) {
            val matrix = Matrix()
            matrix.setTranslate(-width.toFloat(), -height.toFloat())
            shader.setLocalMatrix(matrix)
        }

        paint.shader = shader
        paint.isAntiAlias = true

        val radius = size / 2f
        canvas.drawCircle(radius, radius, radius, paint)
        source.recycle()

        return bitmap
    }

    override fun key(): String = "CropCircleTransformation"
}