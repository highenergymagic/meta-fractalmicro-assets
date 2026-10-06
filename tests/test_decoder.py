# SPDX-License-Identifier: MIT
from pathlib import Path
import unittest
ROOT = Path(__file__).resolve().parents[1]

class DecoderContract(unittest.TestCase):
    def test_fixed_point_source_and_license_pin(self):
        text = (ROOT / "recipes-multimedia/tremor/tremor-native_1.2.1.bb").read_text()
        self.assertIn('SRCREV = "820fb3237ea81af44c9cc468c8b4e20128e3e5ad"', text)
        self.assertIn("BSD-3-Clause", text)
        self.assertIn("inherit autotools pkgconfig native", text)

    def test_conversion_has_explicit_pcm_byte_order_and_bounds(self):
        text = (ROOT / "recipes-assets/kde3-sounds/files/decode.c").read_text()
        self.assertIn("<tremor/ivorbisfile.h>", text)
        self.assertIn("u16(out,(uint16_t)pcm[i])", text)
        self.assertIn("got%4", text)
        self.assertIn("frames>44100*10", text)

    def test_original_asset_hashes_remain(self):
        text = (ROOT / "recipes-assets/kde3-sounds/kde3-sounds_3.5.10.bb").read_text()
        self.assertIn('DEPENDS = "tremor-native"', text)
        self.assertIn("d9bc793b2d1ced1728862cdd01274f943291dabf4eb4215725251d15a3133c73", text)
        self.assertIn("e0bd2e7efe63345e82412032b8aebc8d41be6d0756400f53884ddd8250832d9d", text)
