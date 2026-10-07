# System sound assets

## Source and licence

The sound recipe selects `KDE_Startup_1.ogg` and `KDE_Logout_1.ogg` from
the official kdebase 3.5.10 source archive. The archive and each selected
file are checksum-verified by the recipe.

The package uses the archive's GPLv2 COPYING as its licensing basis:
`GPL-2.0-only`, without assuming an additional later-version grant.
No separate sound-author notice has been established. This is a
package-level interpretation, not an asset-specific permission statement.
Original Ogg files and COPYING are installed alongside the generated WAVs.

## Conversion

Conversion uses a commit-pinned Tremor fixed-point decoder built by
OpenEmbedded. It does not invoke host-installed multimedia utilities.

Inputs must be single-stream, 44.1 kHz stereo Vorbis. The converter preserves
the rate and emits metadata-free, signed 16-bit little-endian PCM WAVs.
Integer-only decoding and explicit byte ordering keep conversion independent
of the builder's native architecture.

## Runtime integration

The distribution layer owns image package selection and playback services.
This layer does not enable a service or alter the speaker-volume limit.
See the [system sound configuration](https://github.com/highenergymagic/meta-fractalmicro-openh432/blob/main/docs/system-sounds.md)
for runtime policy.
