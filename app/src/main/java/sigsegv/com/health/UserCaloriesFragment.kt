package sigsegv.com.health


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.github.mikephil.charting.data.*
import kotlinx.android.synthetic.main.fragment_user_calories.*

private const val ARG_DATES = "dates"

class UserCaloriesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var dates: ArrayList<String>
    private lateinit var dateValueMap : ArrayList<Pair<String, List<Int>>>
    private var showIndex = 0
    private var showDaily = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dateValueMap = ArrayList()
        arguments?.let {
            dates = it.getStringArrayList(ARG_DATES)!!
            for (date in dates){
                val values : ArrayList<Int> = it.getIntegerArrayList(date)!!
                dateValueMap.add(Pair(date, values))
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_calories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if(dateValueMap.size != 0){
           drawChart()
        }
        if(dates.size != 0){
            val arr : Array<String> = Array<String>(dates.size, {r -> dates.get(r) })
            val adapter : ArrayAdapter<String> = ArrayAdapter<String>(context!!, android.R.layout.simple_spinner_item, arr)
            spinner.adapter = adapter
            spinner.setOnItemClickListener { parent, view, position, id -> run{
                showIndex = position
                if(showDaily)
                    drawChart()
            } }
        }
        
        super.onViewCreated(view, savedInstanceState)
    }

    private fun drawChart(){
        if(showDaily){
            val values : List<Int> = dateValueMap.get(showIndex).second
            val entries = ArrayList<BarEntry>()
            for ((ct, i) in values.withIndex())
                entries.add(BarEntry(i.toFloat(), ct.toFloat()))

            val dataset  = BarDataSet(entries, "Burnt Calories")
            dataset.color = ContextCompat.getColor(this.context!!, R.color.colorPrimary)
            val barData  = BarData(dataset)
            barData.barWidth = 10f

            calories_bar_chart.data = barData
            //calories_bar_chart.setMaxVisibleValueCount(12)
            calories_bar_chart.invalidate()
            calories_bar_chart.setDrawBarShadow(false)
            calories_bar_chart.setDrawValueAboveBar(true)
            calories_bar_chart.description.isEnabled = false
        }




        calories_bar_chart.invalidate()
    }


    companion object {
        @JvmStatic
        fun newInstance(dates: ArrayList<String>, values : ArrayList<Pair<String, ArrayList<Int>>>) =
            UserCaloriesFragment().apply {
                arguments = Bundle().apply {
                    putStringArrayList(ARG_DATES, dates)
                    for(p in values){
                        putIntegerArrayList(p.first, p.second)
                    }
                }
            }
    }
}

private fun Spinner.setOnItemClickListener() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}
