package com.View

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Pie
import com.example.wheeler.R


open class anychart : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anychart)

        chart = findViewById(R.id.pie)
        var button = findViewById<Button>(R.id.button)

        configChartView() }


        var chart: AnyChartView? = null


        fun configChartView() {
            val salary = listOf(200, 200, 200, 200)
            val month = listOf("January", "February", "March", "April")
            var pie: Pie = AnyChart.pie()
            var legend = pie.legend()
            legend.enabled(false)
            val dataPieChart: MutableList<DataEntry> = mutableListOf()
            for (index in salary.indices) {
                dataPieChart.add(ValueDataEntry(month.elementAt(index), salary.elementAt(index)))
            }

            pie.data(dataPieChart)
            chart!!.setChart(pie)


        }}
