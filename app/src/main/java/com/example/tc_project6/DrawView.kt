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
    var nextX = 50F
    var nextY = 50F
    var bitmap: Bitmap
    val out: Bitmap
    val water: Bitmap
    val mountain: Bitmap
    val forest: Bitmap
    val plain: Bitmap
    val person: Bitmap
    var personX: Int
    var personY: Int


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
        personX = 0
        personY = 0
        bitmap = out
    }

    override fun onDraw(canvas: Canvas){
        //check to see if bitmaps have been loaded
//        canvas.drawBitmap(bitmap, nextX, nextY, null)

        //make a two dimensional array of character that tell which image go to which square
        arrayWorldMap.forEachIndexed { i, a ->
            a.forEachIndexed { j, b ->
                //print(j)
                println("i=$i j=$j b=$b")
//                var rect1 = Rect(i, j, i+12, j+12)
                //SWITCH which bitmap to use.
                when(b) {
                    "~" -> bitmap = water
                    "M" -> bitmap = mountain
                    "f" -> bitmap = forest
                    "." -> bitmap = plain
                    else -> bitmap = out
                }
                var x = (i * 128).toFloat()
                var y = (j * 128).toFloat()

                canvas.drawBitmap(bitmap, y, x, null)

            }
        }
        //here is where I put the person dot
        canvas.drawBitmap(person, nextY, nextX, null)

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        if(event.action == MotionEvent.ACTION_UP){
            Log.i("CS3680", "Touch event" + event.getX() + "," + event.getY())
            nextX = event.getX()
            nextY = event.getY()
            invalidate()    //indirectly call onDraw make the image show where you click
            return true
        }
        return true
        //return super.onTouchEvent(event)
    }

}