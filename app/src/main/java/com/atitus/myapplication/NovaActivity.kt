package com.atitus.myapplication

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class NovaActivity : AppCompatActivity() {

    private lateinit var sensorManager: SensorManager
    private lateinit var sensorTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nova)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorTextView = findViewById(R.id.sensorTextView)

        //Neste exemplo, a lista de sensores disponíveis é obtida através do método
        //getSensorList(Sensor.TYPE_ALL) do SensorManager.
        // Em seguida, percorremos a lista de sensores e concatenamos seus nomes em um StringBuilder.
        // Por fim, exibimos os nomes dos sensores no TextView com o ID sensorTextView.

        val deviceSensors: List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)
        val sensorNames = StringBuilder()

        for (sensor in deviceSensors) {
            val sensorName = sensor.name
            sensorNames.append("$sensorName\n")
        }

        sensorTextView.text = sensorNames.toString()
    }
}
