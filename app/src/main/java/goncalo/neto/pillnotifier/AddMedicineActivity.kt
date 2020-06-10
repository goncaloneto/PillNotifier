package goncalo.neto.pillnotifier

import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.emredavarci.noty.Noty
import java.text.SimpleDateFormat
import java.util.*


class AddMedicineActivity : AppCompatActivity() {

    lateinit var myDB: DatabaseHelper
    var date: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_medicine)

        myDB = DatabaseHelper(this)

        val calendarView = findViewById<CalendarView>(R.id.calendarView)
        date = calendarView.date
        calendarView.setOnDateChangeListener { view, year, month, day ->
            val c = Calendar.getInstance()
            c[year, month] = day
            date = c.timeInMillis
        }

        findViewById<Button>(R.id.saveBtn).setOnClickListener { _ ->

            val nameEditText = findViewById<EditText>(R.id.nameEditText)
            val qtyEditText = findViewById<EditText>(R.id.qtyTextNumber)

            if(nameEditText.text.isEmpty()) {
                ShowNoty("Name can't be empty!")
            }
            else {
                if (qtyEditText.text.isEmpty()) {
                    ShowNoty("Quantity can't be empty!")
                } else {
                    val n = nameEditText.text.toString()
                    val q = qtyEditText.text.toString().toInt()

                    myDB.AddPillBox(n, q, q, date)

                    finish()
                }
            }
        }
    }

    private fun ShowNoty(msg: String)
    {
        var layout = findViewById<ConstraintLayout>(R.id.addMedicineLayout)
        val background = Integer.toHexString(getResources().getColor(R.color.colorAccent))
        val foreground = Integer.toHexString(getResources().getColor(R.color.colorPrimary))

        Noty.init(this, msg, layout, Noty.WarningStyle.SIMPLE)
            .setAnimation(Noty.RevealAnim.SLIDE_DOWN, Noty.DismissAnim.FADE_OUT, 400,400)
            .setWarningInset(0F,0F,0F,0F)
            .setWarningBoxRadius(0F,0F,0F,0F)
            .setWarningBoxBgColor("#$background")
            .setWarningTextColor("#$foreground")
            .show();
    }
}