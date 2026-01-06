
 Traffic Risk Prediction App

 Overview
**Traffic Risk Prediction App** ek Android application hai jo users ke inputs ke basis par road aur traffic conditions ko analyze karke **Traffic Risk Level** predict karta hai. App on-device **TensorFlow Lite** model use karta hai jo fast aur reliable predictions provide karta hai.  

Ye app drivers aur commuters ko safer aur informed travel decisions lene me help karta hai.

---

 Features
- Predicts traffic risk: **Low**, **Medium**, **High**, **Very High**
- Input options:
  - **Road Type:** City Road, Coastal Road, Highway, Mountain Road, Rural Road
  - **Time of Day:** Morning, Afternoon, Evening, Night
  - **Weather:** Sunny, Rainy, Foggy, Windy, Snowy
  - **Traffic Level:** Low, Medium, High, Very High
- Fast, on-device predictions using **TensorFlow Lite**
- Clean, user-friendly interface
- Works offline (no internet required)

---

 Installation

1. **Clone the repository**
```bash
git clone https://github.com/msalehhayat-70/TrafficRiskApp.git
````

2. **Open in Android Studio**

   * Ensure **Java JDK** and **Android SDK** are installed

3. **Add TensorFlow Lite model**

   * Place `traffic_model.tflite` in `app/src/main/assets/`

4. **Run the App**

   * Connect an Android device or use emulator
   * Click **Run** in Android Studio

---

## How to Use

1. Open the app
2. Select **Road Type**, **Time**, **Weather**, and **Traffic**
3. Tap **Predict**
4. App displays **Traffic Risk Level** predicted by ML model

---

## Dataset

* Columns: `Road Type`, `Time`, `Weather`, `Traffic`, `Risk`
* Encoded numeric features: `Traffic_score`, `Weather_score`, `Time_score`, `RoadType_score`, `Risk_factor`
* One-hot encoded features: `Road_City Road`, `Road_Coastal Road`, `Road_Highway`, `Road_Mountain Road`, `Road_Rural Road`
* Stored as **Excel (.csv)** and used to train ML model

---

## Model Training

* **Framework:** TensorFlow / Keras
* **Output:** TensorFlow Lite (`.tflite`) model
* **Steps:**

  1. Preprocess dataset: encode categorical variables, normalize numeric data
  2. Split into **training** and **test** sets
  3. Train TensorFlow model
  4. Convert trained model to **TFLite**
  5. Integrate `.tflite` model in Android app

---

## Dependencies

* Android Studio
* Java SDK
* TensorFlow Lite
* AndroidX libraries

```

