# SPDX-License-Identifier: MIT
from pathlib import Path
import unittest

ROOT = Path(__file__).resolve().parents[1]


class SpeechAssets(unittest.TestCase):
    def test_separate_pinned_voice_and_language(self):
        for name in ("rhvoice-english_2.17.bb", "rhvoice-slt_4.1.bb"):
            recipe = (ROOT / "recipes-speech/rhvoice" / name).read_text()
            self.assertIn("inherit allarch", recipe)
            self.assertIn("LIC_FILES_CHKSUM", recipe)
            self.assertNotIn("AUTOREV", recipe)
            self.assertNotIn('LICENSE = "MIT"', recipe)
        voice = (ROOT / "recipes-speech/rhvoice/rhvoice-slt_4.1.bb").read_text()
        self.assertIn("c17dded68322016aba2868d9c29f11bba14d275f", voice)
        self.assertIn('LICENSE = "MIT-CMU"', voice)
        self.assertIn("/16000/", voice)
        self.assertNotIn("/24000/", voice)
        self.assertIn('RDEPENDS:${PN} = "rhvoice-english"', voice)
