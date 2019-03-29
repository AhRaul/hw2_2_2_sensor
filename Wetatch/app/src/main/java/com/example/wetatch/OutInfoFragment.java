package com.example.wetatch;


import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import java.util.List;

import static android.content.Context.SENSOR_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class OutInfoFragment extends Fragment {
    private static final String TAG = "!!!!!!OutInfoFragment: ";

    private View viewInflater;              //ссылка на рабочий макет фрагмента 2
    private TextView textViewCityName;      //ссылка на поле вывода названия города в макете start_app
    private TextView textViewTemperatureValue;  //ссылка на поле вывода температуры в макете out_info
    private TextView textViewOutAtmos;      //ссылка на поле выводадавления в макете start_app
    private TextView textViewOutWind;  //ссылка на поле вывода ветра в макете out_info

    private TextView textTempSensor;
    private TextView textDrySensor;
    private SensorManager sensorManager;
    private List<Sensor> sensors;
    private Sensor sensorTemp;
    private Sensor sensorDry;

    //Фабричный метод create создает фрагмент
    public static OutInfoFragment create() {
        OutInfoFragment f = new OutInfoFragment();      //создание
        return f;
    }

    public OutInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.viewInflater = inflater.inflate(R.layout.fragment_out_info, container, false);

        textViewCityName = viewInflater.findViewById(R.id.textViewCityName);    //получить ссылку на название города
        textViewTemperatureValue = viewInflater.findViewById(R.id.textViewTemperatureValue);
        textViewOutAtmos = viewInflater.findViewById(R.id.textViewOutAtmos);
        textViewOutWind = viewInflater.findViewById(R.id.textViewOutWind);

        textTempSensor = viewInflater.findViewById(R.id.textView);
        textDrySensor = viewInflater.findViewById(R.id.textView2);

        // Менеджер датчиков
        sensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);
        // Получить все датчики, какие есть
        sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        if(ActualChoiceState.isSwitchTempSensor()) {
            // Датчик температуры
            sensorTemp = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        }

        if(ActualChoiceState.isSwitchDrySensor()) {
            // Датчик владности
            sensorDry = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        }

//        // Регистрируем слушатель датчика температуры
//        sensorManager.registerListener(listenerTemp, sensorTemp,
//                SensorManager.SENSOR_DELAY_NORMAL);
//
//        // Регистрируем слушатель датчика влажности
//        sensorManager.registerListener(listenerDry, sensorDry,
//                SensorManager.SENSOR_DELAY_NORMAL);

        Log.i(TAG, "onCreateView()");

        return this.viewInflater;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        refresh();
    }

    //обновляет поля ввода
    public void refresh() {
        textViewCityName.setText(ActualChoiceState.getCurrentCityName());

        //тестовые результаты
        if(textViewCityName.getText().toString().equals("Kazan")) {
            textViewTemperatureValue.setText("-5");         //сюда можно поместить настоящий источник данных о температуре
        } else if(textViewCityName.getText().toString().equals("Moscow")) {
            textViewTemperatureValue.setText("-2");         //сюда можно поместить настоящий источник данных о температуре
        } else if(textViewCityName.getText().toString().equals("Peter")) {
            textViewTemperatureValue.setText("-9");         //сюда можно поместить настоящий источник данных о температуре
        }

        if(ActualChoiceState.isSwitchPressure()) {
            //тестовые результаты
            if(textViewCityName.getText().toString().equals("Kazan")) {
                textViewOutAtmos.setText("760 мм рт. ст");         //сюда можно поместить настоящий источник данных о давлении
            } else if(textViewCityName.getText().toString().equals("Moscow")) {
                textViewOutAtmos.setText("740 мм рт. ст");         //сюда можно поместить настоящий источник данных о давлении
            } else if(textViewCityName.getText().toString().equals("Peter")) {
                textViewOutAtmos.setText("780 мм рт. ст");         //сюда можно поместить настоящий источник данных о давлении
            }
        }

        if(ActualChoiceState.isSwitchWindspeed()) {
            //тестовые результаты
            if(textViewCityName.getText().toString().equals("Kazan")) {
                textViewOutWind.setText("5 м/с");         //сюда можно поместить настоящий источник данных о давлении
            } else if(textViewCityName.getText().toString().equals("Moscow")) {
                textViewOutWind.setText("3 м/с");         //сюда можно поместить настоящий источник данных о давлении
            } else if(textViewCityName.getText().toString().equals("Peter")) {
                textViewOutWind.setText("10 м/с");         //сюда можно поместить настоящий источник данных о давлении
            }
        }

        if(ActualChoiceState.isSwitchTempSensor()) {
            textTempSensor.setText((int) sensorTemp.getResolution());
        }

        if(ActualChoiceState.isSwitchDrySensor()) {
            textDrySensor.setText((int) sensorDry.getResolution());
        }
    }

//    // Вывод датчика температуры
//    private void showTempSensors(SensorEvent event){
////        StringBuilder stringBuilder = new StringBuilder();
////        stringBuilder.append("Temp Sensor value = ").append(event.values[0]);
//        textTempSensor.setText((CharSequence) event);
//    }
//
//    // Вывод датчика влажности
//    private void showDrySensors(SensorEvent event){
////        StringBuilder stringBuilder = new StringBuilder();
////        stringBuilder.append("Dry Sensor value = ").append(event.values[0]);
//        textDrySensor.setText((CharSequence) event);
//    }

//    @Override
//    public void onPause() {
//        super.onPause();
//        sensorManager.unregisterListener(listenerTemp, sensorTemp);
//        sensorManager.unregisterListener(listenerDry, sensorDry);
//    }
//
//        // Слушатель датчика температуры
//    SensorEventListener listenerTemp = new SensorEventListener() {
//        @Override
//        public void onAccuracyChanged(Sensor sensor, int accuracy) {
//        } @
//                Override
//        public void onSensorChanged(SensorEvent event) {
//            showTempSensors(event);
//        }
//    };
//
//    // Слушатель датчика влажности
//    SensorEventListener listenerDry = new SensorEventListener() {
//        @Override
//        public void onAccuracyChanged(Sensor sensor, int accuracy) {
//        } @
//                Override
//        public void onSensorChanged(SensorEvent event) {
//            showDrySensors(event);
//        }
//    };
}
