# SPDX-License-Identifier: MIT
SUMMARY = "Pinned English linguistic data for RHVoice"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://${UNPACKDIR}/rhvoice-license/LICENSE.md;md5=b234ee4d69f5fce4486a80fdaf4a4263"
# The language gitlink is pinned by this RHVoice release; its root license
# covers data unless a component supplies its own license.
SRC_URI = "git://github.com/RHVoice/English.git;protocol=https;branch=main;name=language;destsuffix=english \
 git://github.com/RHVoice/RHVoice.git;protocol=https;nobranch=1;name=license;destsuffix=rhvoice-license"
SRCREV_language = "8a2ea34df72a190dae245460aeb20a867d64775c"
SRCREV_license = "fc0040f80740bb1ebbc0b7bf32e530b6afaedbec"
SRCREV_FORMAT = "language_license"
S = "${UNPACKDIR}/english"
inherit allarch
do_install() {
    install -d ${D}${datadir}/RHVoice/languages/English
    for f in ${S}/*.fst ${S}/*.dt ${S}/*.lts ${S}/*.xml ${S}/language.info; do
        install -m 0644 "$f" ${D}${datadir}/RHVoice/languages/English/
    done
    install -d ${D}${datadir}/licenses/${PN}
    install -m 0644 ${UNPACKDIR}/rhvoice-license/LICENSE.md ${D}${datadir}/licenses/${PN}/
}
FILES:${PN} = "${datadir}/RHVoice ${datadir}/licenses/${PN}"
