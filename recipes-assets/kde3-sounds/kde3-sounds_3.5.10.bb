# SPDX-License-Identifier: MIT
SUMMARY = "Selected KDE 3 sounds with pinned integer PCM conversion"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=5c213a7de3f013310bd272cdb6eb7a24"
SRC_URI = "https://download.kde.org/Attic/3.5.10/src/kdebase-${PV}.tar.bz2 file://decode.c"
SRC_URI[sha256sum] = "77aa9d8f28c532f2e7a5157a7f4ba8df1001f00fa1cb72cb70b388b3d0e16b61"
S = "${UNPACKDIR}/kdebase-${PV}"
inherit allarch
DEPENDS = "tremor-native"
do_configure[noexec] = "1"
do_compile() {
    ${BUILD_CC} ${BUILD_CFLAGS} ${BUILD_LDFLAGS} ${UNPACKDIR}/decode.c \
        -o ${B}/decode -lvorbisidec -logg
    cd ${S}/kcontrol/knotify/sounds
    printf '%s\n' \
        'd9bc793b2d1ced1728862cdd01274f943291dabf4eb4215725251d15a3133c73  KDE_Startup_1.ogg' \
        'e0bd2e7efe63345e82412032b8aebc8d41be6d0756400f53884ddd8250832d9d  KDE_Logout_1.ogg' | sha256sum -c -
    ${B}/decode KDE_Startup_1.ogg ${B}/startup.wav
    ${B}/decode KDE_Logout_1.ogg ${B}/shutdown.wav
}
do_install() {
    install -d ${D}${datadir}/openh432/sounds/originals ${D}${datadir}/licenses/kde3-sounds
    install -m 0644 ${B}/startup.wav ${B}/shutdown.wav ${D}${datadir}/openh432/sounds/
    install -m 0644 ${S}/kcontrol/knotify/sounds/KDE_Startup_1.ogg \
        ${S}/kcontrol/knotify/sounds/KDE_Logout_1.ogg ${D}${datadir}/openh432/sounds/originals/
    install -m 0644 ${S}/COPYING ${D}${datadir}/licenses/kde3-sounds/
}
FILES:${PN} = "${datadir}/openh432/sounds ${datadir}/licenses/kde3-sounds"
