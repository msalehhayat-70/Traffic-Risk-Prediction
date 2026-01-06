

````markdown
# Traffic Risk Prediction App

## Project Overview
Traffic Risk Prediction App ek Android application hai jo users ke input ke basis par road aur traffic conditions ke hisaab se **Traffic Risk Level** predict karta hai. App TensorFlow Lite machine learning model ka use karta hai jo on-device fast predictions provide karta hai.  

Ye app drivers aur commuters ko help karta hai ke wo travel decisions better aur safe le saken.

---

## Features
- Predicts traffic risk: **Low**, **Medium**, **High**, **Very High**
- User input ke options:
  - **Road Type:** City Road, Coastal Road, Highway, Mountain Road, Rural Road
  - **Time of Day:** Morning, Afternoon, Evening, Night
  - **Weather:** Sunny, Rainy, Foggy, Windy, Snowy
  - **Traffic Level:** Low, Medium, High, Very High
- Uses **TensorFlow Lite** model for fast predictions
- Clean and user-friendly interface
- On-device prediction (no internet required)

---



## Installation

1. **Clone the repository**
```bash
git clone https://github.com/msalehhayat-70/TrafficRiskApp.git
````

2. **Open project in Android Studio**

   * Open the cloned folder in Android Studio
   * Make sure **Java JDK** and **Android SDK** are installed

3. **Add TensorFlow Lite model**

   * Place `traffic_model.tflite` inside `app/src/main/assets/`

4. **Run the App**

   * Connect an Android device or use an emulator
   * Click **Run** in Android Studio

---

## How to Use

1. Open the app
2. Select **Road Type**, **Time**, **Weather**, and **Traffic** from the dropdowns
3. Tap **Predict**
4. App will display **Traffic Risk Level** based on the trained ML model

---

## Dataset

* Dataset columns:

  * `Road Type`, `Time`, `Weather`, `Traffic`, `Risk`
  * Encoded numeric features: `Traffic_score`, `Weather_score`, `Time_score`, `RoadType_score`, `Risk_factor`
  * One-hot encoded features: `Road_City Road`, `Road_Coastal Road`, `Road_Highway`, `Road_Mountain Road`, `Road_Rural Road`
* Dataset stored in **Excel** (`.csv`) and used to train the model

---

## Model Training

* **Framework:** TensorFlow / Keras
* **Output:** TensorFlow Lite (`.tflite`) model
* **Steps:**

  1. Preprocess dataset: encode categorical variables and normalize numeric data
  2. Split dataset into **training** and **test** sets
  3. Train TensorFlow model
  4. Convert trained model to **TFLite format**
  5. Integrate `.tflite` model in Android app

---

## Dependencies

* Android Studio
* Java SDK
* TensorFlow Lite
* AndroidX libraries

---


Kya mai ye next step bhi kar doon?
```
