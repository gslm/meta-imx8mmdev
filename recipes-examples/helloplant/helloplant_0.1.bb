# ${D}: image folder 
DESCRIPTION = "Hello world application from meta-plant layer."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI =  "file://userprog.c \
            file://functest.c"

S = "${WORKDIR}"

do_compile() {
    ${CC} userprog.c functest.c ${LDFLAGS} -o userprog -o functest
}


do_install() {
    install -d ${D}${bindir}
    install -m 0755 userprog ${D}${bindir}    
    install -m 0755 functest ${D}${bindir}    
}