# ${D}: image folder 
DESCRIPTION = "Function test application."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI =  "file://userprog.c"

S = "${WORKDIR}"

do_compile() {
   make
}


do_install() {
    install -d ${D}${bindir}
    install -m 0755 userprog ${D}${bindir} 
}