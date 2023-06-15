package com.atitus.myapplication

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.view.View


class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var lightSensor: Sensor? = null
    private lateinit var lightTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar o SensorManager e o Sensor de luz
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

        // Obter referência ao TextView para exibir os valores de luminosidade
        lightTextView = findViewById(R.id.lightTextView)
    }


    override fun onResume() {
        super.onResume()
        // Registrar o listener do sensor de luz
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        // Cancelar o registro do listener do sensor de luz para economizar recursos
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_LIGHT) {
            val luminosity = event.values[0]
            lightTextView.text = "Luminosidade: $luminosity lux"
        }
    }

    fun redirecionarParaNovaPagina(view: View) {
        val intent = Intent(this, NovaActivity::class.java)
        startActivity(intent)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Implementação opcional para lidar com mudanças na precisão do sensor de luz
    }
}


