import unittest
from pipeline import process_frame
import numpy as np

class TestPipeline(unittest.TestCase):
    def test_process_frame(self):
        frame = np.zeros((100, 100, 3), dtype=np.uint8)
        result = process_frame(frame)
        self.assertEqual(frame.shape, result.shape)

if __name__ == '__main__':
    unittest.main()
