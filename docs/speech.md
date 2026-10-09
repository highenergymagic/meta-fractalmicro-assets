# Speech assets

The layer supplies independently packaged data for the RHVoice offline
speech engine:

| Package | Contents | Upstream licence |
| --- | --- | --- |
| `rhvoice-english` | English language model, version 2.17 | GPL-2.0-or-later |
| `rhvoice-slt` | SLT voice, version 4.1, 16 kHz model | MIT-CMU |

Recipes pin the exact upstream Git commits. Sources are fetched during
the build and are not stored in this repository. SLT depends on the English
language package; the 24 kHz model is not installed.

English attribution follows RHVoice's source licence. SLT's upstream README
identifies the CMU licence and voice contributors; the installed package
retains that notice and the licence text. These licences are distinct from
the MIT licence on layer metadata.

The OS layer selects the packages and configures Speech Dispatcher and
audio routing. Importing this layer alone does not start a speech service.
See the
[speech configuration](https://github.com/highenergymagic/meta-fractalmicro-openh432/blob/main/docs/speech.md)
for runtime interfaces and limitations.
