# ${D}: image folder 
DESCRIPTION = "Hello world application from git repo"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = "git://github.com/gslm/meta-plant.git;protocol=https"
S = "${WORKDIR}/git"
SRCREV = "${AUTOREV}"

do_compile() {
    ${CC} userprog.c -o userprog ${LDFLAGS}
}


do_install() {
    install -d ${D}{bindir}
    install -m 0755 userprog ${D}{bindir}
}