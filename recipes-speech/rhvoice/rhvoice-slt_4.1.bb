# SPDX-License-Identifier: MIT
SUMMARY = "SLT English voice for RHVoice, 16 kHz model"
LICENSE = "MIT-CMU"
LIC_FILES_CHKSUM = "file://README.md;md5=e43355497e8453f10a6b9935d62f9c0a"
SRC_URI = "git://github.com/RHVoice/slt-eng.git;protocol=https;branch=main"
SRCREV = "c17dded68322016aba2868d9c29f11bba14d275f"
inherit allarch
RDEPENDS:${PN} = "rhvoice-english"
do_install() {
    install -d ${D}${datadir}/RHVoice/voices/slt/16000 ${D}${datadir}/licenses/${PN}
    install -m 0644 ${S}/voice.info ${S}/voice.params ${D}${datadir}/RHVoice/voices/slt/
    install -m 0644 ${S}/16000/* ${D}${datadir}/RHVoice/voices/slt/16000/
    install -m 0644 ${S}/README.md ${D}${datadir}/licenses/${PN}/
    install -m 0644 ${COMMON_LICENSE_DIR}/MIT-CMU ${D}${datadir}/licenses/${PN}/
}
FILES:${PN} = "${datadir}/RHVoice ${datadir}/licenses/${PN}"
