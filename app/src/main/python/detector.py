import pipeline

def get_centroid(frame_bytes, width, height, h_min, s_min, v_min, h_max, s_max, v_max):
    """
    Bridge function called from Java.
    """
    lower = [h_min, s_min, v_min]
    upper = [h_max, s_max, v_max]

    cX, cY = pipeline.process_frame(frame_bytes, width, height, lower, upper)

    if cX is not None:
        return [cX, cY]
    return None
