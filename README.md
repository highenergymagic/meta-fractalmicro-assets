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

The layer provides KDE 3 startup and shutdown sounds, converted from
checksum-verified Ogg sources to PCM WAV using a pinned integer-only decoder.
Original source media and the upstream licence notice accompany the
converted files.

See [system sound assets](docs/system-sounds.md) for source provenance,
conversion requirements and the package's licensing basis. See the
[playback configuration](https://github.com/highenergymagic/meta-fractalmicro-openh432/blob/main/docs/system-sounds.md)
for image selection and service policy.

Importing this layer does not enable playback or set device volume.

## Licence

Integration metadata is MIT-licensed. Third-party assets and conversion
tools retain their upstream licences; the metadata licence does not apply
to them. Asset-specific attribution and licensing qualifications are
documented with each asset recipe.
