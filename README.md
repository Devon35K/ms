# MotionSmash 🎮

> **AR Motion Smasher** — A Computer Vision-Based Gesture Recognition System for Mobile Augmented Reality Gaming

[![Platform](https://img.shields.io/badge/Platform-Android%208.0%2B-green)](https://developer.android.com)
[![Python](https://img.shields.io/badge/Python-3.10%2B-blue)](https://python.org)
[![OpenCV](https://img.shields.io/badge/OpenCV-4.x-orange)](https://opencv.org)
[![Course](https://img.shields.io/badge/Course-ICE%20322%20%7C%20ICE%20323-purple)](https://usep.edu.ph)

---

## 📌 About

**MotionSmash** is an augmented reality (AR) mobile game for Android that uses your phone's front-facing camera and real-time computer vision to track your hand movements. Instead of tapping a screen, you physically smash falling virtual objects by moving your hand or a colored marker in front of the camera.

Built as a partial fulfillment for **ICE 322 (Professional Elective 2)** and **ICE 323 (Professional Elective 3)** at the **University of Southeastern Philippines**.

---

## 👥 Team

| Name | Role |
|---|---|
| Crucio, John Paul S. | Computer Vision / Python Pipeline |
| Micaroz, Arthur Dale Enrique | Android Game Frontend |
| Renigado, Kyle Harvey C. | Integration & Testing |

**Submitted to:** Ms. Luchi Dela Cruz  
**Date:** April 2026

---

## 🗂️ Project Location

```
D:\App\laragon\www\LE\MotionSmash\
```

---

## 🏗️ Project Structure

```
MotionSmash/
│
├──
│   ├── app
│   │   └── src/
│   │       └── main/
│   │           ├── java/com/motionsmash/
│   │           │   ├── MainActivity.java       # App entry point
│   │           │   ├── GameView.java           # SurfaceView game renderer
│   │           │   ├── GameEngine.java         # Game loop, collision, scoring
│   │           │   └── BridgeManager.java      # Chaquopy / socket bridge
│   │           ├── python/
│   │           │   ├── detector.py             # Embedded Python detector
│   │           │   └── pipeline.py             # Image processing pipeline
│   │           ├── res/
│   │           │   ├── layout/
│   │           │   │   ├── activity_main.xml
│   │           │   │   ├── activity_game.xml
│   │           │   │   └── activity_calibration.xml
│   │           │   ├── drawable/
│   │           │   └── values/
│   │           │       ├── strings.xml
│   │           │       └── colors.xml
│   │           └── AndroidManifest.xml
│   ├── build.gradle
│   └── settings.gradle
│
├── python_module/                      # Standalone Python module (dev/testing)
│   ├── detector.py                     # Main detection script
│   ├── pipeline.py                     # Full OpenCV pipeline
│   ├── calibration.py                  # HSV range calibration tool
│   ├── socket_server.py                # Fallback: local socket server
│   ├── requirements.txt
│   ├── utils/
│   │   ├── __init__.py
│   │   ├── color_utils.py
│   │   └── frame_utils.py
│   └── tests/
│       ├── test_detector.py
│       └── test_pipeline.py
│
├── assets/
│   ├── markers/                        # Reference color marker images
│   └── sounds/                         # Collision / game audio
│
├── data/
│   ├── raw_frames/                     # Captured validation frames
│   └── validation/                     # Annotated centroid data
│
├── docs/
│   ├── ICE_322-Project_Proposal.pdf
│   └── ICE_323-Project_Proposal.pdf
│
├── .gitignore
└── README.md
```

---

## ⚙️ Tech Stack

| Layer | Technology |
|---|---|
| Image Processing | Python 3.10+, OpenCV (cv2), NumPy |
| Game Frontend | Java, Android Studio |
| Camera | Android CameraX API |
| Rendering | Android Canvas / SurfaceView |
| Python–Java Bridge | Chaquopy SDK (primary) |
| Fallback Bridge | Python socket server (Flask/socket) |
| Debug Visualization | Matplotlib |

---

## 🚀 Getting Started

### Prerequisites

- Android Studio (latest stable)
- Python 3.10 or higher
- Android device running **Android 8.0 (Oreo) or higher**
- A brightly colored marker (red, green, or orange recommended)

### 1. Open the project

```
D:\App\laragon\www\LE\MotionSmash\
```

Open `android_app/` in Android Studio.

### 2. Install Python dependencies

```bash
cd python_module
pip install -r requirements.txt
```

### 3. Test the Python pipeline first (standalone)

```bash
cd python_module
python detector.py
```

This opens your webcam and prints live centroid coordinates. Tune your HSV values using `calibration.py` before deploying to Android.

### 4. Build and run on Android

1. Open `android_app/` in Android Studio
2. Configure Chaquopy plugin in `build.gradle`
3. Connect Android device via USB (enable Developer Mode)
4. Run → grant camera permission → calibrate HSV → play!

---

## 🎮 How to Play

1. Launch **MotionSmash** on your Android device
2. On the **Calibration Screen**, adjust HSV sliders until your hand/marker is cleanly detected
3. Tap **Start Game**
4. Move your hand in front of the camera to smash falling objects
5. Each hit = **+10 points** | Each miss = **-1 life**
6. Difficulty increases as your score rises

---

## 🔬 Computer Vision Pipeline

```
Camera Frame (CameraX)
        ↓
Resize to 640×480
        ↓
BGR → HSV Color Space
        ↓
Gaussian Blur (5×5 kernel)
        ↓
HSV Thresholding  ←  user-defined HSV range
        ↓
Morphological Opening (erode → dilate)
        ↓
Contour Detection (cv2.findContours)
        ↓
Largest Contour → cv2.moments()
        ↓
Centroid Output: cX, cY
        ↓
Java Game Engine (collision detection)
```

---

## 📊 Performance Targets

| Metric | Target |
|---|---|
| Frame Rate | ≥ 15 FPS |
| Detection Accuracy | ≥ 85% |
| Latency per frame | ≤ 20ms |
| Validation frames | 200–300 |
| Lighting conditions | 3 (fluorescent, natural, low-light) |

---

## ⚠️ Known Limitations

- Indoor use only — direct sunlight degrades detection
- Tracks one object at a time
- Marker must be distinctly colored against the background
- FPS varies depending on device hardware

---

## 📚 References

- OpenCV Team. (2024). OpenCV Documentation. https://docs.opencv.org/4.x/
- Google LLC. (2024). Android CameraX API. https://developer.android.com/training/camerax
- Chaquopy. (2024). Python SDK for Android. https://chaquo.com/chaquopy/doc/
- Dhamodaran et al. (2024). Hand gesture recognition using OpenCV. IJRPR, 5(5), 8039–8050.

---

## 📄 License

Academic project — University of Southeastern Philippines, 2026. All rights reserved by the authors.






