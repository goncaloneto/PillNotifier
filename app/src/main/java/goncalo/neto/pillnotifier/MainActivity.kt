package goncalo.neto.pillnotifier

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var myDB: DatabaseHelper
    var pillList = mutableListOf<PillBox>()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK)
        {
            UpdatePillList()
            UpdateListView()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        InitDB()
        UpdatePillList()
        UpdateListView()

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { _ ->
            val addPage = Intent(this, AddMedicineActivity::class.java)
            this.startActivityForResult(addPage,1);
        }
    }

    private fun InitDB(){
        myDB = DatabaseHelper(this)
    }

    private fun UpdatePillList()
    {
        pillList.clear()
        val data = myDB.ListBoxes
        while (data.moveToNext()) {
            val box = PillBox(data.getString(DatabaseHelper.NAME_COL_INDEX), data.getInt(DatabaseHelper.QTY_COL_INDEX), data.getFloat(DatabaseHelper.REMAINING_COL_INDEX), data.getFloat(DatabaseHelper.DOSAGE_COL_INDEX), Date(data.getLong(DatabaseHelper.EXP_DATE_COL_INDEX)), Date(data.getLong(DatabaseHelper.START_DATE_COL_INDEX)))
            pillList.add(box)
        }
    }

    public fun UpdateListView()
    {
        val listView = findViewById<ListView>(R.id.main_listview)
        listView.adapter = MedicineListAdapter(this, pillList)
    }

    private class MedicineListAdapter(context: Context, pills: MutableList<PillBox>) : BaseAdapter()
    {
        private val context = context
        private val pillList = pills

        // render out each row
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(context)
            val rowMain = layoutInflater.inflate(R.layout.medicine_row_main, parent, false)

            val name = rowMain.findViewById<TextView>(R.id.rowNameValue)
            name.text = pillList[position].Name

            val remaining = rowMain.findViewById<TextView>(R.id.rowRemainingValue)
            remaining.text = pillList[position].RemainingPills.toString()

            val expDate = rowMain.findViewById<TextView>(R.id.expDateValue)
            expDate.text = pillList[position].ExpirationDate

            return rowMain
        }

        override fun getItem(position: Int): Any {
            return 0
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return pillList.size
        }

    }
}