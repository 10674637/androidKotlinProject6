package com.example.tc_project6

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect
import android.text.TextUtils.indexOf
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import java.security.KeyStore

class DrawView(context: Context, attrs: AttributeSet? = null): View(context, attrs) {
    var nextX = 0F
    var nextY = 0F
    var bitmap: Bitmap
    val out: Bitmap
    val water: Bitmap
    val mountain: Bitmap
    val forest: Bitmap
    val plain: Bitmap
    val person: Bitmap

    val blockSize = 155;

    var xOffset = 0
    var yOffset = 0

    //12 X 12
    val arrayWorldMap = arrayOf(
        arrayOf("M","M","M","M","M",".",".",".","~","~","~","~"),
        arrayOf("M","M","M","M",".",".",".",".",".","~","~","~"),
        arrayOf("M","M","M","M","f",".",".",".",".","~","~","~"),
        arrayOf("M","M","M","f","f",".",".",".","~","~","~","~"),
        arrayOf("M","M","M","f","f",".",".",".","~","~","~","~"),
        arrayOf("M","M","M","M","f","f",".",".",".","~","~","~"),
        arrayOf("M","M","M","M","f",".",".",".","~","~","~","~"),
        arrayOf("M","M","M","f","f",".",".",".","~","~","~","~"),
        arrayOf("M","M","M","f",".",".",".","~","~","~","~","~"),
        arrayOf("M","M","f","f",".",".",".",".","~","~","~","~"),
        arrayOf("M","M","f","f",".",".",".",".",".","~","~","~"),
        arrayOf("M","f","f",".",".",".",".",".",".","~","~","~")
    )

    init{
        //images should be loaded asynchronously
        out = BitmapFactory.decodeResource(
            getResources(),
            R.drawable.out,
            null
        )
        water = BitmapFactory.decodeResource(
            getResources(),
            R.drawable.water,
            null
        )
        mountain = BitmapFactory.decodeResource(
            getResources(),
            R.drawable.mountain,
            null
        )
        plain = BitmapFactory.decodeResource(
            getResources(),
            R.drawable.plain,
            null
        )
        forest = BitmapFactory.decodeResource(
            getResources(),
            R.drawable.forest,
            null
        )
        person = BitmapFactory.decodeResource(
            getResources(),
            R.drawable.person,
            null
        )
        bitmap = out
    }

    override fun onDraw(canvas: Canvas){
        //protect edge fliy off
//        if (nextX > (blockSize * 11 - xOffset)) {
//            nextX = (blockSize * xOffset).toFloat()
//        }
//        if (nextY > (blockSize * 11 - yOffset)) {
//            nextY = (blockSize * 10).toFloat()
//        }

        //check nextX see where
//        if (nextX == 0F && nextY == 0F) {
//
//        } else {
            if (nextX > blockSize * 5) {
                xOffset = blockSize * 6
                nextX = 0F
            } else if (nextX < blockSize){
                //if already to the left
                if (xOffset == 0) {

                }
                // if on the right
                else {
                    xOffset = 0;
                    nextX = (blockSize * 6).toFloat()
                }
            }
            if (nextY > blockSize * 8) {
                yOffset = blockSize * 7
                nextY = (blockSize * 2).toFloat()
            } else if (nextY < blockSize) {
                //if top already
                if (yOffset == 0) {

                }
                // bring back to top
                else {
                    yOffset = 0
                    nextY = (blockSize * 3).toFloat()
                }
            }
//        }


        //make a two dimensional array of character that tell which image go to which square
//        for (var i = 0; i < arrayWorldMap.size; i++)
        arrayWorldMap.forEachIndexed { i, a ->
            a.forEachIndexed { j, b ->
                //print(j)
//                println("i=$i j=$j b=$b")
//                var rect1 = Rect(i, j, i+12, j+12)
                //SWITCH which bitmap to use.
                when(b) {
                    "~" -> bitmap = water
                    "M" -> bitmap = mountain
                    "f" -> bitmap = forest
                    "." -> bitmap = plain
                    else -> bitmap = out
                }
                var x = (i * blockSize).toFloat() - yOffset
                var y = (j * blockSize).toFloat() - xOffset

                canvas.drawBitmap(bitmap, y, x, null)

            }
        }
        //here is where I put the person dot
        canvas.drawBitmap(person, nextX, nextY, null)
        println("nextX=${nextX}, nextY=${nextY}")

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        if(event.action == MotionEvent.ACTION_UP){
            Log.i("CS3680", "Touch event" + event.getX() + "," + event.getY())
            nextX = event.getX() - (event.getX() % blockSize)
            nextY = event.getY() - (event.getY() % blockSize)
            invalidate()    //indirectly call onDraw make the image show where you click
            return true
        }
        return true
        //return super.onTouchEvent(event)
    }

    fun checkEdge(position: Int) {
        if (position > blockSize * 12)  {

        }
    }

}