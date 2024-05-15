package com.example.finlandweather;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.DecimalFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView textViewGreeting;
    TextView textViewTemperature;
    TextView textViewCityName;
    TextView tvResult;
   ImageView weatherImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewGreeting = findViewById(R.id.textViewGreeting);
        textViewGreeting.setText(getGreeting());
        textViewCityName = findViewById(R.id.textViewCityName);
        weatherImage = findViewById(R.id.weatherimage);
        textViewTemperature = findViewById(R.id.textViewTemperature);

        textViewCityName.setText("Lahti");

        fetchWeatherDataForLahti();



        ImageButton quizButton = findViewById(R.id.quizbutton);
        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });


        ImageButton settingsButton = findViewById(R.id.settingsbutton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        Button wikiButton = findViewById(R.id.buttontowikifinn);
        wikiButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String wiki = "https://en.wikipedia.org/wiki/History_of_Finland";
                Intent gotoWiki = new Intent(Intent.ACTION_VIEW);
                gotoWiki.setData(Uri.parse(wiki));
                startActivity(gotoWiki);
            }
        });

        tvResult = findViewById(R.id.tvResult);
    }

    @NonNull
    private String getGreeting() {
        Calendar calendar = Calendar.getInstance();
        int timeOfDay = calendar.get(Calendar.HOUR_OF_DAY);

        if (timeOfDay < 12) {
            return "Good Morning,";
        } else if (timeOfDay < 18) {
            return "Good Afternoon,";
        } else {
            return "Good Evening,";
        }
    }

    public void getWeatherDetails(View view) {
        Log.d("MainActivity", "getWeatherDetails method called");

        EditText etCity = findViewById(R.id.etCity);
        String city = etCity.getText().toString().trim();
        String formattedCity = adjustCityName(city);

        if (!formattedCity.isEmpty()) {
            String url = "https://api.openweathermap.org/data/2.5/weather?q=" + formattedCity + "&appid=a52075ba958987a1111ebb7b89eb8499";
            fetchWeatherData(url, city);
        } else {
            Toast.makeText(this, "Please enter a city name", Toast.LENGTH_SHORT).show();
        }

        hideKeyboard();
    }

    private String adjustCityName(String cityName) {
        if (cityName.equalsIgnoreCase("Lahti")) {
            return "Lahtis";
        }
        return cityName;}

    private void fetchWeatherDataForLahti() {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=Lahtis&appid=a52075ba958987a1111ebb7b89eb8499";
        fetchWeatherData(url, "Lahti");
    }

    private void fetchWeatherData(String url, final String city) {
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            displayWeatherDetails(jsonResponse, city);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            textViewCityName.setText("Error fetching weather data");
                            weatherImage.setVisibility(View.GONE);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                textViewCityName.setText("City not found");
                textViewTemperature.setText("");
                tvResult.setText("");
                weatherImage.setVisibility(View.GONE);
            }
        });

        queue.add(stringRequest);
    }

    private void displayWeatherDetails(JSONObject jsonResponse, String city) {
        try {
            if (jsonResponse.has("message")) {
                textViewCityName.setText("City not found");
                textViewTemperature.setText("");
                tvResult.setText("");
                weatherImage.setVisibility(View.GONE);
                return;
            }

            JSONObject jsonObjectMain = jsonResponse.getJSONObject("main");
            double temp = jsonObjectMain.getDouble("temp") - 273.15;
            double feelsLike = jsonObjectMain.getDouble("feels_like") - 273.15;
            float pressure = jsonObjectMain.getInt("pressure");
            int humidity = jsonObjectMain.getInt("humidity");

            JSONObject jsonObjectWind = jsonResponse.getJSONObject("wind");
            String wind = jsonObjectWind.getString("speed");

            int roundedTemp = (int) Math.round(temp);
            textViewTemperature.setText(roundedTemp + "°C");

            JSONObject jsonObjectClouds = jsonResponse.getJSONObject("clouds");
            String clouds = jsonObjectClouds.getString("all");

            JSONObject jsonObjectSys = jsonResponse.getJSONObject("sys");
            String countryName = jsonObjectSys.getString("country");
            String cityName = jsonResponse.getString("name");

            String capitalizedCity = Character.toUpperCase(city.charAt(0)) + city.substring(1);
            textViewCityName.setText(capitalizedCity);

            String output = "Current weather of " + cityName + " (" + countryName + ")"
                    + "\n Feels Like: " + new DecimalFormat("#.##").format(feelsLike) + " °C"
                    + "\n Humidity: " + humidity + "%"
                    + "\n Description: " + jsonResponse.getJSONArray("weather").getJSONObject(0).getString("description")
                    + "\n Wind Speed: " + wind + "m/s"
                    + "\n Cloudiness: " + clouds + "%"
                    + "\n Pressure: " + pressure + " hPa";
            tvResult.setText(output);

            String description = jsonResponse.getJSONArray("weather").getJSONObject(0).getString("description");

            ImageView weatherImage = findViewById(R.id.weatherimage);
            weatherImage.setVisibility(View.VISIBLE);
            if (description.toLowerCase().contains("clear")) {
                weatherImage.setImageResource(R.drawable.cloudy_day_svgrepo_com);
            } else if (description.toLowerCase().contains("rain")) {
                weatherImage.setImageResource(R.drawable.rain_svgrepo_com);
            } else if (description.toLowerCase().contains("drizzle")) {
                weatherImage.setImageResource(R.drawable.cloud_drizzle_svgrepo_com);
            } else if (description.toLowerCase().contains("light rain")) {
                weatherImage.setImageResource(R.drawable.cloud_drizzle_svgrepo_com);
            } else if (description.toLowerCase().contains("snow")) {
                weatherImage.setImageResource(R.drawable.snow_cloud_svgrepo_com);
            } else if (description.toLowerCase().contains("cloud")) {
                weatherImage.setImageResource(R.drawable.overcast_day_svgrepo_com);
            } else if (description.toLowerCase().contains("thunder")) {
                weatherImage.setImageResource(R.drawable.thunder_svgrepo_com);
            } else if (description.toLowerCase().contains("sunny")) {
                weatherImage.setImageResource(R.drawable.sunny_sun_svgrepo_com);
            } else {
                weatherImage.setImageResource(R.drawable.sun_svgrepo_com);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            textViewCityName.setText("Error fetching weather data");
            weatherImage.setVisibility(View.GONE);
        }
    }



    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
