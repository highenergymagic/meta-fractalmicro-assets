# SPDX-License-Identifier: MIT
SUMMARY = "Pinned integer-only Vorbis decoder for reproducible build assets"
HOMEPAGE = "https://wiki.xiph.org/Tremor"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=db1b7a668b2a6f47b2af88fb008ad555"
SRC_URI = "git://gitlab.xiph.org/xiph/tremor.git;protocol=https;nobranch=1"
SRCREV = "820fb3237ea81af44c9cc468c8b4e20128e3e5ad"
DEPENDS = "libogg-native"
inherit autotools pkgconfig native
# Defined signed wrapping and no alias-based transformations for this codec.
CFLAGS:append = " -fwrapv -fno-strict-aliasing"
