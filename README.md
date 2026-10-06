# Fractal Microsystems assets

Reproducibly fetched third-party media for OpenH432. Integration metadata is
MIT; each asset recipe retains its upstream license and provenance. A separate
layer is an organizational boundary, not a way to change licensing obligations.
No media binaries are stored in Git.

## KDE 3 system sounds

The recipe selects KDE_Startup_1.ogg and KDE_Logout_1.ogg from the checksum-pinned
official kdebase 3.5.10 source archive. Each selected file has its own SHA256 check in the recipe.
It uses the archive's GPLv2 COPYING as the package-level licensing basis
(GPL-2.0-only, without assuming an additional later-version grant).
No separate sound-author notice was found; this is a package-level
interpretation, not a newly discovered asset-specific license statement.
Original Ogg files and COPYING are installed alongside the generated WAVs.

Conversion uses pinned libvorbis in the build container, not host multimedia
tools. Inputs must be single-stream, 44.1 kHz stereo; conversion preserves that
rate and creates metadata-free signed 16-bit little-endian PCM WAVs.
This layer does not enable playback or change the speaker volume ceiling.
