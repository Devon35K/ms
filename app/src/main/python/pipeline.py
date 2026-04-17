import cv2
import numpy as np

def process_frame(frame_data, width, height, hsv_lower, hsv_upper):
    """
    Processes a camera frame to find the centroid of a colored object.

    :param frame_data: Raw byte array from CameraX (typically YUV or converted to BGR)
    :param width: Frame width
    :param height: Frame height
    :param hsv_lower: List/tuple of [H, S, V] lower bound
    :param hsv_upper: List/tuple of [H, S, V] upper bound
    :return: (cX, cY) coordinates or (None, None) if not found
    """
    # Convert byte array to numpy array
    nparr = np.frombuffer(frame_data, np.uint8)
    img = cv2.imdecode(nparr, cv2.IMREAD_COLOR)

    if img is None:
        return None, None

    # 1. Resize for performance
    img = cv2.resize(img, (640, 480))

    # 2. BGR to HSV
    hsv = cv2.cvtColor(img, cv2.COLOR_BGR2HSV)

    # 3. Gaussian Blur
    blurred = cv2.GaussianBlur(hsv, (5, 5), 0)

    # 4. Thresholding
    lower = np.array(hsv_lower)
    upper = np.array(hsv_upper)
    mask = cv2.inRange(blurred, lower, upper)

    # 5. Morphological Opening (remove noise)
    mask = cv2.erode(mask, None, iterations=2)
    mask = cv2.dilate(mask, None, iterations=2)

    # 6. Find Contours
    cnts, _ = cv2.findContours(mask.copy(), cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)

    if len(cnts) > 0:
        # 7. Find largest contour
        c = max(cnts, key=cv2.contourArea)
        M = cv2.moments(c)

        if M["m00"] > 0:
            # 8. Centroid calculation
            cX = int(M["m10"] / M["m00"])
            cY = int(M["m01"] / M["m00"])
            return cX, cY

    return None, None
