# OpenH432 asset layer

Yocto/OpenEmbedded recipes for third-party media used by OpenH432.
This layer fetches verified upstream sources and produces reproducible
runtime assets. Media binaries are not stored in Git.

## Build integration

| Setting | Value |
| --- | --- |
| Yocto series | Wrynose |
| Layer dependency | OpenEmbedded Core |
| Build entry point | [openh432-build](https://github.com/highenergymagic/openh432-build) |

The OpenH432 manifest includes and pins this layer. Follow its build
workflow rather than invoking host media-conversion tools.
[meta-fractalmicro-openh432](https://github.com/highenergymagic/meta-fractalmicro-openh432)
selects the image packages and configures playback services.

## Assets

| Asset family | Packages | Reference |
| --- | --- | --- |
| KDE 3 system sounds | Startup and shutdown PCM WAVs, source Oggs and licence notice | [System sounds](docs/system-sounds.md) |
| RHVoice English speech data | English language model and SLT 16 kHz voice | [Speech assets](docs/speech.md) |

Recipes pin upstream source revisions and checksums. Sound conversion uses a
pinned integer-only decoder; third-party attribution accompanies the installed
assets. Importing this layer does not enable playback or set device volume.
The distribution layer owns [playback policy](https://github.com/highenergymagic/meta-fractalmicro-openh432/blob/main/docs/system-sounds.md)
and speech backend selection.

## Licence

Integration metadata is MIT-licensed. Third-party assets and conversion
tools retain their upstream licences; the metadata licence does not apply
to them. Asset-specific attribution and licensing qualifications are
documented with each asset recipe.
