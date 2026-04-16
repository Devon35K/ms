# Color processing utilities
import cv2
import numpy as np

def get_hsv_mask(frame, lower, upper):
    hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)
    return cv2.inRange(hsv, lower, upper)
