package com.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Pie
import com.example.wheeler.R


class anychart : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anychart)

        chart = findViewById(R.id.pie)

        configChartView() }

        var chart: AnyChartView? = null

        val salary = listOf(200, 300, 400, 600)
        val month = listOf("January", "February", "March", "April")

        fun configChartView() {
            val pie: Pie = AnyChart.pie()

            val dataPieChart: MutableList<DataEntry> = mutableListOf()

            for (index in salary.indices) {
                dataPieChart.add(ValueDataEntry(month.elementAt(index), salary.elementAt(index)))
            }

            pie.data(dataPieChart)
            pie.title("Salaries Overview")
            chart!!.setChart(pie)
        }
        }


